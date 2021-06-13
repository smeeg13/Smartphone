package Smartphone.Meteo;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * This class is used for the Foculistener of the searchBar
 * @author Thomas Cheseaux
 */

public class ClickRecherche implements FocusListener {
    private final JTextField recherche ;

    /**
     * this constructor create a textfield where you can enter the locality
     * @param recherche JTextfield for the high panel
     */
    public ClickRecherche(JTextField recherche){
        this.recherche = recherche;
    }

    /**
     * this method is invoked when a component gains the keyboard focus
     * @param e is FocusEvent an object for the focus on the JTextfield
     */
    @Override
    public void focusGained(FocusEvent e) {
        if(recherche.getText().equals("Recherche")) {
            recherche.setText("");
        }
    }

    /**
     * this method is invoked when a component loses the keyboard focus
     * @param e is FocusEvent an object for the focus on the JTextfield
     */
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
