package Smartphone.Contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdd extends JPanel {
    private JPanel panel = new JPanel();

    private JButton buttonPicture = new JButton();
    private JButton buttonOk = new JButton();

    private  JLabel nomLab =new JLabel("Nom : ");
    private  JTextField nameTxt= new JTextField(30);
    private  JLabel indicatifLab = new JLabel("Indicatif : ");
    private  JComboBox  indicChoices= new JComboBox( new Object[]{"+1   ","+32   ","+33   ","+41   ","+44   ","+49   "});
    private  JLabel numeroLab = new JLabel("Numéro :  ");
    private JTextField numTxt = new JTextField(10);
    private JLabel emailLab = new JLabel("Email :  ");
    private JTextField emailTxt = new JTextField(45);
    private JLabel adresseLab = new JLabel("Addresse :  ");
    private JTextField adresseTxt = new JTextField(45);

    public PanelAdd(){
        setBackground(Color.cyan);
        panel.setBackground(Color.orange);
        JLabel lab = new JLabel("Contact adding");
        panel.add(lab);
        panel.add(buttonOk, BorderLayout.SOUTH);
        panel.add(buttonPicture,BorderLayout.NORTH);

        buttonOk.addActionListener(new Listener());
        add(panel);
    }

    class Listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String nom = nameTxt.getText();
            String num = numTxt.getText();
            //...
        }
    }
}
