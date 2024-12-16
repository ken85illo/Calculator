import javax.swing.JFrame;
import java.awt.Color;

public class Calculator extends JFrame{
    private final int WIDTH = 450, HEIGHT = 600; 
    private final String TITLE = "Calculator";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }

    private Calculator() {
        this.setTitle(TITLE);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
