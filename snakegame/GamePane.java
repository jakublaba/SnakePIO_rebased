package snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ListIterator;

public class GamePane extends Pane {
    private final double segmentSize;

    public GamePane() {
        this.segmentSize = GameSettings.SEGMENT_SIZE;
    }

    public void setBackground() {
        int sideLength = 40;
        double rest = GameSettings.HEIGHT % sideLength;
        double numberOfSquare = (GameSettings.HEIGHT - GameSettings.HEIGHT % sideLength) / sideLength;

        for (int i = 0; i < numberOfSquare; ++i) {
            for (int j = 0; j < numberOfSquare; ++j) {
                Rectangle squareBackground = new Rectangle();
                squareBackground.setX(i * sideLength + rest / 2);
                squareBackground.setY(j * sideLength + rest / 2);
                squareBackground.setWidth(sideLength);
                squareBackground.setHeight(sideLength);
                if ((i + j) % 2 == 0) {
                    squareBackground.setFill(GameSettings.BG_COLOR_ONE);
                } else {
                    squareBackground.setFill(GameSettings.BG_COLOR_TWO);
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
        ListIterator<PointVector> mySnakeIterator = mySnakeBodySegments.listIterator(); //creates the iterator
        while (mySnakeIterator.hasNext()) {
            Circle snakeSegmentImg = new Circle(segmentSize / 2);
            var snakeSegmentPosition = mySnakeIterator.next(); //temporary PointVector object
            snakeSegmentImg.setCenterX(snakeSegmentPosition.getX() - segmentSize / 2);
            snakeSegmentImg.setCenterY(snakeSegmentPosition.getY() - segmentSize / 2);

            /* colouring */
            if (colorChooser % 2 == 0)
                snakeSegmentImg.setFill(GameSettings.SNAKE_COLOR_ONE);
            else
                snakeSegmentImg.setFill(GameSettings.SNAKE_COLOR_TWO);
            colorChooser++;
            snakeSegmentImg.setStroke(GameSettings.SNAKE_EDGE_COLOR);

            getChildren().add(snakeSegmentImg);
        }

        Circle foodImg = new Circle(segmentSize / 2);
        foodImg.setCenterX(myBoard.myFood.getX());
        foodImg.setCenterY(myBoard.myFood.getY());
        foodImg.setFill(GameSettings.FOOD_COLOR);
        getChildren().add(foodImg);
    }

}