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

public class Functions {
    private final int BUTTON_WIDTH = 90, BUTTON_HEIGHT = 65;
    private final int MAX_CHARACTERS = 15;

    private Button[] buttons = Button.values();
    private JButton[] guiButtons = new JButton[Button.values().length];

    private JLabel label;

    private DecimalFormat decimalFormat = new DecimalFormat("#,###.##########");
    
    private double firstOperand = -1, secondOperand = -1;
    private char operator = 0;
    private boolean isOperatorUsed = false;
    private boolean isEqualsUsed = false;
    private boolean isError = false;
    private boolean isDecimal = false;

    public Functions(JLabel label) {
        label.setText("0");
        this.label = label;
    }

    public JButton[] getButtons(int verticalGap) {
        for(int i = 0; i < Button.values().length; i++) {
            guiButtons[i] = buttons[i].guiButton;
            guiButtons[i].addActionListener(listener);
            if(buttons[i] == Button.EQUALS)
                guiButtons[i].setPreferredSize(new Dimension((BUTTON_WIDTH*2)+5, BUTTON_HEIGHT));
            else
                guiButtons[i].setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        }
        
        return guiButtons;
        
    }

    private ActionListener listener = (e) -> {
        for (Button button : buttons) {
            if(e.getSource() == button.guiButton) {
                try {
                    checkButton(button);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    };

    private void checkButton(Button button) throws InterruptedException {
        
        this.setTextWithDelayEffect(label.getText());

        switch(button) {
            case Button.POINT: case Button.NUMBER_0: 
            case Button.NUMBER_1: case Button.NUMBER_2: case Button.NUMBER_3: 
            case Button.NUMBER_4: case Button.NUMBER_5: case Button.NUMBER_6: 
            case Button.NUMBER_7: case Button.NUMBER_8: case Button.NUMBER_9: 
                if(label.getText().length() == 1 && label.getText().charAt(0) == '0' && button != Button.POINT)
                    label.setText("");
                if(isOperatorUsed || isEqualsUsed || isError) {
                    label.setText("");
                    isOperatorUsed = false;
                    isEqualsUsed = false;
                    isError = false;
                    isDecimal = false;
                    if(button == Button.POINT) {
                        label.setText(label.getText().concat("0"));
                    }
                }

                if(button != Button.POINT)
                    label.setText(decimalFormat.format(Double.parseDouble(label.getText().concat(button.text).replace(",", ""))));

                if(button == Button.POINT && !isDecimal) {
                    label.setText(label.getText().concat("."));
                    isDecimal = true;
                }

                if(label.getText().length() > MAX_CHARACTERS) {
                    label.setText(decimalFormat.format(Double.parseDouble(label.getText().replace(",", "").substring(0, MAX_CHARACTERS-3))));
                }   

                break;
                
            case Button.DELETE: 
                if(label.getText().endsWith(".")) 
                    isDecimal = false;


                label.setText(label.getText().substring(0, label.getText().length() - 1));
                
                if(label.getText().equals(""))
                    label.setText("0");
                break;

            case Button.ALL_CLEAR: 
                firstOperand = secondOperand = -1;
                operator = 0;
                isDecimal = false;
                label.setText("0");
                break; 

            case Button.PLUS: case Button.MINUS: case Button.TIMES: case Button.DIVIDE: case Button.MODULUS:
                if (operator != 0 && isOperatorUsed) {
                    operator = button.text.charAt(0);  
                    isOperatorUsed = false;
                }
                else if(operator == 0 && !isOperatorUsed && !isError) {
                    if(label.getText().charAt(label.getText().length() - 1) == '.')
                        label.setText(label.getText().replace(".", ""));

                    firstOperand = Double.parseDouble(label.getText().replace(",", ""));
                    operator = button.text.charAt(0);   
                }
                else if(secondOperand == -1 && !isOperatorUsed && !isError) {
                    secondOperand = Double.parseDouble(label.getText().replace(",", ""));
                    if(secondOperand == 0) {
                        calculatorError();
                        return;
                    }

                    firstOperand = calculate(firstOperand, secondOperand, operator);
                    operator = button.text.charAt(0);
                    secondOperand = -1;
                    
                    label.setText(decimalFormat.format(firstOperand));
                    limitCharacters(MAX_CHARACTERS, label.getText());
                }
                isOperatorUsed = true;
                
                break;

            case Button.EQUALS:
                if(operator != 0) {
                    secondOperand = Double.parseDouble(label.getText().replace(",", ""));
                    if(secondOperand == 0) {
                        calculatorError();
                        return;
                    }

                    label.setText(decimalFormat.format(calculate(firstOperand, secondOperand, operator)));
                    limitCharacters(MAX_CHARACTERS, label.getText());
                    firstOperand = secondOperand = -1;
                    operator = 0;
                    isEqualsUsed = true;
                }
        }
    }

    private double calculate(double firstOperand, double secondOperand, char operator) {
        switch(operator) {
            case '+':
                return firstOperand + secondOperand;
            case '-':
                if(firstOperand < secondOperand)
                    return 0;
                return firstOperand - secondOperand; 
            case '*':
                return firstOperand * secondOperand; 
            case '/':
                return firstOperand / secondOperand; 
            case '%':
                return firstOperand % secondOperand; 
        }
        return -1;
    }

    private void setTextWithDelayEffect(String text) throws InterruptedException {
        label.setForeground(Color.GRAY);;
        label.setText(text);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {                
                label.setForeground(Color.WHITE);
            }
        };
        timer.schedule(task, 100);
    }

    private void limitCharacters(int size, String text) {
        double pointedValue = Double.parseDouble(text.replace(",", ""));
        if(text.length() > size) {
            if((pointedValue - (long)pointedValue) == 0)  
                calculatorError();
            else
                label.setText(decimalFormat.format(Double.parseDouble(label.getText().replace(",", "").substring(0, size-3))));;
        }
    }

    private void calculatorError() {
        label.setText("ERROR");
        firstOperand = -1;
        operator = 0;
        secondOperand = -1;
        isError = true;
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

