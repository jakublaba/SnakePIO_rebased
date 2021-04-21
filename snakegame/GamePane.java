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


    public void checkFood() {
        Vector distance = new Vector(snake.bodySegments.get(0).getX(), snake.bodySegments.get(0).getY());
        distance.subtract(food);
        if(distance.length() < segmentSize / 1.2) {
            respawnFood();
            //na potrzeby testowania wąż rośnie szybciej, żeby przy każdym uruchomieniu nie musieć zbierać żarcia w nieskończoność xd
            for(int i = 0; i < 5; i++) { snake.addBodySegment(); }
        }
    }

    public void setBackground() {
        int sideLength = 40;
        double rest = GameSettings.HEIGHT % sideLength;
        double numberOfSquare = (GameSettings.HEIGHT - GameSettings.HEIGHT % sideLength) / sideLength;

        for(int i = 0; i < numberOfSquare; ++i) {
            for(int j = 0; j < numberOfSquare; ++j) {

                Rectangle squareBackground = new Rectangle();
                squareBackground.setX(i*sideLength + rest/2);
                squareBackground.setY(j*sideLength + rest/2);
                squareBackground.setWidth(sideLength);
                squareBackground.setHeight(sideLength);
                if ((i + j) % 2 == 0) {
                    squareBackground.setFill(GameSettings.background_one);
                } else {
                    squareBackground.setFill(GameSettings.getBackground_two);
                }

                getChildren().add(squareBackground);
            }
        }
    }

    public void show() {
        getChildren().clear();
        setBackground();

        for(int i = 0; i < Snake.bodySegments.size(); i++) {
            Circle snakeSegmentImg = new Circle(segmentSize / 2);
            snakeSegmentImg.setCenterX(Snake.bodySegments.get(i).getX() - segmentSize / 2);
            snakeSegmentImg.setCenterY(Snake.bodySegments.get(i).getY() - segmentSize / 2);
            snakeSegmentImg.setFill(GameSettings.snakeColor);
            snakeSegmentImg.setStroke(GameSettings.snakeEdgeColor);
            getChildren().add(snakeSegmentImg);
        }

        Circle foodImg = new Circle(segmentSize / 2);
        foodImg.setCenterX(food.getX());
        foodImg.setCenterY(food.getY());
        foodImg.setFill(GameSettings.foodColor);
        getChildren().add(foodImg);
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