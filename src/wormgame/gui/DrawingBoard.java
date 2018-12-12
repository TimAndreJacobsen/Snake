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
        this.pieceLength = pieceLength;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        // Background
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0,0,getWidth(),getHeight());
        // Worm
        for (Piece p : wormGame.getWorm().getPieces() ) {
            graphics.setColor(Color.BLACK);
            graphics.fill3DRect(p.getX() * pieceLength,p.getY() * pieceLength,pieceLength,pieceLength,true);
        }
        //Apples
        graphics.setColor(Color.RED);
        graphics.fillOval(wormGame.getApple().getX() * pieceLength, wormGame.getApple().getY() * pieceLength, pieceLength, pieceLength);
    }

    @Override
    public void update() {
        super.repaint();
            }
}
