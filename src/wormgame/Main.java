package wormgame;

import wormgame.game.WormGame;
import wormgame.gui.UserInterface;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        WormGame game = new WormGame(20, 20);
        UserInterface ui = new UserInterface(game, 20);
        SwingUtilities.invokeLater(ui);

        while (ui.getUpdatable() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("relaunching");
            }
        }
        game.setUpdatable(ui.getUpdatable());
        game.start();
    }
}
