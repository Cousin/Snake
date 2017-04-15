package xyz.betanyan.snakegame;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GameTile extends JPanel {

    /*
        Set of all tiles in the game.
     */
    private static Set<GameTile> tiles = new HashSet<>();

    /*
        Find tile by coords.
     */
    public static GameTile getByCoords(int x, int y) {
        return tiles.stream().filter(tile -> tile.getXCoord() == x && tile.getYCoord() == y)
                .findFirst().orElse(null);
    }

    private Color color;

    private int x;
    private int y;

    public GameTile(Color color, int x, int y) {
        setColor(color);
        this.x = x;
        this.y = y;
        tiles.add(this);
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public int getXCoord() {
        return x;
    }

    public int getYCoord() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GameTile) {
            GameTile tile = (GameTile) obj;
            if (tile.getXCoord() == getXCoord() && tile.getYCoord() == getYCoord()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "GAMETILE[" + getXCoord() + ", " + getYCoord() + "]";
    }

}
