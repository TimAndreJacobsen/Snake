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
        super(500, null);
        this.width = width;
        this.height = height;
        this.continues = true;
        this.worm = new Worm(width / 2, height / 2, Direction.DOWN);
        do {
            Random rand = new Random();
            this.apple = new Apple(rand.nextInt(width), rand.nextInt(height));
        } while (worm.runsInto(apple));
        addActionListener(this);
        setInitialDelay(500);
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

    // REQUIRES: new game, worm eats apple.
    // MODIFIES: WormGame, Apple
    // EFFECTS : Spawns a new apple, checks that it doesn't spawn on the worm
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
        }
        // Fail conditions
        if (worm.runsIntoItself()) {
            System.out.println("Ran into itself");
            continues = false;
        }
        if (worm.getHeadX() == this.width+1 || worm.getHeadY() == this.height+1) {
            System.out.println("Out of bounds");
            continues = false;
        }
        if (worm.getHeadX() == -1 || worm.getHeadY() == -1) {
            System.out.println("Out of bounds");
            continues = false;
        }
        updatable.update();
        setDelay(1000 / worm.getLength());
    }

}
