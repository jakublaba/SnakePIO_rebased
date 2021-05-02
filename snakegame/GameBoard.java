package snakegame;

import java.util.ListIterator;

public class GameBoard {
    private final double boardHeight, boardWidth, segmentSize;
    private final Snake mySnake;
    public static Food myFood;
    private final GameSoundPlayer mySoundPlayer;

    public GameBoard() {
        this.boardHeight = GameSettings.HEIGHT;
        this.boardWidth = GameSettings.WIDTH;
        this.segmentSize = GameSettings.SEGMENT_SIZE;
        myFood = new Food();
        mySnake = new Snake();
        mySoundPlayer = new GameSoundPlayer();
    }

    public Snake getMySnake() {
        return mySnake;
    }

    public void updateGame(PointVector mousePosition) {
        mySnake.move(mousePosition);
        checkBorders();
        if (checkTailCollision()) {
            mySoundPlayer.playSnakeCrashedSound();
        }
        if (checkFood()) {
            mySoundPlayer.playFoodEatenSound();
        }
    }

    private boolean checkFood() {
        PointVector distance = new PointVector(mySnake.getHead().getX(), mySnake.getHead().getY());
        distance.subtract(myFood.getPosition());
        if (distance.length() < segmentSize) {
            myFood.respawn();
            mySnake.addBodySegment();
            return true;
        }
        return false;
    }

    private boolean checkTailCollision() {
        final int limitDoUgryzienia = 30; // nie da się zbytnio zderzyć poniżej
        var mySnake = getMySnake();

        if ((mySnake.getActualSize()) > limitDoUgryzienia) {
            var myBodySegments = mySnake.getBodySegments();
            ListIterator<PointVector> mySnakeIterator = myBodySegments.listIterator(limitDoUgryzienia);

            var headPosition = mySnake.getHead();

            while (mySnakeIterator.hasNext()) {
                var distance = new PointVector(headPosition.getX(), headPosition.getY());
                distance.subtract(mySnakeIterator.next());
                if (distance.length() < (segmentSize / 2)) {
                    System.exit(1); //to będzie trzeba usunąć oczywiście
                    return true;
                }
            }
        }
        return false;
    }

    private void checkBorders() {
        ListIterator<PointVector> mySnakeIterator = mySnake.getBodySegments().listIterator();
        final var head = mySnakeIterator.next();
        var newHead = new PointVector(head.getX(), head.getY());

        if (head.getX() > boardWidth) {
            newHead.setX(0);
            mySnakeIterator.set(newHead);
        } else if (head.getX() < 0) {
            newHead.setX(boardWidth);
            mySnakeIterator.set(newHead);
        }

        if (head.getY() > boardHeight) {
            newHead.setY(0);
            mySnakeIterator.set(newHead);
        } else if (head.getY() < 0) {
            newHead.setY(boardHeight);
            mySnakeIterator.set(newHead);
        }
    }


}