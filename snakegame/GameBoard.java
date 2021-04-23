package snakegame;

public class GameBoard {
    private final double boardHeight, boardWidth, segmentSize;
    public static Snake mySnake;
    public static Food myFood;
    private final SoundPlayer mySoundPlayer;

    public GameBoard() {
        this.boardHeight = GameSettings.HEIGHT;
        this.boardWidth = GameSettings.WIDTH;
        this.segmentSize = GameSettings.segmentSize;
        myFood = new Food();
        mySnake = new Snake();
        mySoundPlayer = new SoundPlayer();
    }

    public void updateGame(PointVector mousePosition) {
        mySnake.updateHeadLocation(mousePosition);
        mySnake.move();
        checkBorders();
        if (checkTailCollision()) {
            mySoundPlayer.playSnakeCrashedSound();
        }
        if (checkFood()) {
            mySoundPlayer.playFoodEatenSound();
        }
    }

    private boolean checkFood() {
        PointVector distance = new PointVector(mySnake.get(0).getX(), mySnake.get(0).getY());
        distance.subtract(myFood.getPosition());
        if (distance.length() < (segmentSize / 1.2)) {
            myFood.respawn();
            //na potrzeby testowania wąż rośnie szybciej
            for (int i = 0; i < 9; i++) {
                mySnake.addBodySegment();
            }
            return true;
        }
        return false;
    }

    private boolean checkTailCollision() {
        if (mySnake.getSize() > 10) {
            for (int i = 10; i < mySnake.getSize(); i++) {
                PointVector distance = new PointVector(mySnake.get(0).getX(), mySnake.get(0).getY());
                distance.subtract(mySnake.get(i));
                if (distance.length() < (segmentSize / 2)) {
                    System.out.printf("Game Over: Collision with tail segment number %d\n", i);
                    System.exit(1); //to będzie trzeba usunąć oczywiście
                    return true;
                }
            }
        }
        return false;
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