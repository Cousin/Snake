package xyz.betanyan.snakegame;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SnakeObject {

    private List<SnakeBody> tiles;

    public SnakeObject(GameTile head) {
        this.tiles = new ArrayList<>(Collections.singletonList(new SnakeBody(head, 0)));
    }

    /*
        Update snake
     */
    public void update() {

        SnakeBody headBody = tiles.get(tiles.size() - 1);
        GameTile snakeHead = headBody.tile;
        snakeHead.setColor(Color.BLACK);

        // Get the next spot the head will be
        switch (headBody.direction) {
            case 0:
                headBody = new SnakeBody(GameTile.getByCoords(snakeHead.getXCoord() - 1, snakeHead.getYCoord()), headBody.direction);
                break;
            case 1:
                headBody = new SnakeBody(GameTile.getByCoords(snakeHead.getXCoord() + 1, snakeHead.getYCoord()), headBody.direction);
                break;
            case 2:
                headBody = new SnakeBody(GameTile.getByCoords(snakeHead.getXCoord(), snakeHead.getYCoord() + 1), headBody.direction);
                break;
            case 3:
                headBody = new SnakeBody(GameTile.getByCoords(snakeHead.getXCoord(), snakeHead.getYCoord() - 1), headBody.direction);
                break;
        }

        // Set each tile to 1 step ahead
        for (int i=0;i<tiles.size();i++) {
            tiles.get(i).tile.setColor(Color.BLACK);
            if (i != tiles.size() - 1) {
                tiles.set(i, tiles.get(i + 1));
            }
        }

        // Add the snake head
        tiles.set(tiles.size() - 1, headBody);

        // Color each tile
        tiles.stream().map(SnakeBody::getTile)
                .filter(SnakeGame::insideBounds)
                    .forEach(tile -> tile.setColor(Color.GREEN));

    }

    /*
        Add body part to the end of snake, depending on which direction the tail was going.
     */
    public void addBody() {

        GameTile toAdd;
        SnakeBody tailBody = tiles.get(0);
        GameTile tail = tailBody.tile;

        switch (tailBody.direction) {
            case 0:
                toAdd = GameTile.getByCoords(tail.getXCoord() + 1, tail.getYCoord());
                break;
            case 1:
                toAdd = GameTile.getByCoords(tail.getXCoord() - 1, tail.getYCoord());
                break;
            case 2:
                toAdd = GameTile.getByCoords(tail.getXCoord(), tail.getYCoord() - 1);
                break;
            case 3:
                toAdd = GameTile.getByCoords(tail.getXCoord(), tail.getYCoord() + 1);
                break;
            default:
                toAdd = GameTile.getByCoords(tail.getXCoord(), tail.getYCoord() + 1);
                break;
        }

        toAdd.setColor(Color.GREEN);
        tiles.add(0, new SnakeBody(toAdd, tailBody.direction));

    }

    public List<SnakeBody> getTiles() {
        return tiles;
    }

    public GameTile getHead() {
        return tiles.get(tiles.size() - 1).tile;
    }

    public SnakeBody getHeadBody() {
        return tiles.get(tiles.size() - 1);
    }

    public int getSize() {
        return tiles.size();
    }

    /*
        Class encapsulating GameTile and direction it was going.
     */
    public class SnakeBody {

        private GameTile tile;
        private int direction;

        public SnakeBody(GameTile tile, int direction) {
            this.tile = tile;
            this.direction = direction;
        }

        public GameTile getTile() {
            return tile;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "SNAKEBODY[" + tile + direction + "]";
        }
    }

}
