package Sokoban;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class Sokoban extends JFrame {

    Level gameLevel;
    LevelCount levelCount;
    Steps steps;
    ArrayList<String> levels = new ArrayList<>();
    int currentLevel = 0;

    public Sokoban() {
        try {
            File levelFile = new File("levels.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(levelFile));
            String level;
            while ((level = bufferedReader.readLine()) != null) {
                levels.add(level);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GUI();
    }

    public static void main(String[] args) {
        new Sokoban();
    }

    public void end() {
        Timer t = new Timer(1000,
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );
        t.setRepeats(false);
        t.start();
    }

    public void GUI() {

        Controls controls = new Controls();
        add(controls);

        levelCount = new LevelCount(currentLevel);
        add(levelCount);


        gameLevel = new Level(levels.get(currentLevel));
        add(gameLevel);


        steps = new Steps(gameLevel.currentSteps);
        add(steps);

        setLayout(null);
        setTitle("Sokoban");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    remove(gameLevel);
                    gameLevel = new Level(levels.get(currentLevel));
                    add(gameLevel);
                    revalidate();
                    repaint();
                }
                gameLevel.keyPressed(e);
                remove(steps);
                steps = new Steps(gameLevel.currentSteps);
                add(steps);
                revalidate();
                repaint();
                if (gameLevel.isFinished()) {
                    currentLevel++;
                    remove(gameLevel);
                    if (currentLevel >= levels.size()) {
                        gameLevel = new Level("B   B BBBBB B   B|B   B   B   BB  B|B B B   B   B B B|BB BB   B   B  BB|B   B BBBBB B   BC");
                        add(gameLevel);
                        remove(controls);
                        remove(levelCount);
                        remove(steps);
                        revalidate();
                        repaint();
                        end();
                    } else {
                        gameLevel = new Level(levels.get(currentLevel));
                        add(gameLevel);
                        remove(levelCount);
                        levelCount = new LevelCount(currentLevel);
                        add(levelCount);
                        remove(steps);
                        steps = new Steps(gameLevel.currentSteps);
                        add(steps);
                        revalidate();
                        repaint();
                    }
                }
            }
        });

    }

}
