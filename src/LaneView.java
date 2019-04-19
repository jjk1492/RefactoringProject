/*
 *  constructs a prototype Lane View
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class LaneView implements LaneObserver, ActionListener {

	private int roll;
	private boolean initDone = true;

	JFrame frame;
	Container cpanel;
	Vector bowlers;
	int cur;
	Iterator bowlIt;

	JPanel2DArray balls;
	JLabel2DArray ballLabel;
	JPanel2DArray scores;
	JLabel2DArray scoreLabel;
	JPanel2DArray ballGrid;
	JPanel[] pins;

	JButton maintenance;
	Lane lane;

	public LaneView(Lane lane, int laneNum) {

		this.lane = lane;

		initDone = true;
		frame = new JFrame("Lane " + laneNum + ":");
		cpanel = frame.getContentPane();
		cpanel.setLayout(new BorderLayout());

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});

		cpanel.add(new JPanel());

	}

	public void show() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}

	private JPanel makeFrame(Party party) {

		initDone = false;
		bowlers = party.getMembers();
		int numBowlers = bowlers.size();

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(0, 1));

		balls = new JPanel2DArray(numBowlers, 23);
		ballLabel = new JLabel2DArray(numBowlers, 23);
		scores = new JPanel2DArray(numBowlers, 10);
		scoreLabel = new JLabel2DArray(numBowlers, 10, SwingConstants.CENTER);
		ballGrid = new JPanel2DArray(numBowlers, 10);
		pins = new JPanel[numBowlers];

		for (int i = 0; i != numBowlers; i++) {
			for (int j = 0; j != 23; j++) {
			    balls.addJLabel(i, j, ballLabel.get(i, j));
			}
			for (int j = 0; j != 9; j++) {
			    ballGrid.setLayout(i, j, 0, 3);
			    ballGrid.addJComponent(i, j, new JLabel(" "), BorderLayout.EAST);
			    ballGrid.addJComponent(i, j, balls.get(i, 2 * j), BorderLayout.EAST);
			    ballGrid.addJComponent(i, j, balls.get(i, 2 * j + 1), BorderLayout.EAST);
			}
			ballGrid.setLayout(i, 9, 0, 3);
			ballGrid.addJPanel(i, 9, balls.get(i, 2 * 9));
			ballGrid.addJPanel(i, 9, balls.get(i, 2 * 9 + 1));
			ballGrid.addJPanel(i, 9, balls.get(i, 2 * 9 + 2));
			pins[i] = new JPanel();
			pins[i].setBorder(BorderFactory.createTitledBorder(((Bowler) bowlers.get(i)).getNick()));
			pins[i].setLayout(new GridLayout(0, 10));
			for (int k = 0; k != 10; k++) {
			    scores.setBoarder(i, k, BorderFactory.createLineBorder(Color.BLACK));
			    scores.setLayout(i, k, 0, 1);
			    scores.addJComponent(i, k, ballGrid.get(i, k), BorderLayout.EAST);
			    scores.addJComponent(i, k, scoreLabel.get(i, k), BorderLayout.SOUTH);
			    pins[i].add(scores.get(i, k), BorderLayout.EAST);
			}
			panel.add(pins[i]);
		}
		initDone = true;
		return panel;
	}

	public void receiveLaneEvent(LaneEvent le) {
		if (lane.isPartyAssigned()) {
			int numBowlers = le.getParty().getMembers().size();
			while (!initDone) {
				//System.out.println("chillin' here.");
				try {
					Thread.sleep(1);
				} catch (Exception e) {
				}
			}

			if (le.getFrameNum() == 1
				&& le.getBall() == 0
				&& le.getIndex() == 0) {
				System.out.println("Making the frame.");
				cpanel.removeAll();
				cpanel.add(makeFrame(le.getParty()), "Center");

				// Button Panel
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());

				//Insets buttonMargin = new Insets(4, 4, 4, 4);

				maintenance = new JButton("Maintenance Call");
				JPanel maintenancePanel = new JPanel();
				maintenancePanel.setLayout(new FlowLayout());
				maintenance.addActionListener(this);
				maintenancePanel.add(maintenance);

				buttonPanel.add(maintenancePanel);

				cpanel.add(buttonPanel, "South");

				frame.pack();

			}

			int[][] lescores = le.getCumulScore();

			for (int k = 0; k < numBowlers; k++) {
				int[] scores = (int[]) le.getScore().get(bowlers.get(k));

				for (int i = 0; i <= le.getFrameNum() - 1; i++) {
					if (lescores[k][i] != 0)
						scoreLabel.get(k, i).setText((lescores[k][i]) + "");
				}

				for (int i = 0; i < 21; i++) {
					if (scores[i] != -1)
						if (scores[i] == 10 && (i % 2 == 0 || i == 19))
							ballLabel.get(k, i).setText("X");
						else if (i > 0 && scores[i] + scores[i - 1] == 10 && i % 2 == 1)
							ballLabel.get(k, i).setText("/");
						else if ( scores[i] == -2 ){
							ballLabel.get(k, i).setText("F");
						} else
							ballLabel.get(k, i).setText((scores[i]) + "");
				}
			}

		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(maintenance)) {
			lane.pauseGame();
		}
	}

}
