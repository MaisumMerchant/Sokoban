package Sokoban;

import javax.swing.*;
import java.awt.*;

public class LevelCount extends JPanel {

    JLabel text = new JLabel();

    public LevelCount(int currentLevel) {
        text.setText("Level: " + (currentLevel + 1));
        add(text);
        text.setFont(new Font("Arial", Font.BOLD, 30));
        setBounds((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - text.getPreferredSize().getWidth()) / 2), 10, (int) text.getPreferredSize().getWidth(), (int) text.getPreferredSize().getHeight() + 10);
    }
}
