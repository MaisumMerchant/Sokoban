package Sokoban;

import javax.swing.*;

public class Endpoint extends JLabel {
    public Endpoint(ImageIcon imageIcon, int x,int y) {
        super(imageIcon);
        setBounds(x,y,imageIcon.getIconWidth(),imageIcon.getIconHeight());
    }
}
