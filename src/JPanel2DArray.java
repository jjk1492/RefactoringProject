import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class JPanel2DArray {

    private JPanel[] arrays;
    private int y;

    public JPanel2DArray(int x, int y){
        arrays = new JPanel[x * y];
        this.y = y;

        for(int i = 0; i < (x * y); i++){
            arrays[i] = new JPanel();
            arrays[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }

    public JPanel get(int x, int y){
        return arrays[(this.y * x) + y];
    }

    public void addJLabel(int x, int y, JLabel o){
        arrays[(this.y * x) + y].add(o);
    }

    public void addJPanel(int x, int y, JPanel o){
        arrays[(this.y * x) + y].add(o);
    }

    public void addJComponent(int x, int y, Component component, String layout){
        arrays[(this.y * x) + y].add(component, layout);
    }

    public void setBoarder(int x, int y, Border border){
        arrays[(this.y * x) + y].setBorder(border);
    }

    public void setLayout(int x, int y, int rows, int cols){
        arrays[(this.y * x) + y].setLayout(new GridLayout(rows, cols));
    }

}
