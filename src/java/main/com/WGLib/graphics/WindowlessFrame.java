package java.main.com.WGLib.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

public class WindowlessFrame extends JFrame {
    private int mouseX,mouseY;
    private boolean isDraggable = true;
    public WindowlessFrame(int width,int height){
        super();
        this.setSize(width, height);
        this.setLocation(50, 50);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0)); // this is the critical line - that fourth 0 represents alpha (or opacity)

        setAlwaysOnTop( true );  // keeps it in the foreground, so you don't click away from it - note that clicks on the transparent part DO pass through to the desktop, at least on Lion

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(isDraggable) {
                    setLocation(getX() + e.getX() - mouseX, getY() + e.getY() - mouseY);
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(isDraggable) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
            }
        });
    }
    void setImage(String filePath){
        JLabel imageLabel = new JLabel(new ImageIcon(filePath));
        this.add(imageLabel);
    }

    static class Builder{
        Builder(){

        }
    }
}
