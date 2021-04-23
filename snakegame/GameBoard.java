package snakegame;

public class GameBoard {
    private final double boardHeight, boardWidth, segmentSize;
    public static Snake mySnake;
    public static Food myFood;

    public GameBoard() {
        this.boardHeight = GameSettings.HEIGHT;
        this.boardWidth = GameSettings.WIDTH;
        this.segmentSize = GameSettings.segmentSize;
        myFood = new Food();
        mySnake = new Snake();
    }

    public void updateGame(Vector mousePosition) {
        mySnake.updateHeadLocation(mousePosition);
        mySnake.move();
        checkBorders();
        checkTailCollision();
        checkFood();
    }

    private void checkFood() {
        Vector distance = new Vector(mySnake.get(0).getX(), mySnake.get(0).getY());
        distance.subtract(myFood.getPosition());
        if (distance.length() < (segmentSize / 1.2)) {
            myFood.respawn();
            //na potrzeby testowania wąż rośnie szybciej
            for (int i = 0; i < 9; i++) {
                mySnake.addBodySegment();
            }
        }
    }

    private void checkTailCollision() {
        if (mySnake.getSize() > 10) {
            for (int i = 10; i < mySnake.getSize(); i++) {
                Vector distance = new Vector(mySnake.get(0).getX(), mySnake.get(0).getY());
                distance.subtract(mySnake.get(i));
                if (distance.length() < (segmentSize / 2)) {
                    System.out.printf("Game Over: Collision with tail segment number %d\n", i);
                    System.exit(1);
                }
            }
        }
    }

    private void checkBorders() {
        if (mySnake.get(0).getX() > boardWidth) {
            mySnake.get(0).setX(0);
        } else if (mySnake.get(0).getX() < 0) {
            mySnake.get(0).setX(boardWidth);
        }

        if (mySnake.get(0).getY() > boardHeight) {
            mySnake.get(0).setY(0);
        } else if (mySnake.get(0).getY() < 0) {
            mySnake.get(0).setY(boardHeight);
        }
    }


}