package Smartphone.Contacts;

import javax.swing.*;

public class PanelContactSelected extends JPanel {



    public PanelContactSelected() {

    }
    /*
    public void SetInfoContactPanel(Contact contactSelec){
        // Si le contact n'existe pas, ne rien mettre à jour
        if (contactSelec == null) return;
        //Ajouter les infos du contact sélectionné à la page d'info du contact
        nameTxtContSelec.setText(contactSelec.getName());
        indicChoiceContSelec.setText(contactSelec.getIndicatif());
        numTxtContSelec.setText(contactSelec.getPhoneNumber());
        emailTxtContSelec.setText(contactSelec.getEmail());
        adresseTxtContSelec.setText(contactSelec.getAddress());

        if (contactSelec.isAddphoto() == false) {
            buttonPicturePContactAdd.setIcon(contactSelec.getPhoto());
            buttonPictureContSelec.setText("");
        } else
            //Reprendre photo du contact si existe
            //    buttonPictureContSelec.setIcon(contactSelec.getPhoto());
            buttonPictureContSelec.setText(" ");

        if (contactSelec.isFavContact() == true)
            favCheckBoxContSelec.setSelected(true);
        else
            favCheckBoxContSelec.setSelected(false);
    }
     */
}
