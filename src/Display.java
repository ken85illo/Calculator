import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display extends JPanel{
    private final int PANEL_WIDTH = 400, PANEL_HEIGHT = 100;
    private JLabel label = new JLabel();

    public Display() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
        this.setLayout(null);

        this.add(label);
        label.setBounds(0, 0, WIDTH - 15, HEIGHT);
        label.setText("100/100");
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(new Font("Open Sans", Font.PLAIN, 60));
    }
}
