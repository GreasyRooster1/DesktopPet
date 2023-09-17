package java.main.com.WGLib.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

public class WindowlessFrame extends JFrame {
    private int mouseX,mouseY;
    private static boolean isDraggable = true;
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

    void setDraggable(boolean a){
        isDraggable = a;
    }
    void setImage(String filePath){
        JLabel imageLabel = new JLabel(new ImageIcon(filePath));
        this.add(imageLabel);
    }

    public static class Builder{
        private int width,height;
        private String imagePath;
        private boolean isDraggable = true;

        public Builder(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public WindowlessFrame.Builder image(String filepath){
            String imagePath = new File("").getAbsolutePath();
            imagePath = imagePath.concat(filepath);
            return this;
        }

        public WindowlessFrame.Builder isDraggable(boolean a){
            isDraggable = a;
            return this;
        }

        public WindowlessFrame build(){
            WindowlessFrame frame = new WindowlessFrame(width,height);

            if(imagePath!=null){
                frame.setImage(imagePath);
            }
            frame.setDraggable(isDraggable);

            return frame;
        }
    }
}
