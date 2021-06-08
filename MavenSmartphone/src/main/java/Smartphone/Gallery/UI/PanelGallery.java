package Smartphone.Gallery.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGallery extends JPanel {

    private CardLayout screen = new CardLayout();

    private JPanel mainPanel = new JPanel();

    private JPanel galleryPanel = new JPanel();
        private JPanel upGallery = new JPanel();
            private JButton add;
            private JButton select;

        private JPanel midGallery = new JPanel();
            private GridLayout showContents;

        private JPanel selectPanel = new JPanel(); //south
            private JLabel selected = new JLabel();
            private JButton all = new JButton();

    private JPanel albumPanel = new JPanel();
        private JPanel upAlbum = new JPanel();
            private JLabel name = new JLabel();
            private JButton back = new JButton();
            private JButton delete = new JButton();
            private JButton rename = new JButton();

        private JPanel renamePanel = new JPanel();
            private JTextField newName = new JTextField();
            private JButton validate = new JButton();
            private JButton cancel = new JButton();

        private JPanel deletePanel = new JPanel();
            private JButton confirm = new JButton();

    private JPanel picturePanel = new JPanel();
        private JPanel upPicture = new JPanel();
        private JPanel midPicture = new JPanel();

    private JPanel addPanel = new JPanel();
        private JPanel midAdd = new JPanel();
            private JButton picture = new JButton();
            private JButton album = new JButton();

    public PanelGallery(){
        mainPanel.setPreferredSize(new Dimension(400,650));
        mainPanel.setLayout(screen);

        mainPanel.add(galleryPanel,"galleryPanel");
        mainPanel.add(albumPanel,"albumPanel");
        mainPanel.add(picturePanel,"picturePanel");
        mainPanel.add(addPanel,"addPanel");

        galleryPanel.add(upGallery,BorderLayout.NORTH);
        upGallery.setPreferredSize(new Dimension(400,40));
        upGallery.setLayout(new FlowLayout(FlowLayout.RIGHT));

        add = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Plus.png",15,15));
            add.setBorderPainted(false);
            add.setFocusPainted(false);
            add.setContentAreaFilled(false);
            add.addActionListener(new ActionsGallery());

        select = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_select.png",15,15));
            select.setBorderPainted(false);
            select.setFocusPainted(false);
            select.setContentAreaFilled(false);
            select.addActionListener(new ActionsGallery());

        upGallery.add(add);
        upGallery.add(select);

    }

    class ActionsGallery implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource()== add) screen.show(mainPanel,"addPanel");
        }
    }


    public ImageIcon addImageIconJButton(String path,int width, int height){
        ImageIcon imageSearch;
        imageSearch = new ImageIcon(path);
        Image imagetest = imageSearch.getImage();
        Image newTest = imagetest.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return imageSearch = new ImageIcon(newTest);
    }
}
