package xyz.betanyan.snakegame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SnakeGame extends JFrame {

    public static final int TILE_ROWS = 24;
    public static final int TILE_COLS = 24;

    public static void main(String[] args) throws IOException {

        new SnakeGame();

    }

    public SnakeGame() {

        Random random = ThreadLocalRandom.current();

        // Setup frame
        setTitle("Snake in java");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(TILE_ROWS, TILE_COLS));
        getContentPane().setBackground(Color.GREEN);
        setSize(630, 629);
        setResizable(false);

        // Load all the GameTiles dynamically
        for(int x = 0; x < TILE_COLS; x++) {
            for(int y = 0; y < TILE_ROWS; y++) {
                getContentPane().add(new GameTile(Color.BLACK, y, x));
            }
        }

        // Choose random location for the snake to spawn
        SnakeObject snake = new SnakeObject(GameTile.getByCoords(random.nextInt(TILE_ROWS), random.nextInt(TILE_COLS)));
        snake.getHead().setColor(Color.GREEN);

        // Choose random location for the food to spawn
        GameTile food = getRandomFoodTile();
        food.setColor(Color.RED);

        // Start runnables and listeners
        GameRunnable gameRunnable = new GameRunnable(this, snake, food);
        addKeyListener(new MoveListener(snake, gameRunnable));
        setVisible(true);

        new Thread(gameRunnable).start();

    }

    /*
        Recursively find tile for food to be placed.
    */
    public GameTile getRandomFoodTile() {
        Random random = ThreadLocalRandom.current();
        GameTile found = GameTile.getByCoords(random.nextInt(TILE_COLS), random.nextInt(TILE_ROWS));
        if (found.getColor() == Color.BLACK) {
            return found;
        }

        return getRandomFoodTile();
    }

    /*
        Check if tile is inside window.
     */
    public static boolean insideBounds(GameTile tile) {

        if (tile == null) {
            return false;
        } else {

            int x = tile.getXCoord();
            int y = tile.getYCoord();

            return !(x < 0 || y < 0) && !(x > TILE_COLS || y > TILE_ROWS);

        }

    }
}
