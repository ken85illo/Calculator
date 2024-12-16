import javax.swing.JFrame;
import java.awt.Color;
import java.awt.FlowLayout;

public class Calculator extends JFrame{
    private final int WIDTH = 450, HEIGHT = 600; 
    private final String TITLE = "Calculator";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.add(new Display());
    }

    private Calculator() {
        this.setTitle(TITLE);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GRAY);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setVisible(true);
    }
}
