package Sokoban;

import javax.swing.*;

public class Box extends JLabel {
    int x , y;
    ImageIcon imageIcon;

    public Box(ImageIcon imageIcon, int x,int y) {
        super(imageIcon);
        this.x = x;
        this.y = y;
        this.imageIcon = imageIcon;
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        setBounds(x,getY(),imageIcon.getIconWidth(),imageIcon.getIconHeight());
    }

    public void setY(int y) {
        setBounds(getX(),y,imageIcon.getIconWidth(),imageIcon.getIconHeight());
    }
}
