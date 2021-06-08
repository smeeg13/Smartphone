package Smartphone.Calculatrice;

import Smartphone.ToolBox;
import org.apache.maven.shared.utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculette extends JPanel {

    //Pas plus de 3 chiffres

    ToolBox toolBox = new ToolBox();

    // create a Label
    private JLabel ecran = new JLabel();
    private JLabel ecranResult = new JLabel();

    // create number buttons
    JButton b0 = new JButton("0");
    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");

    // equals button
    JButton begal = new JButton("=");

    // create operator buttons
    JButton baddition = new JButton("+");
    JButton bsoustraction = new JButton("-");
    JButton bdivision = new JButton("/");
    JButton bmultiplication = new JButton("*");
    JButton bpourcent = new JButton("%");
    JButton bsupprimer = new JButton("C");
    JButton bRevenirde1 = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Fleche_Back.png", 20, 20));
    JButton bplusmoins = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_PlusMoins.png", 25, 25));

    // create . button
    JButton bpoint = new JButton(".");

    // create a panel
    JPanel paneltexte = new JPanel();
    JPanel panelCalcul = new JPanel();
    JPanel panelButton = new JPanel();
    JPanel panelResult = new JPanel();

    private String operateur = "";
    private boolean update = false, clickOperateur = false, point = false;
    private double chiffre1;


    // default constructor
    public Calculette() {

        setBackground(Color.lightGray);
        paneltexte.setBackground(Color.lightGray);

        // Mise en page panel calculette
        panelCalcul.setBackground(Color.lightGray);
        panelCalcul.setPreferredSize(new Dimension(189, 40));
        ecran.setBounds(5, 11, 100, 40);
        ecran.setFont(new Font("Tahoma",Font.BOLD,15));
        panelCalcul.add(ecran);

        panelResult.setBackground(Color.lightGray);
        panelResult.setPreferredSize(new Dimension(189,40));
        ecranResult.setBounds(70,11,50,40);
        ecranResult.setFont(new Font("Tahoma",Font.BOLD,15));
        panelResult.add(ecranResult);

        panelButton.setBackground(Color.lightGray);
        panelButton.setLayout(new GridLayout(5, 4, 15, 15));
        panelButton.setPreferredSize(new Dimension(380, 473));


        paneltexte.setLayout(new BoxLayout (paneltexte, BoxLayout.X_AXIS));
        paneltexte.add(panelCalcul);
        paneltexte.add(panelResult);

//Ajout des boutons
        bRevenirde1.setFont(new Font("Tahoma", Font.BOLD, 16));
        bRevenirde1.setBorderPainted(false);
        panelButton.add(bRevenirde1);

        bsupprimer.setFont(new Font("Tahoma", Font.BOLD, 16));
        bsupprimer.setBorderPainted(false);
        panelButton.add(bsupprimer);

        bpourcent.setFont(new Font("Tahoma", Font.BOLD, 16));
        bpourcent.setBorderPainted(false);
        panelButton.add(bpourcent);

        bdivision.setFont(new Font("Tahoma", Font.BOLD, 16));
        bdivision.setBorderPainted(false);
        panelButton.add(bdivision);

        b7.setFont(new Font("Tahoma", Font.BOLD, 16));
        b7.setBorderPainted(false);
        panelButton.add(b7);

        b8.setFont(new Font("Tahoma", Font.BOLD, 16));
        b8.setBorderPainted(false);
        panelButton.add(b8);

        b9.setFont(new Font("Tahoma", Font.BOLD, 16));
        b9.setBorderPainted(false);
        panelButton.add(b9);

        bmultiplication.setFont(new Font("Tahoma", Font.BOLD, 16));
        bmultiplication.setBorderPainted(false);
        panelButton.add(bmultiplication);

        b4.setFont(new Font("Tahoma", Font.BOLD, 16));
        b4.setBorderPainted(false);
        panelButton.add(b4);

        b5.setFont(new Font("Tahoma", Font.BOLD, 16));
        b5.setBorderPainted(false);
        panelButton.add(b5);

        b6.setFont(new Font("Tahoma", Font.BOLD, 16));
        b6.setBorderPainted(false);
        panelButton.add(b6);

        bsoustraction.setFont(new Font("Tahoma", Font.BOLD, 16));
        bsoustraction.setBorderPainted(false);
        panelButton.add(bsoustraction);

        b1.setFont(new Font("Tahoma", Font.BOLD, 16));
        b1.setBorderPainted(false);
        panelButton.add(b1);

        b2.setFont(new Font("Tahoma", Font.BOLD, 16));
        b2.setBorderPainted(false);
        panelButton.add(b2);

        b3.setFont(new Font("Tahoma", Font.BOLD, 16));
        b3.setBorderPainted(false);
        panelButton.add(b3);

        baddition.setFont(new Font("Tahoma", Font.BOLD, 16));
        baddition.setBorderPainted(false);
        panelButton.add(baddition);

        bplusmoins.setFont(new Font("Tahoma", Font.BOLD, 16));
        bplusmoins.setBorderPainted(false);
        panelButton.add(bplusmoins);

        b0.setFont(new Font("Tahoma", Font.BOLD, 16));
        b0.setBorderPainted(false);
        panelButton.add(b0);

        bpoint.setFont(new Font("Tahoma", Font.BOLD, 16));
        bpoint.setBorderPainted(false);
        panelButton.add(bpoint);

        begal.setFont(new Font("Tahoma", Font.BOLD, 16));
        begal.setBorderPainted(false);
        panelButton.add(begal);


        bRevenirde1.addActionListener(new Listener());
        bsupprimer.addActionListener(new Listener());
        bpourcent.addActionListener(new Listener());
        bdivision.addActionListener(new Listener());
        b7.addActionListener(new Listener());
        b8.addActionListener(new Listener());
        b9.addActionListener(new Listener());
        bmultiplication.addActionListener(new Listener());
        b4.addActionListener(new Listener());
        b5.addActionListener(new Listener());
        b6.addActionListener(new Listener());
        bsoustraction.addActionListener(new Listener());
        b1.addActionListener(new Listener());
        b2.addActionListener(new Listener());
        b3.addActionListener(new Listener());
        baddition.addActionListener(new Listener());
        bplusmoins.addActionListener(new Listener());
        b0.addActionListener(new Listener());
        bpoint.addActionListener(new Listener());
        begal.addActionListener(new Listener());

        add(paneltexte);
        add(panelButton);

    }
    class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String command = e.getActionCommand();

            Object source = e.getSource();
            if (bsupprimer.equals(source)) {
                ecran.setText("");
                ecranResult.setText("");
            } else if (bRevenirde1.equals(source)) {
                String str = " " + ecran.getText();
                if (str.length() == 1) {
                    str = ".";
                    ecran.setText(str);
                } else {
                    str = str.substring(0, str.length() - 1);
                    ecran.setText(str);
                }
            } else if (begal.equals(source)) {
                ecranResult.setText(evaluate(ecran.getText()));
            } else if (bplusmoins.equals(source)) {
                command = "(-)";
                ecran.setText("-" + ecran.getText());
            } else {
                ecran.setText(ecran.getText() + command);
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