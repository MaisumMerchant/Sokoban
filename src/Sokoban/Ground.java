package Sokoban;

import javax.swing.*;

public class Ground extends JLabel {
    public Ground(ImageIcon imageIcon, int x,int y) {
        super(imageIcon);
        setBounds(x,y,imageIcon.getIconWidth(),imageIcon.getIconHeight());
    }
}
