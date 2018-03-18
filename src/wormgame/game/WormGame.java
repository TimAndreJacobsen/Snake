package wormgame.game;

import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;

    public WormGame(int width, int height) {
        super(1000, null);
        this.width = width;
        this.height = height;
        this.continues = true;
        this.worm = new Worm(width / 2, height / 2, Direction.DOWN);
        do {
            Random rand = new Random();
            this.apple = new Apple(rand.nextInt(width), rand.nextInt(height));
        } while (worm.runsInto(apple));
        addActionListener(this);
        setInitialDelay(2000);
    }

    // Getters
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public Worm getWorm() {
        return this.worm;
    }
    public Apple getApple() {
        return this.apple;
    }

    // Setters
    public void setWorm(Worm worm) {
        this.worm = worm;
    }
    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }
    public void setApple(Apple apple) {
        this.apple = apple;
    }

    // Class Methods
    public boolean continues() {
        return continues;
    }

    private void spawnApple() {
        do {
            Random rand = new Random();
            this.apple = new Apple(rand.nextInt(width), rand.nextInt(height));
        } while (worm.runsInto(apple));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            System.out.println("game ending");
            return;
        }
        worm.move();

        if (worm.runsInto(apple)) {
            worm.grow();
            spawnApple();

        // fail conditions
        } else if (worm.runsIntoItself()) {
            System.out.println("Ran into itself");
            continues = false;

        } else if (worm.getHeadX() == this.width || worm.getHeadY() == this.height) {
            System.out.println("Out of bounds");
            continues = false;
        }
        updatable.update();
        setDelay(1000 / worm.getLength());
    }

}
