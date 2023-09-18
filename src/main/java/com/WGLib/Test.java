package main.java.com.WGLib;

import main.java.com.WGLib.graphics.WindowlessFrame;

public class Test {
    public static void main(String[] args) {
        WindowlessFrame frame = new WindowlessFrame.Builder(500,500).image("\\src\\main\\java\\com\\WGLib\\assets\\image.png").build();
        frame.setVisible(true);
    }
}