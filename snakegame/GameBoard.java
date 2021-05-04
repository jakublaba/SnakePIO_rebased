package snakegame;

import java.util.ListIterator;

public final class GameBoard {
    private final double boardHeight, boardWidth, segmentSize;
    private final Snake mySnake;
    private final Food myFood;
    private final SpecialFood mySpecialFood;
    private final GameSoundPlayer mySoundPlayer;

    public GameBoard() {
        this.boardHeight = GameSettings.HEIGHT;
        this.boardWidth = GameSettings.WIDTH;
        this.segmentSize = GameSettings.SEGMENT_SIZE;

        myFood = new Food();
        mySpecialFood = new SpecialFood();
        mySnake = new Snake();
        mySoundPlayer = new GameSoundPlayer();
    }

    public Snake getMySnake() {
        return mySnake;
    }

    public Food getMyFood() {
        return myFood;
    }

    public SpecialFood getMySpecialFood() {
        return mySpecialFood;
    }

    public void updateGame(PointVector mousePosition) {
        mySnake.move(mousePosition);
        mySpecialFood.move();
        //System.out.println(mySpecialFood.getPosition().getX() + " " + mySpecialFood.getPosition().getY() + " dupa ");
        checkBorders();
        if (checkTailCollision()) {
            mySoundPlayer.playSnakeCrashedSound();
        }
        if (checkFood()) {
            mySoundPlayer.playFoodEatenSound();
        }
        if (checkSpecialFood()) {
            mySoundPlayer.playFoodEatenSound();
        }
    }

    private boolean checkFood() {
        var distance = new PointVector(mySnake.getHead().getX(), mySnake.getHead().getY());
        distance.subtract(getMyFood().getPosition());
        if (distance.length() < segmentSize) {
            getMyFood().respawn();
            mySnake.addBodySegment();
            return true;
        }
        return false;
    }

    private boolean checkSpecialFood() {
        if (mySpecialFood.isAlive()) {
            var distance = new PointVector(mySnake.getHead().getX(), mySnake.getHead().getY());
            distance.subtract(getMySpecialFood().getPosition());
            if (distance.length() < segmentSize) {
                mySnake.addBodySegment();
                mySnake.addBodySegment(); //not very elegant
                mySpecialFood.setLongevity(0);
                return true;
            } else
                return false;
        } else {
            getMySpecialFood().respawn();
            return false;
        }

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