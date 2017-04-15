package xyz.betanyan.snakegame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MoveListener extends KeyAdapter {

    private GameRunnable gameRunnable;
    private SnakeObject snake;

    public MoveListener(SnakeObject snake, GameRunnable gameRunnable) {
        this.snake = snake;
        this.gameRunnable = gameRunnable;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Start the game
        gameRunnable.setStarted(true);

        // For each direction change, we check if it's not the opposite direction and the size isn't 1.
        switch (e.getKeyCode()) {

            case 37:	// Left Array
                if (snake.getHeadBody().getDirection() != 1) {
                    snake.getHeadBody().setDirection(0);
                } else if (snake.getSize() == 1) {
                    snake.getHeadBody().setDirection(0);
                }
                break;
            case 38:	// Up Array
                if (snake.getHeadBody().getDirection() != 2) {
                    snake.getHeadBody().setDirection(3);
                } else if (snake.getSize() == 1) {
                    snake.getHeadBody().setDirection(3);
                }
                break;
            case 39: 	// Right Array
                if (snake.getHeadBody().getDirection() != 0) {
                    snake.getHeadBody().setDirection(1);
                } else if (snake.getSize() == 1) {
                    snake.getHeadBody().setDirection(1);
                }
                break;
            case 40:	// Down Array
                if (snake.getHeadBody().getDirection() != 3) {
                    snake.getHeadBody().setDirection(2);
                } else if (snake.getSize() == 1) {
                    snake.getHeadBody().setDirection(2);
                }
                break;
            default:
                break;
        }
    }
}
