package Sokoban;

import javax.swing.*;
import java.awt.*;

public class Steps extends JPanel {

    JLabel text = new JLabel();

    public Steps(int Steps) {
        text.setText("Steps: " + (Steps));
        add(text);
        text.setFont(new Font("Arial", Font.BOLD, 30));
        setBounds((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 10 - text.getPreferredSize().getWidth())), 10, (int) text.getPreferredSize().getWidth(), (int) text.getPreferredSize().getHeight() + 10);
    }

}
