import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.FlowLayout;

public class Calculator extends JFrame{
    private final int WINDOW_WIDTH = 450, WINDOW_HEIGHT = 505; 
    private final int HORIZONTAL_GAP = 5, VERTICAL_GAP = 5;
    private final String TITLE = "Calculator";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        Display display = new Display();
        calculator.add(display);

        Functions functions = new Functions(display.getLabel());
        for (JButton button : functions.getButtons(calculator.VERTICAL_GAP)) 
            calculator.add(button);

        calculator.setVisible(true);
    }

    private Calculator() {
        ImageIcon icon = new ImageIcon("calculator.jpg");
        this.setTitle(TITLE);
        this.setIconImage(icon.getImage());
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GRAY);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP));
    }
}
