package Smartphone.Contacts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Contacts extends JPanel implements ActionListener {

    private JPanel panelHaut = new JPanel();
    private JButton bAjouter = new JButton("Ajouter");
    private JButton bRechercher = new JButton("Rechercher");

    private JPanel panelCentre = new JPanel();
    private List contactsList = new List();


    private JButton bSupprimer = new JButton("Supprimer");



    public void load(String json) {

    }

    public String save(){

        String ab="abc";
        return ab;
    }

    public Contacts() {

        //Design page contact
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        add(panelHaut);
        add(panelCentre);
        panelHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.Y_AXIS));

        //Ajouter les boutons
        panelHaut.add(bAjouter);
        panelHaut.add(bRechercher);

        add(bSupprimer); //Ajouter boutton quand séléctionne un contact

        //Ajouter les actions des boutons
        bAjouter.addActionListener(this);
        bSupprimer.addActionListener(this);
        bRechercher.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bAjouter) {
        }
        if (e.getSource() == bSupprimer){
        }
        if (e.getSource() == bRechercher){

        }
    }

}
