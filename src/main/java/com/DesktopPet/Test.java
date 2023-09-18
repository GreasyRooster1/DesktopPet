package main.java.com.DesktopPet;

import main.java.com.DesktopPet.graphics.PetFrame;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("Test"));
        PetFrame frame = new PetFrame.Builder(500,500).image("\\src\\main\\java\\com\\WGLib\\assets\\image.png").setPopupMenu(popupMenu).build();
        frame.setVisible(true);
    }
}