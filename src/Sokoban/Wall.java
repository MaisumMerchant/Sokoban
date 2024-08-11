package Sokoban;

import javax.swing.*;

public class Wall extends JLabel {
    public Wall(ImageIcon imageIcon, int x,int y) {
        super(imageIcon);
        setBounds(x,y,imageIcon.getIconWidth(),imageIcon.getIconHeight());
    }
}
