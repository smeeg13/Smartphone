package Smartphone.Calculator;

import Smartphone.Errors.BusinessException;
import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class provides the code for creating calculator and displaying it.
 *
 * @author Mégane Solliard
 * @version
 */
public class Calculator extends JPanel {
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[10];
    JButton addBut, subBut, mulBut,divBut;
    JButton decBut,percBut, negBut, equBut, delBut, clrBut ;
    Font myFont = new Font("Tahoma", Font.BOLD, 16);

    double num1 = 0, num2 = 0,result = 0;
    char operator;

    /**
     * This constructor provides the calculator display.
     */
    public Calculator() {

        setBackground(Color.lightGray);

        // create a panel
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout (mainpanel, BoxLayout.Y_AXIS));
        mainpanel.setBackground(Color.lightGray);
        mainpanel.setPreferredSize(new Dimension(400,533));

        JPanel paneltexte = new JPanel();
        paneltexte.setBackground(Color.lightGray);
        paneltexte.setLayout(null);
        JPanel panelButton = new JPanel();
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
    /**
     * This class is used to give actionListener to all the JButtons
     */
    class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //Set the number of the button in the textField above
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButtons[i]) {
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }

            //Set a point after the text already in the textField above
            if (e.getSource() == decBut) {
                textField.setText(textField.getText().concat("."));
            }

            //Take what is inside the textField and put it into an double num1
            //And set the operator with + before reset the textField
            if (e.getSource() == addBut) {
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            }

            //Take what is inside the textField and put it into an double num1
            //And set the operator with - before reset the textField
            if (e.getSource() == subBut) {
                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
            }

            //Take what is inside the textField and put it into an double num1
            //And set the operator with * before reset the textField
            if (e.getSource() == mulBut) {
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }

            //Take what is inside the textField and put it into an double num1
            //And set the operator with / before reset the textField
            if (e.getSource() == divBut) {
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }

            //Take what is inside the textField and put it into an double num2
            //Depending on which operator has been set, the calcul will be done
            //And the result of this will be put into the num1 and displayed in the textField
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

            //Reset the textField
            if (e.getSource() == clrBut) {
                textField.setText("");
            }

            //Delete the last char of the textField
            if (e.getSource() == delBut) {
                String str = textField.getText();
                textField.setText("");
                for (int i = 0; i<str.length()-1; i++){
                    textField.setText(textField.getText()+str.charAt(i));
                }
            }

            //Take the text of the textField and put it into a double temp
            //Then put the temp negative and displayed it in the textField
            if (e.getSource() == negBut) {
                double temp = Double.parseDouble(textField.getText());
                temp*=-1;
                textField.setText(String.valueOf(temp));
            }

            //Take the text of the textField and put it into a double temp
            //Then divide the temp by 100 and displayed it in the textField
            if (e.getSource() == percBut) {
                double temp = Double.parseDouble(textField.getText());
                temp/=100;
                textField.setText(String.valueOf(temp));
            }
        }
    }
}