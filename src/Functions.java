import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

enum Buttons {
    ALL_CLEAR("AC", Color.ORANGE), 
    DELETE("DEL", Color.ORANGE), 
    MODULUS("%", Color.ORANGE), 
    PLUS("+", Color.ORANGE), 
    NUMBER_7("7", Color.LIGHT_GRAY), 
    NUMBER_8("8", Color.LIGHT_GRAY), 
    NUMBER_9("9", Color.LIGHT_GRAY), 
    MINUS("-", Color.ORANGE),
    NUMBER_4("4", Color.LIGHT_GRAY), 
    NUMBER_5("5", Color.LIGHT_GRAY), 
    NUMBER_6("6", Color.LIGHT_GRAY), 
    TIMES("*", Color.ORANGE),
    NUMBER_1("1", Color.LIGHT_GRAY), 
    NUMBER_2("2", Color.LIGHT_GRAY), 
    NUMBER_3("3", Color.LIGHT_GRAY), 
    DIVIDE("/", Color.ORANGE),
    NUMBER_0("0", Color.LIGHT_GRAY), 
    POINT(".", Color.LIGHT_GRAY), 
    EQUALS("=", Color.ORANGE);

    JButton guiButton;

    Buttons(String text, Color color) {
        this.guiButton = new JButton(text);
        this.guiButton.setBackground(color);
        this.guiButton.setFocusable(false);
        this.guiButton.setFont(new Font("Open Sans", Font.BOLD, 20));
    }
}

public class Functions implements ActionListener{
    
    private static final int BUTTON_WIDTH = 90, BUTTON_HEIGHT = 70;
    private static final int NUMBER_OF_BUTTONS = 19;
    private static Buttons[] buttons = Buttons.values();


    public static JButton[] getButtons(int verticalGap) {
        JButton[] guiButtons = new JButton[NUMBER_OF_BUTTONS];

        for(int i = 0; i < NUMBER_OF_BUTTONS; i++) {
            guiButtons[i] = buttons[i].guiButton;
            guiButtons[i].setBorder(BorderFactory.createRaisedBevelBorder());
            if(buttons[i] == Buttons.EQUALS)
                guiButtons[i].setPreferredSize(new Dimension((BUTTON_WIDTH*2)+5, BUTTON_HEIGHT));
            else
                guiButtons[i].setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        }
        
        return guiButtons;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Buttons.ALL_CLEAR.guiButton) {

        }
        else if(e.getSource() == Buttons.DELETE.guiButton) {

        }
        else if(e.getSource() == Buttons.MODULUS.guiButton) {

        }
        else if(e.getSource() == Buttons.PLUS.guiButton) {

        }
        else if(e.getSource() == Buttons.NUMBER_7.guiButton) {

        }
        else if(e.getSource() == Buttons.NUMBER_8.guiButton) {
            
        }
        else if(e.getSource() == Buttons.NUMBER_9.guiButton) {
            
        }
        else if(e.getSource() == Buttons.MINUS.guiButton) {
            
        }
        else if(e.getSource() == Buttons.NUMBER_4.guiButton) {
            
        }
        else if(e.getSource() == Buttons.NUMBER_5.guiButton) {
            
        }
        else if(e.getSource() == Buttons.NUMBER_6.guiButton) {
            
        }
        else if(e.getSource() == Buttons.TIMES.guiButton) {
            
        }
        else if(e.getSource() == Buttons.NUMBER_1.guiButton) {
            
        }
        else if(e.getSource() == Buttons.NUMBER_2.guiButton) {
            
        }
        else if(e.getSource() == Buttons.NUMBER_3.guiButton) {
            
        }
        else if(e.getSource() == Buttons.DIVIDE.guiButton) {
            
        }
        else if(e.getSource() == Buttons.NUMBER_0.guiButton) {
            
        }
        else if(e.getSource() == Buttons.POINT.guiButton) {
            
        }
        else if(e.getSource() == Buttons.EQUALS.guiButton) {
            
        }
    }
}
