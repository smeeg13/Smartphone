package Smartphone;

import Smartphone.Errors.BusinessException;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws BusinessException {

        JFrame frame = new StructureFrame();

        frame.setSize(400,650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
