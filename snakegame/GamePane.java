package snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public final class GamePane extends Pane {
    private static Color BG_COLOR_O = GameSettings.BG_COLOR_ONE;
    private static Color BG_COLOR_T = GameSettings.BG_COLOR_TWO;
    private final ImageView appleImg, rubyImg, verticalSawImg, horizontalSawImg, diagonalUpSawImg, diagonalDownSawImg;

    public GamePane() {
        Image apple = new Image(getClass().getResourceAsStream(GameSettings.APPLE_IMG));
        Image ruby = new Image(getClass().getResourceAsStream(GameSettings.RUBY_IMG));
        Image smallSaw = new Image(getClass().getResourceAsStream(GameSettings.SMALL_SAW_IMG));
        Image bigSaw = new Image(getClass().getResourceAsStream(GameSettings.BIG_SAW_IMG));
        appleImg = new ImageView();
        rubyImg = new ImageView();
        verticalSawImg = new ImageView();
        horizontalSawImg = new ImageView();
        diagonalUpSawImg = new ImageView();
        diagonalDownSawImg = new ImageView();
        appleImg.setImage(apple);
        rubyImg.setImage(ruby);
        verticalSawImg.setImage(smallSaw);
        horizontalSawImg.setImage(bigSaw);
        diagonalUpSawImg.setImage(smallSaw);
        diagonalDownSawImg.setImage(bigSaw);
    }


    public static void setBG_COLORS(Color choiceOne, Color choiceTwo) {
        BG_COLOR_O = choiceOne;
        BG_COLOR_T = choiceTwo;
    }

    public void setBackground() {
        int sideLength = 40;
        double rest = GameSettings.BOARD_HEIGHT % sideLength;
        double numberOfSquare = (GameSettings.BOARD_HEIGHT - GameSettings.BOARD_HEIGHT % sideLength) / sideLength;

        for (int i = 0; i < numberOfSquare; ++i) {
            for (int j = 0; j < numberOfSquare; ++j) {
                Rectangle squareBackground = new Rectangle();
                squareBackground.setX(i * sideLength + rest / 2);
                squareBackground.setY(j * sideLength + rest / 2);
                squareBackground.setWidth(sideLength);
                squareBackground.setHeight(sideLength);
                if ((i + j) % 2 == 0) {
                    squareBackground.setFill(BG_COLOR_O);
                } else {
                    squareBackground.setFill(BG_COLOR_T);
                }

                getChildren().add(squareBackground);
            }
        }
    }

    public void render(GameBoard myBoard) {
        getChildren().clear();
        setBackground();

        int colorChooser = 0; /* only used for determining snakeBodySegment colour */

        var mySnakeBodySegments = myBoard.getMySnake().getBodySegments(); //copy the segments

        for (PointVector mySnakeBodySegment : mySnakeBodySegments) {
            Circle snakeSegmentImg = new Circle(GameSettings.SEGMENT_SIZE / 2);
            snakeSegmentImg.setCenterX(mySnakeBodySegment.getX());
            snakeSegmentImg.setCenterY(mySnakeBodySegment.getY());

            /* colouring */
            if ((colorChooser / GameSettings.FOOD_MULTIPLIER) % 2 == 0) snakeSegmentImg.setFill(GameSettings.SNAKE_COLOR_ONE);
            else snakeSegmentImg.setFill(GameSettings.SNAKE_COLOR_TWO);
            colorChooser++;
            snakeSegmentImg.setStroke(GameSettings.SNAKE_EDGE_COLOR);

            getChildren().add(snakeSegmentImg);
        }

        appleImg.setX(myBoard.getMyFood().getX() - GameSettings.SEGMENT_SIZE / 2);
        appleImg.setY(myBoard.getMyFood().getY() - GameSettings.SEGMENT_SIZE / 2);
        getChildren().add(appleImg);

        if (myBoard.getMySpecialFood().isAlive()) {
            rubyImg.setX(myBoard.getMySpecialFood().getX() - GameSettings.SEGMENT_SIZE / 2);
            rubyImg.setY(myBoard.getMySpecialFood().getY() - GameSettings.SEGMENT_SIZE / 2);
            getChildren().add(rubyImg);
        }

        verticalSawImg.setX(myBoard.getMyVerticalSaw().getX() - myBoard.getMyVerticalSaw().getSize() / 2);
        verticalSawImg.setY(myBoard.getMyVerticalSaw().getY() - myBoard.getMyVerticalSaw().getSize() / 2);
        verticalSawImg.setRotate(verticalSawImg.getRotate() + GameSettings.FAST_ROTATION);
        getChildren().add(verticalSawImg);

        horizontalSawImg.setX(myBoard.getMyHorizontalSaw().getX() - myBoard.getMyHorizontalSaw().getSize() / 2);
        horizontalSawImg.setY(myBoard.getMyHorizontalSaw().getY() - myBoard.getMyHorizontalSaw().getSize() / 2);
        horizontalSawImg.setRotate(horizontalSawImg.getRotate() + GameSettings.SLOW_ROTATION);
        getChildren().add(horizontalSawImg);

        diagonalUpSawImg.setX(myBoard.getMyDiagonalUpSaw().getX() - myBoard.getMyDiagonalUpSaw().getSize() / 2);
        diagonalUpSawImg.setY(myBoard.getMyDiagonalUpSaw().getY() - myBoard.getMyDiagonalUpSaw().getSize() / 2);
        diagonalUpSawImg.setRotate(diagonalUpSawImg.getRotate() + GameSettings.FAST_ROTATION);
        getChildren().add(diagonalUpSawImg);

        diagonalDownSawImg.setX(myBoard.getMyDiagonalDownSaw().getX() - myBoard.getMyDiagonalDownSaw().getSize() / 2);
        diagonalDownSawImg.setY(myBoard.getMyDiagonalDownSaw().getY() - myBoard.getMyDiagonalDownSaw().getSize() / 2);
        diagonalDownSawImg.setRotate(diagonalDownSawImg.getRotate() + GameSettings.SLOW_ROTATION);
        getChildren().add(diagonalDownSawImg);
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

    public Saw getMyVerticalSaw() {
        return verticalSaw;
    }

    public Saw getMyHorizontalSaw() {
        return horizontalSaw;
    }

    public Saw getMyDiagonalUpSaw() {
        return diagonalUpSaw;
    }

    public Saw getMyDiagonalDownSaw() {
        return diagonalDownSaw;
    }

    public void updateGame(PointVector mousePosition) throws IOException {
        mySnake.move(mousePosition);
        mySpecialFood.move();
        verticalSaw.verticalMove();
        horizontalSaw.horizontalMove();
        diagonalUpSaw.diagonalMoveUp();
        diagonalDownSaw.diagonalMoveDown();
        Controller c = new Controller();
        checkBorders();
        if (checkTailCollision() || checkSawCollision()) {
            mySoundPlayer.playSnakeCrashedSound();
            c.setLosePane();
            Controller.disablePauseButton();
            Controller.layerPane.getChildren().add(Controller.losePane);
            Controller.gameLoop.stop();
            Controller.soundtrackPlayer.stop();
            if (score > highscore) {
                ReaderOfHighscore r = new ReaderOfHighscore();
                r.setHighscore(score);
                r.writeHighScore();
            }
        }
        if (checkFood() || checkSpecialFood()) {
            mySoundPlayer.playFoodEatenSound();
            score = mySnake.getSizeForUser();
            System.out.println("Score:" + score);
        }
    }

    private boolean checkFood() {
        var distance = new PointVector(mySnake.getHead().getX(), mySnake.getHead().getY());
        distance.subtract(getMyFood().getPosition());
        if (distance.length() < GameSettings.SEGMENT_SIZE) {
            getMyFood().respawn();
            for (int i = 0; i < GameSettings.FOOD_MULTIPLIER; i++) mySnake.addBodySegment();
            return true;
        }
        return false;
    }

    private boolean checkSpecialFood() {
        if (mySpecialFood.isAlive()) {
            var distance = new PointVector(mySnake.getHead().getX(), mySnake.getHead().getY());
            distance.subtract(getMySpecialFood().getPosition());
            if (distance.length() < GameSettings.SEGMENT_SIZE) {
                for (int i = 0; i < GameSettings.SPECIAL_FOOD_MULTIPLIER; i++) mySnake.addBodySegment();
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
        final int biteLimit = (int) GameSettings.SEGMENT_SIZE; // nie da się zbytnio zderzyć poniżej
        var mySnake = getMySnake();

        if ((mySnake.getActualSize()) > biteLimit) {
            var myBodySegments = mySnake.getBodySegments();
            ListIterator<PointVector> mySnakeIterator = myBodySegments.listIterator(biteLimit);

            var headPosition = mySnake.getHead();

            while (mySnakeIterator.hasNext()) {
                var distance = new PointVector(headPosition);
                distance.subtract(mySnakeIterator.next());
                if (distance.length() < (GameSettings.SEGMENT_SIZE / 2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkSawCollision() {
        var mySnake = getMySnake();
        for (PointVector bodySegment : mySnake.getBodySegments()) {
            var distanceToHorizontalSaw = new PointVector(bodySegment);
            var distanceToVerticalSaw = new PointVector(bodySegment);
            var distanceToDiagonalUpSaw = new PointVector(bodySegment);
            var distanceToDiagonalDownSaw = new PointVector(bodySegment);
            distanceToHorizontalSaw.subtract(getMyHorizontalSaw().getLocation());
            distanceToVerticalSaw.subtract(getMyVerticalSaw().getLocation());
            distanceToDiagonalUpSaw.subtract(getMyDiagonalUpSaw().getLocation());
            distanceToDiagonalDownSaw.subtract(getMyDiagonalDownSaw().getLocation());
            if (distanceToHorizontalSaw.length() < (GameSettings.SEGMENT_SIZE + getMyHorizontalSaw().getSize()) / 2.3 ||
                    distanceToVerticalSaw.length() < (GameSettings.SEGMENT_SIZE + getMyVerticalSaw().getSize()) / 2.3 ||
                    distanceToDiagonalUpSaw.length() < ((GameSettings.SEGMENT_SIZE + getMyDiagonalUpSaw().getSize()) / 2.3) ||
                    distanceToDiagonalDownSaw.length() < (GameSettings.SEGMENT_SIZE + getMyDiagonalDownSaw().getSize()) / 2.3) {
                return true;
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