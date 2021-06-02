package Smartphone.Meteo;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ClickRecherche implements FocusListener {
    private JTextField recherche ;

    public ClickRecherche(JTextField recherche){
        this.recherche = recherche;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(recherche.getText().equals("Recherche")) {
            recherche.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(recherche.getText().equals("")) {
            recherche.setText("Recherche");
        }
        else {
            recherche.setText(recherche.getText());
        }
    }
}
