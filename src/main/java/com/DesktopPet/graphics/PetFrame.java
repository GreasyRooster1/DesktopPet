package main.java.com.DesktopPet.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

public class PetFrame extends JFrame {
    private int mouseX,mouseY;
    private static boolean isDraggable = true;
    private JLabel imageLabel;
    private JPopupMenu popup;
    public PetFrame(int width, int height){
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

        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
                if(popup!=null) {
                    showPopup(me, popup); // showPopup() is our own user-defined method
                }
            }
        }) ;
    }

    void setDraggable(boolean a){
        isDraggable = a;
    }

    void setImage(String filePath){
        imageLabel = new JLabel(new ImageIcon(filePath));
        this.add(imageLabel);
    }

    void setPopup(JPopupMenu p){
        popup = p;
    }

    void showPopup(MouseEvent me,JPopupMenu popup) {
        if(me.isPopupTrigger()) {
            popup.show(me.getComponent(), me.getX(), me.getY());
        }
    }

    public static class Builder{
        private int width,height;
        private String imagePath;
        private boolean isDraggable = true;
        private JPopupMenu popup;

        public Builder(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public PetFrame.Builder image(String filepath){
            String filePath = new File("").getAbsolutePath();
            imagePath = filePath.concat(filepath);
            return this;
        }

        public PetFrame.Builder isDraggable(boolean a){
            isDraggable = a;
            return this;
        }

        public PetFrame.Builder setPopupMenu(JPopupMenu jpopup){
            popup = jpopup;
            return this;
        }

        public PetFrame build(){
            PetFrame frame = new PetFrame(width,height);

            if(imagePath!=null){
                frame.setImage(imagePath);
            }
            frame.setPopup(popup);
            frame.setDraggable(isDraggable);

            return frame;
        }
    }
}
