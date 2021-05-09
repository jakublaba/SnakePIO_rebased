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
}