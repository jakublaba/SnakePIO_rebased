package snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GamePane extends Pane {
    private final double segmentSize;
    private static Color BG_COLOR_O = GameSettings.BG_COLOR_ONE;
    private static Color BG_COLOR_T = GameSettings.BG_COLOR_TWO;
    private final ImageView appleImg, rubyImg, verticalSawImg, horizontalSawImg;

    public GamePane() {
        segmentSize = GameSettings.SEGMENT_SIZE;
        Image apple = new Image(getClass().getResourceAsStream(GameSettings.APPLE_IMG));
        Image ruby = new Image(getClass().getResourceAsStream(GameSettings.RUBY_IMG));
        Image verticalSaw = new Image(getClass().getResourceAsStream(GameSettings.SAW_IMG1));
        Image horizontalSaw = new Image(getClass().getResourceAsStream(GameSettings.SAW_IMG2));
        appleImg = new ImageView();
        rubyImg = new ImageView();
        verticalSawImg = new ImageView();
        horizontalSawImg = new ImageView();
        appleImg.setImage(apple);
        rubyImg.setImage(ruby);
        verticalSawImg.setImage(verticalSaw);
        horizontalSawImg.setImage(horizontalSaw);
    }


    public static void setBG_COLORS (Color choiceOne, Color choiceTwo){
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
                    squareBackground.setFill(this.BG_COLOR_O);
                } else {
                    squareBackground.setFill(this.BG_COLOR_T);
                }

                getChildren().add(squareBackground);
            }
        }
    }
    public void show(GameBoard myBoard) {
        getChildren().clear();
        setBackground();

        int colorChooser = 0; /* only used for determining snakeBodySegment colour */

        var mySnakeBodySegments = myBoard.getMySnake().getBodySegments(); //copy the segments

        for (PointVector mySnakeBodySegment : mySnakeBodySegments) {
            Circle snakeSegmentImg = new Circle(segmentSize / 2);
            snakeSegmentImg.setCenterX(mySnakeBodySegment.getX() - segmentSize / 2);
            snakeSegmentImg.setCenterY(mySnakeBodySegment.getY() - segmentSize / 2);

            /* colouring */
            if ((colorChooser / GameSettings.SIZE_MULTIPLIER) % 2 == 0) snakeSegmentImg.setFill(GameSettings.SNAKE_COLOR_ONE);
            else snakeSegmentImg.setFill(GameSettings.SNAKE_COLOR_TWO);
            colorChooser++;
            snakeSegmentImg.setStroke(GameSettings.SNAKE_EDGE_COLOR);

            getChildren().add(snakeSegmentImg);
        }
        /*
        Circle foodImg = new Circle(segmentSize / 2);
        foodImg.setCenterX(myBoard.getMyFood().getX());
        foodImg.setCenterY(myBoard.getMyFood().getY());
        foodImg.setFill(GameSettings.FOOD_COLOR);
        getChildren().add(foodImg);
        */
        appleImg.setX(myBoard.getMyFood().getX() - segmentSize / 2);
        appleImg.setY(myBoard.getMyFood().getY() - segmentSize / 2);
        getChildren().add(appleImg);

        if (myBoard.getMySpecialFood().isAlive()) {
            /*
            Circle specialFoodImg = new Circle(segmentSize / 2);
            specialFoodImg.setCenterX(myBoard.getMySpecialFood().getX());
            specialFoodImg.setCenterY(myBoard.getMySpecialFood().getY());
            if (myBoard.getMySpecialFood().getLongevity() > 100)
                specialFoodImg.setFill(Color.DARKORANGE);
            else
                specialFoodImg.setFill(Color.YELLOW);
            getChildren().add(specialFoodImg);
            */
            rubyImg.setX(myBoard.getMySpecialFood().getX() - segmentSize / 2);
            rubyImg.setY(myBoard.getMySpecialFood().getY() - segmentSize / 2);
            getChildren().add(rubyImg);
        }
        verticalSawImg.setX(myBoard.getMyVerticalSaw().getX() - myBoard.getMyVerticalSaw().getSize() / 2);
        verticalSawImg.setY(myBoard.getMyVerticalSaw().getY() - myBoard.getMyVerticalSaw().getSize() / 2);
        verticalSawImg.setRotate(verticalSawImg.getRotate() + 10);
        getChildren().add(verticalSawImg);

        horizontalSawImg.setX(myBoard.getMyHorizontalSaw().getX() - myBoard.getMyHorizontalSaw().getSize() / 2);
        horizontalSawImg.setY(myBoard.getMyHorizontalSaw().getY() - myBoard.getMyHorizontalSaw().getSize() / 2);
        horizontalSawImg.setRotate(horizontalSawImg.getRotate() + 5);
        getChildren().add(horizontalSawImg);
    }
}