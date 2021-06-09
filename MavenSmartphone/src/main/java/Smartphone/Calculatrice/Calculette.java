package Smartphone.Calculatrice;

import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculette extends JPanel {

    //Pas plus de 3 chiffres

    ToolBox toolBox = new ToolBox();

    // create a panel
    private  JPanel mainpanel = new JPanel();
    private  JPanel paneltexte = new JPanel();
    private JPanel panelButton = new JPanel();

    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[10];
    JButton addBut, subBut, mulBut,divBut;
    JButton decBut,percBut, negBut, equBut, delBut, clrBut ;

    Font myFont = new Font("Tahoma", Font.BOLD, 16);

    double num1 = 0, num2 = 0,result = 0;
    char operator;

    // default constructor
    public Calculette() {

        setBackground(Color.lightGray);

        mainpanel.setLayout(new BoxLayout (mainpanel, BoxLayout.Y_AXIS));
        mainpanel.setBackground(Color.lightGray);
        mainpanel.setPreferredSize(new Dimension(400,533));


        paneltexte.setBackground(Color.lightGray);
        paneltexte.setLayout(null);
        panelButton.setPreferredSize(new Dimension(350,350));
        panelButton.setBackground(Color.lightGray);
        panelButton.setLayout(new GridLayout(5, 4, 13, 13));

        textField = new JTextField();
        textField.setBounds(50,20,300,45);

        textField.setFont(new Font("Tahoma", Font.BOLD, 18));
        textField.setEditable(false);
        paneltexte.add(textField);

        addBut = new JButton("+");
        subBut = new JButton("-");
        mulBut = new JButton("*");
        divBut = new JButton("/");
        decBut = new JButton(".");
        percBut = new JButton("%");
        negBut = new JButton("+/-");
        equBut = new JButton("=");
        delBut = new JButton("Del");
        clrBut = new JButton("C");

        functionButtons[0] = addBut;
        functionButtons[1] = subBut;
        functionButtons[2] = mulBut;
        functionButtons[3] = divBut;
        functionButtons[4] = decBut;
        functionButtons[5] = percBut;
        functionButtons[6] = negBut;
        functionButtons[7] = equBut;
        functionButtons[8] = delBut;
        functionButtons[9] = clrBut;

        for (int i = 0 ; i < 10 ; i++){
            functionButtons[i].addActionListener(new Listener());
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0 ; i < 10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new Listener());
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        panelButton.add(delBut);
        panelButton.add(clrBut);
        panelButton.add(percBut);
        panelButton.add(divBut);

        panelButton.add(numberButtons[7]);
        panelButton.add(numberButtons[8]);
        panelButton.add(numberButtons[9]);
        panelButton.add(mulBut);

        panelButton.add(numberButtons[4]);
        panelButton.add(numberButtons[5]);
        panelButton.add(numberButtons[6]);
        panelButton.add(subBut);

        panelButton.add(numberButtons[1]);
        panelButton.add(numberButtons[2]);
        panelButton.add(numberButtons[3]);
        panelButton.add(addBut);

        panelButton.add(negBut);
        panelButton.add(numberButtons[0]);
        panelButton.add(decBut);
        panelButton.add(equBut);

        mainpanel.add(paneltexte);
        mainpanel.add(panelButton);

        add(mainpanel);
    }
    class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButtons[i]) {
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }
            if (e.getSource() == decBut) {
                textField.setText(textField.getText().concat("."));
            }
            if (e.getSource() == addBut) {
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            }
            if (e.getSource() == subBut) {
                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
            }
            if (e.getSource() == mulBut) {
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }
            if (e.getSource() == divBut) {
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }
            if (e.getSource() == equBut){
                num2 = Double.parseDouble(textField.getText());

                switch (operator){
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
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
                num1 = result;
            }
            if (e.getSource() == clrBut) {
                textField.setText("");
            }
            if (e.getSource() == delBut) {
                String str = textField.getText();
                textField.setText("");
                for (int i = 0; i<str.length()-1; i++){
                    textField.setText(textField.getText()+str.charAt(i));
                }
            }
            if (e.getSource() == negBut) {
                double temp = Double.parseDouble(textField.getText());
                temp*=-1;
                textField.setText(String.valueOf(temp));
            }
            if (e.getSource() == percBut) {
                double temp = Double.parseDouble(textField.getText());
                temp/=100;
                textField.setText(String.valueOf(temp));
            }
        }
    }

    public static String evaluate(String expression) {
        char[] arr = expression.toCharArray();
        String operand1 = "";String operand2 = "";String operator = "";
        double result = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= '0' && arr[i] <= '9' || arr[i] == '.') {
                if(operator.isEmpty()){
                    operand1 += arr[i];
                }else{
                    operand2 += arr[i];
                }
            }

            if(arr[i] == '+' || arr[i] == '-' || arr[i] == '/' || arr[i] == '*' || arr[i] =='%') {
                operator += arr[i];
            }
        }

        if (operator.equals("+"))
            result = (Double.parseDouble(operand1) + Double.parseDouble(operand2));
        else if (operator.equals("-"))
            result = (Double.parseDouble(operand1) - Double.parseDouble(operand2));
        else if (operator.equals("/"))
            result = (Double.parseDouble(operand1) / Double.parseDouble(operand2));
        else if(operator.equals("*"))
            result = (Double.parseDouble(operand1) * Double.parseDouble(operand2));
        else
            result = (Double.parseDouble(operand1) / 100);
        return "= " + result;
    }
}