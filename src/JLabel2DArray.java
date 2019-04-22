import javax.swing.*;

public class JLabel2DArray {

    private JLabel[] array;
    private int y;

    public JLabel2DArray(int x, int y){
        array = new JLabel[x * y];
        this.y = y;

        for(int i = 0; i < (x * y); i++){
            array[i] = new JLabel(" ");
        }
    }

    public JLabel2DArray(int x, int y, int constant){
        array = new JLabel[x * y];
        this.y = y;

        for(int i = 0; i < (x * y); i++){
            array[i] = new JLabel(" ", constant);
        }
    }

    public JLabel get(int x, int y){
        return array[(this.y * x) + y];
    }

}
