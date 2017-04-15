package xyz.betanyan.snakegame;

import java.awt.*;

public class GameRunnable implements Runnable {

    private SnakeGame snakeGame;

    private SnakeObject snake;
    private GameTile food;

    private boolean started;

    public GameRunnable(SnakeGame snakeGame, SnakeObject snake, GameTile food) {
        this.snakeGame = snakeGame;

        this.snake = snake;
        this.food = food;

        this.started = false;
    }

    @Override
    public void run() {

        while (true) {

            while (started) {

                snake.update();

                /*
                    Checks if snake runs into itself.
                 */
                for (int i=0;i<snake.getTiles().size();i++) {
                    if (i != snake.getTiles().size() - 1) {
                        if (snake.getTiles().get(i).getTile().equals(snake.getHead())) {
                            System.out.println("Hit yourself");
                            System.exit(0);
                        }
                    }
                }

                /*
                    Check if snake went outside of bounds.
                 */
                if (!SnakeGame.insideBounds(snake.getHead())) {
                    System.out.println("Out of bounds");
                    System.exit(0);
                }

                /*
                    Check if snake ate food.
                 */
                if (snake.getHead().equals(food)) {
                    snake.addBody();
                    food = snakeGame.getRandomFoodTile();
                    food.setColor(Color.RED);
                }

                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void setStarted(boolean started) {
        this.started = started;
    }

}
