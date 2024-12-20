import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CalculatorButtons {
    private final int BUTTON_WIDTH = 90, BUTTON_HEIGHT = 65;
    private final int MAX_CHARACTERS = 14;

    private JButton[] guiButtons = new JButton[Button.values().length];

    private JLabel label;

    private DecimalFormat decimalFormat = new DecimalFormat("#,###.############");
    
    private double firstOperand = -1, secondOperand = -1;
    private char operator = 0;
    private boolean paused = false;
    private boolean pointed = false;

    public CalculatorButtons(JLabel label) {
        label.setText("0");
        this.label = label;

        int i = 0;
        for(Button button : Button.values()) 
            guiButtons[i++] = button.guiButton;
    }

    public JButton[] getButtons(int verticalGap) {
        for(JButton guiButton : guiButtons) {
            guiButton.addActionListener(listener);
            if(guiButton.getText() == Button.EQUALS.text)
                guiButton.setPreferredSize(new Dimension((BUTTON_WIDTH*2)+5, BUTTON_HEIGHT));
            else
                guiButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        }
        
        return guiButtons;
    }

    private ActionListener listener = (e) -> {
        for (Button button : Button.values()) 
        if(e.getSource() == button.guiButton) 
            checkButton(button);
        System.out.println(firstOperand + " " + operator + " " + secondOperand);
    };

    private void checkButton(Button button) {
        this.setTextWithDelayEffect(label.getText());

        switch(button) {
            case Button.POINT: 
                if(paused)
                    label.setText(label.getText().concat("0"));

                if(label.getText().length() != MAX_CHARACTERS && !label.getText().endsWith(".") && !pointed) {
                    label.setText(label.getText().concat("."));
                    pointed = true;
                }

                break;

            case Button.NUMBER_0: case Button.NUMBER_1: case Button.NUMBER_2: case Button.NUMBER_3: 
            case Button.NUMBER_4: case Button.NUMBER_5: case Button.NUMBER_6: 
            case Button.NUMBER_7: case Button.NUMBER_8: case Button.NUMBER_9: 
                if(label.getText().length() == 1 && label.getText().charAt(0) == '0')
                    label.setText("");
        
                if(paused) {
                    label.setText("");
                    paused = false;
                }

                if(label.getText().length() != MAX_CHARACTERS) 
                    label.setText(decimalFormat.format(Double.parseDouble(label.getText().concat(button.text).replace(",", ""))));

                break;
                
            case Button.DELETE: 
                if(label.getText().endsWith("."))
                    pointed = false;

                label.setText(label.getText().substring(0, label.getText().length() - 1));
                
                if(label.getText().equals(""))
                    label.setText("0");

                break;

            case Button.ALL_CLEAR: 
                firstOperand = secondOperand = -1;
                operator = 0;
                label.setText("0");
                break; 

            case Button.PLUS: case Button.MINUS: case Button.TIMES: case Button.DIVIDE: case Button.MODULUS:
                if (paused) {
                    operator = button.text.charAt(0);  
                    paused = false;
                }
                else if(operator == 0) {
                    if(label.getText().endsWith("."))
                        label.setText(label.getText().replace(".", ""));

                    firstOperand = Double.parseDouble(label.getText().replace(",", ""));
                    operator = button.text.charAt(0);   
                }
                else if(secondOperand == -1) {
                    calculate(button);
                    operator = button.text.charAt(0);
                }

                paused = true;
                
                break;

            case Button.EQUALS:
                if(operator != 0 && !paused) {
                    calculate(button);
                    operator = 0;
                    paused = true;
                }
        }
    }

    private void calculate(Button button) {
        secondOperand = Double.parseDouble(label.getText().replace(",", ""));
        if(secondOperand == 0) {
            calculatorError();
            return;
        }

        switch(operator) {
            case '+':
                firstOperand += secondOperand;
                break;
            case '-':
                if(firstOperand < secondOperand)
                    firstOperand = 0;
                firstOperand -= secondOperand; 
                break;
            case '*':
                firstOperand *= secondOperand; 
                break;
            case '/':
                firstOperand /= secondOperand; 
                break;
            case '%':
                firstOperand %= secondOperand; 
                break;
        }

        secondOperand = -1;
        
        label.setText(decimalFormat.format(firstOperand));
        limitCharacters(MAX_CHARACTERS);
    }

    private void setTextWithDelayEffect(String text) {
        label.setForeground(Color.GRAY);;
        label.setText(text);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {                
                label.setForeground(Color.WHITE);
            }
        };
        timer.schedule(task, 50);
    }

    private void limitCharacters(int size) {
        if(label.getText().length() > size) {
            if((firstOperand - (long)firstOperand) == 0)  
                calculatorError();
            else 
                label.setText(decimalFormat.format(Double.parseDouble(label.getText().replace(",", "").substring(0, size))));;
        }
    }

    private void calculatorError() {
        label.setText("ERROR");
        firstOperand = -1;
        operator = 0;
        secondOperand = -1;
        paused = true;
    }
}

enum Button {
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
    String text;
    Color color;

    Button(String text, Color color) {
        this.color = color;
        this.text = text;
        guiButton = new JButton(text);
        guiButton.setBackground(color);
        guiButton.setFont(new Font("Open Sans", Font.BOLD, 20));
        guiButton.setFocusable(false);
        guiButton.setBorder(BorderFactory.createRaisedBevelBorder());
    }
}

