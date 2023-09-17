package java.main.com.WGLib;

import java.main.com.WGLib.graphics.WindowlessFrame;

public class Test {
    public static void main(String[] args) {
        WindowlessFrame frame = new WindowlessFrame.Builder(500,500).image("\\src\\java\\main\\com\\WGLib\\assets\\image.png").build();
    }
}