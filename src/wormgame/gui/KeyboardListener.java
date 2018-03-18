package wormgame.gui;

import wormgame.Direction;
import wormgame.domain.Worm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private Worm worm;

    public KeyboardListener(Worm worm) {
        this.worm = worm;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (worm.getDirection() == Direction.DOWN) {
                return;
            }
            worm.setDirection(Direction.UP);

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (worm.getDirection() == Direction.UP) {
                return;
            }
            worm.setDirection(Direction.DOWN);

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (worm.getDirection() == Direction.RIGHT) {
                return;
            }
            worm.setDirection(Direction.LEFT);

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (worm.getDirection() == Direction.LEFT) {
                return;
            }
            worm.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
