package Sokoban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Level extends JPanel {

    int x = 0;
    int y = 0;
    int width, height;

    String level;
    int currentSteps = 0;

    int collidingIndex;

    ImageIcon groundImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/textures/Ground.png")));
    ImageIcon characterImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/textures/Character.png")));
    ImageIcon wallImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/textures/Wall.png")));
    ImageIcon boxImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/textures/Box.png")));
    ImageIcon endpointImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/textures/Endpoint.png")));

    Character character = null;
    ArrayList<Ground> grounds = new ArrayList<>();
    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Box> boxes = new ArrayList<>();
    ArrayList<Endpoint> endpoints = new ArrayList<>();

    public Level(String level) {
        this.level = level;
        width = 64 * (Arrays.asList(level.split("\\|"))).get(0).length();
        height = 64 * (Arrays.asList(level.split("\\|"))).size();
        createLevel(level);
    }

    void createLevel(String level) {

        for (int i = 0; i < level.length(); i++) {
            switch (level.charAt(i)) {
                case 'W':
                    walls.add(new Wall(wallImg, x, y));
                    break;
                case 'B':
                    boxes.add(new Box(boxImg, x, y));
                    grounds.add(new Ground(groundImg, x, y));
                    break;
                case 'E':
                    endpoints.add(new Endpoint(endpointImg, x, y));
                    grounds.add(new Ground(groundImg, x, y));
                    break;
                case 'C':
                    character = new Character(characterImg, x, y);
                    grounds.add(new Ground(groundImg, x, y));
                    break;
                case '|':
                    x = -64;
                    y += 64;
                case 'G':
                    grounds.add(new Ground(groundImg, x, y));
                    break;
                case 'F':
                    boxes.add(new Box(boxImg, x, y));
                    endpoints.add(new Endpoint(endpointImg, x, y));
                    grounds.add(new Ground(groundImg, x, y));
                    break;
                default:
                    break;
            }
            x += 64;
        }

        add(character);
        add(walls);
        add(boxes);
        add(endpoints);
        add(grounds);

        setBounds((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - width) / 2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - height) / 2), width, height);
        setLayout(null);

    }

    private void add(ArrayList<?> list) {
        for (int i = 0; i < list.size(); i++) {
            add((Component) list.get(i));
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> moveCharacter("up");
            case KeyEvent.VK_DOWN -> moveCharacter("down");
            case KeyEvent.VK_LEFT -> moveCharacter("left");
            case KeyEvent.VK_RIGHT -> moveCharacter("right");
            default -> {
            }
        }
    }

    public void moveCharacter(String direction) {

        int currentX = character.getX();
        int currentY = character.getY();
        switch (direction) {
            case "up" -> {
                if (canAvoidWall(currentX, currentY, direction)) {
                    if (boxColliding(currentX, currentY, direction)) {
                        moveBox(direction, collidingIndex);
                    } else {
                        currentSteps++;
                        character.setX(currentX);
                        character.setY(currentY - 64);
                    }
                }
            }
            case "down" -> {
                if (canAvoidWall(currentX, currentY, direction)) {
                    if (boxColliding(currentX, currentY, direction)) {
                        moveBox(direction, collidingIndex);
                    } else {
                        currentSteps++;
                        character.setX(currentX);
                        character.setY(currentY + 64);
                    }
                }
            }
            case "left" -> {
                if (canAvoidWall(currentX, currentY, direction)) {
                    if (boxColliding(currentX, currentY, direction)) {
                        moveBox(direction, collidingIndex);
                    } else {
                        currentSteps++;
                        character.setX(currentX - 64);
                        character.setY(currentY);
                    }
                }
            }
            case "right" -> {
                if (canAvoidWall(currentX, currentY, direction)) {
                    if (boxColliding(currentX, currentY, direction)) {
                        moveBox(direction, collidingIndex);
                    } else {
                        currentSteps++;
                        character.setX(currentX + 64);
                        character.setY(currentY);
                    }
                }
            }
            default -> {
            }
        }

    }

    public boolean canAvoidWall(int currentX, int currentY, String direction) {
        for (int i = 0; i < walls.size(); i++) {
            switch (direction) {
                case "up" -> {
                    if ((currentY - 64) == walls.get(i).getY() && currentX == walls.get(i).getX()) {
                        return false;
                    }
                }
                case "down" -> {
                    if ((currentY + 64) == walls.get(i).getY() && currentX == walls.get(i).getX()) {
                        return false;
                    }
                }
                case "left" -> {
                    if ((currentX - 64) == walls.get(i).getX() && currentY == walls.get(i).getY()) {
                        return false;
                    }
                }
                case "right" -> {
                    if ((currentX + 64) == walls.get(i).getX() && currentY == walls.get(i).getY()) {
                        return false;
                    }
                }
                default -> {
                }
            }
        }
        return true;
    }

    public boolean canAvoidBox(int currentX, int currentY, String direction) {
        for (int i = 0; i < boxes.size(); i++) {
            switch (direction) {
                case "up" -> {
                    if ((currentY - 64) == boxes.get(i).getY() && currentX == boxes.get(i).getX()) {
                        return false;
                    }
                }
                case "down" -> {
                    if ((currentY + 64) == boxes.get(i).getY() && currentX == boxes.get(i).getX()) {
                        return false;
                    }
                }
                case "left" -> {
                    if ((currentX - 64) == boxes.get(i).getX() && currentY == boxes.get(i).getY()) {
                        return false;
                    }
                }
                case "right" -> {
                    if ((currentX + 64) == boxes.get(i).getX() && currentY == boxes.get(i).getY()) {
                        return false;
                    }
                }
                default -> {
                }
            }
        }
        return true;
    }

    public boolean boxColliding(int currentX, int currentY, String direction) {
        for (int i = 0; i < boxes.size(); i++) {
            switch (direction) {
                case "up" -> {
                    if ((currentY - 64) == boxes.get(i).getY() && currentX == boxes.get(i).getX()) {
                        collidingIndex = i;
                        return true;
                    }
                }
                case "down" -> {
                    if ((currentY + 64) == boxes.get(i).getY() && currentX == boxes.get(i).getX()) {
                        collidingIndex = i;
                        return true;
                    }
                }
                case "left" -> {
                    if ((currentX - 64) == boxes.get(i).getX() && currentY == boxes.get(i).getY()) {
                        collidingIndex = i;
                        return true;
                    }
                }
                case "right" -> {
                    if ((currentX + 64) == boxes.get(i).getX() && currentY == boxes.get(i).getY()) {
                        collidingIndex = i;
                        return true;
                    }
                }
                default -> {
                }
            }
        }
        return false;
    }


    public void moveBox(String direction, int collidingIndex) {
        int currentX = boxes.get(collidingIndex).getX();
        int currentY = boxes.get(collidingIndex).getY();

        switch (direction) {
            case "up" -> {
                if (canAvoidWall(currentX, currentY, direction) && canAvoidBox(currentX, currentY, direction)) {
                    boxes.get(collidingIndex).setX(currentX);
                    boxes.get(collidingIndex).setY(currentY - 64);
                    character.setX(currentX);
                    character.setY(currentY);
                    currentSteps++;
                }
            }
            case "down" -> {
                if (canAvoidWall(currentX, currentY, direction) && canAvoidBox(currentX, currentY, direction)) {
                    boxes.get(collidingIndex).setX(currentX);
                    boxes.get(collidingIndex).setY(currentY + 64);
                    character.setX(currentX);
                    character.setY(currentY);
                    currentSteps++;
                }
            }
            case "left" -> {
                if (canAvoidWall(currentX, currentY, direction) && canAvoidBox(currentX, currentY, direction)) {
                    boxes.get(collidingIndex).setX(currentX - 64);
                    boxes.get(collidingIndex).setY(currentY);
                    character.setX(currentX);
                    character.setY(currentY);
                    currentSteps++;
                }
            }
            case "right" -> {
                if (canAvoidWall(currentX, currentY, direction) && canAvoidBox(currentX, currentY, direction)) {
                    boxes.get(collidingIndex).setX(currentX + 64);
                    boxes.get(collidingIndex).setY(currentY);
                    character.setX(currentX);
                    character.setY(currentY);
                    currentSteps++;
                }
            }
            default -> {
            }
        }
    }

    public boolean isFinished() {

        int onEndpoint = 0;

        for (int i = 0; i < boxes.size(); i++) {
            for (int j = 0; j < endpoints.size(); j++) {
                if (boxes.get(i).getX() == endpoints.get(j).getX() && boxes.get(i).getY() == endpoints.get(j).getY()) {
                    onEndpoint++;
                }
            }
        }

        return onEndpoint == boxes.size();

    }
}



