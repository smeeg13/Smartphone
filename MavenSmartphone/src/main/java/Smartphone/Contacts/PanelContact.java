package Smartphone.Contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelContact extends JPanel  {

    private Icon iconPlus = new ImageIcon("icone_Plus.png");
    private Icon iconSearch = new ImageIcon("icone_Recherche.png");
    private Icon iconBack = new ImageIcon("icone_Back.png");


    private CardLayout ecran = new CardLayout(75,235);

    private JPanel base = new JPanel();
    private JPanel haut = new JPanel();
    private JPanel bas = new JPanel();

    private JPanel contact = new JPanel();
    private JPanel contactAdd = new PanelAdd();
    private JPanel contactSearch = new PanelSearch();


    private JButton buttonAdd = new JButton("",iconPlus);
    private JButton buttonSearch = new JButton("",iconSearch);
    private JButton buttonBack = new JButton("",iconBack);
    private JButton buttonBack2 = new JButton("",iconBack);

    private JLabel label =new JLabel("Contact List : ");

    //Limité à 200 contacts pour le test
    static  final  int MAX_CONTACTS = 200;
    private Contact[] contacts = new Contact[MAX_CONTACTS];

    // private JList<Contact> contactList =new JList<Contact>(data);


    public void load(String json) {

    }

    public String save(){

        String ab="abc";
        return ab;
    }

    public PanelContact() {

        setBackground(Color.RED);

        base.setBackground(Color.yellow);
        Dimension tailleHaut = new Dimension(370,50);

        haut.setPreferredSize(tailleHaut);

        base.setLayout(ecran);

        base.add(contact,"Contact");
        base.add(contactAdd,"Ajout Contact");
        base.add(contactSearch, "Recherche Contact");

        contact.setLayout(new FlowLayout(50,20,20));


        contact.add(buttonAdd,BorderLayout.NORTH);
        contact.add(buttonSearch,BorderLayout.CENTER);

        contact.add(label,BorderLayout.SOUTH);


        contactAdd.add(buttonBack2);
        contactSearch.add(buttonBack);



        buttonBack.addActionListener(new Actions());
        buttonBack2.addActionListener(new Actions());
        buttonAdd.addActionListener(new Actions());
        buttonSearch.addActionListener(new Actions());

        add(base,BorderLayout .CENTER);
    }

    class Actions implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonAdd){
                ecran.show(base,"Ajout Contact");
            }
            if (e.getSource() == buttonSearch){
                ecran.show(base,"Recherche Contact");
            }
            if ((e.getSource() == buttonBack) || (e.getSource()==buttonBack2)){
                ecran.show(base,"Contact");
            }

        }
    }
}

