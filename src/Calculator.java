import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.FlowLayout;

public class Calculator extends JFrame{
    private final int WINDOW_WIDTH = 450, WINDOW_HEIGHT = 525; 
    private static final int HORIZONTAL_GAP = 5, VERTICAL_GAP = 5;
    private final String TITLE = "Calculator";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.add(new Display());

        for (JButton button : Functions.getButtons(VERTICAL_GAP)) 
            calculator.add(button);

        calculator.setVisible(true);
    }

    private Calculator() {
        this.setTitle(TITLE);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GRAY);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP));
    }
}
