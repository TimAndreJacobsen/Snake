package wormgame.gui;

import wormgame.domain.Piece;
import wormgame.game.WormGame;

import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel implements Updatable {

    private WormGame wormGame;
    private int pieceLength;

    public DrawingBoard(WormGame wormGame, int pieceLength) {
        this.wormGame = wormGame;
        this.pieceLength = pieceLength; // Size of either height / length
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (Piece p : wormGame.getWorm().getPieces() ) {
            graphics.setColor(Color.BLACK);
            graphics.fill3DRect(p.getX() * pieceLength,p.getY() * pieceLength,pieceLength,pieceLength,true);
        }
        graphics.setColor(Color.RED);
        graphics.fillOval(wormGame.getApple().getX(), wormGame.getApple().getY(), pieceLength, pieceLength);
    }

    @Override
    public void update() {
        super.repaint();
            }
}
