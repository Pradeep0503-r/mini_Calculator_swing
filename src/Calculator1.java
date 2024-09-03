
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator1 implements ActionListener{

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons=new JButton[10];
    JButton[] functionButtons=new JButton[10];
    JButton addButton,subButton,multiButton,divButton,negButton,percentageButton;

    JButton decButton,equButton,delButton,clrButton;
    JPanel panel;

    //Font myfont=new Font("Ink Free",Font.HANGING_BASELINE,50);
    Font myfont=new Font("red",Font.BOLD ,30);
    double num1=0,num2=0,result=0;
    char operator ='\0';
    boolean errorOccurred = false;

    Calculator1() {

        frame=new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(335, 515);
        //420, 550
        frame.setLocation(380, 60);
        frame.setLayout(null);

        textField=new JTextField();
        textField.setBounds(10, 25, 300, 70);
        textField.setFont(myfont);
        textField.setBackground(Color.white);

        textField.setEditable(false);

        addButton=new JButton("+");
        subButton=new JButton("-");
        multiButton=new JButton("*");
        divButton=new JButton("/");
        decButton=new JButton(".");
        equButton=new JButton("=");
        delButton=new JButton("Dl");
        clrButton=new JButton("clr");
        negButton=new JButton("(-)");
        percentageButton =new JButton("%");



        functionButtons[0]=addButton;
        functionButtons[1]=subButton;
        functionButtons[2]=multiButton;
        functionButtons[3]=divButton;
        functionButtons[4]=clrButton;
        functionButtons[5]=decButton;
        functionButtons[6]=equButton;
        functionButtons[7]=delButton;
        functionButtons[8]=negButton;
        functionButtons[9]=percentageButton;
        for(int i=0;i<10;i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myfont);
            functionButtons[i].setFocusable(false);

        }
        for(int i=0;i<10;i++) {

            numberButtons[i]=new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myfont);
            numberButtons[i].setFocusable(false);

        }
        percentageButton.setBounds(10, 400, 75, 70);
        negButton.setBounds(85, 400, 75, 70);
        delButton.setBounds(160, 400, 75, 70);
        clrButton.setBounds(235, 400, 75, 70);


        panel=new JPanel();
        panel.setBounds(10,100, 300, 300);
        panel.setLayout(new GridLayout(4, 4,2,2));
        panel.setBackground(Color.LIGHT_GRAY);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiButton);

        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        //panel.add(percentageButton);


        frame.add(textField);
        frame.add(panel);
        frame.add(clrButton);
        frame.add(delButton);
        frame.add(textField);
        frame.add(negButton);
        frame.add(percentageButton);

        frame.setVisible(true);
        frame.setBackground(Color.BLUE);
    }

    public static void main(String[] args) {

        new Calculator1();



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (errorOccurred && e.getSource() != clrButton) {
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == multiButton || e.getSource() == divButton || e.getSource() == percentageButton) {
            if (!textField.getText().isEmpty()) {
                if (operator == '\0') {
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText("");
                    operator = ((JButton) e.getSource()).getText().charAt(0);
                } else {
                    num2 = Double.parseDouble(textField.getText());
                    switch (operator) {
                        case '+':
                            num1 += num2;
                            break;
                        case '-':
                            num1 -= num2;
                            break;
                        case '*':
                            num1 *= num2;
                            break;
                        case '/':
                            if (num2 != 0) {
                                num1 /= num2;
                            } else {
                                textField.setText("Error: Division by zero");
                                errorOccurred = true;
                                return;
                            }
                            break;
                        case '%':
                            num1 = num1 * num2 / 100;
                            break;
                    }
                    textField.setText(String.valueOf(num1));
                    operator = ((JButton) e.getSource()).getText().charAt(0);
                    textField.setText("");
                }
            }
        }

        if (e.getSource() == equButton) {
            if (operator != '\0' && !textField.getText().isEmpty()) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textField.setText("Error: Division by zero");
                            errorOccurred = true;
                            return;
                        }
                        break;
                    case '%':
                        result = num1 * num2 / 100;
                        break;
                }
                if (result == (int) result) {
                    textField.setText(String.valueOf((int) result));
                } else {
                    textField.setText(String.valueOf(result));
                }
                operator = '\0';
                num1 = result;
            }
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = '\0';
        }
        if(e.getSource()==delButton) {

            String string=textField.getText();
            textField.setText("");

            for(int i=0;i<string.length()-1;i++) {
                textField.setText(textField.getText()+string.charAt(i));
            }
        }

        if (e.getSource() == negButton) {
            if (!textField.getText().isEmpty()) {
                double temp = Double.parseDouble(textField.getText());
                temp *= -1;
                textField.setText(String.valueOf(temp));
            }
        }

        if (e.getSource() == percentageButton) {
            if (!textField.getText().isEmpty()) {
                double amount = Double.parseDouble(textField.getText());
                String percentageInput = JOptionPane.showInputDialog(frame, "Enter percentage:");
                try {
                    double percentage = Double.parseDouble(percentageInput);
                    double result = 0;
                    if (operator == '\0') {
                        result = amount * percentage / 100;
                    } else {
                        num2 = amount * percentage / 100;
                        switch (operator) {
                            case '+':
                                result = num1 + num2;
                                break;
                            case '-':
                                result = num1 - num2;
                                break;
                            case '*':
                                result = num1 * num2;
                                break;
                            case '/':
                                if (num2 != 0) {
                                    result = num1 / num2;
                                } else {
                                    textField.setText("Error: Division by zero");
                                    errorOccurred = true;
                                    return;
                                }
                                break;
                        }
                    }
                    textField.setText(String.valueOf(result));
                    operator = '\0';
                    num1 = result;
                } catch (NumberFormatException ex) {
                    textField.setText("Invalid percentage!");
                }
            }
        }
    }
}
