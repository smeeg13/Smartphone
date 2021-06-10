package Smartphone.Contacts;

import javax.swing.*;
import java.awt.*;

public class PanelContactShow extends JPanel {
    private JPanel PanelBack = new JPanel();
    private JLabel labContactShow = new JLabel("Contact : ");
    private JButton buttonBack2 ;
    private JPanel PanelCentre2 = new JPanel();

    public PanelContactShow(){
        setBackground(Color.pink);
        setPreferredSize(new Dimension(400,435));
    }
}
