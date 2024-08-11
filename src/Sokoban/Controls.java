package Sokoban;

import javax.swing.*;
import java.awt.*;

public class Controls extends JPanel {

    JLabel text = new JLabel();

    public Controls() {
        text.setText("<html> Controls: <br/>" +
                "Press Up Arrow To Move Up <br/>" +
                "Press Down Arrow To Move Down <br/>" +
                "Press Right Arrow To Move Right <br/>" +
                "Press Left Arrow To Move Left <br/>" +
                "Press R To Reset <br/>" +
                "Enjoy :) <br/><br/>" +
                "</html>");
        text.setFont(new Font("Arial", Font.BOLD, 20));
        add(text);
        setBounds(10, 10, (int) text.getPreferredSize().getWidth(), (int) text.getPreferredSize().getHeight());
    }

}
