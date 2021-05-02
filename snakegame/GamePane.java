package snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GamePane extends Pane {
    private final double segmentSize;

    public GamePane() {
        this.segmentSize = GameSettings.SEGMENT_SIZE;
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
                    squareBackground.setFill(GameSettings.BG_COLOR_ONE);
                } else {
                    squareBackground.setFill(GameSettings.BG_COLOR_TWO);
                }

                getChildren().add(squareBackground);
            }
        }
    }

    public void show(GameBoard myBoard) {
        getChildren().clear();
        setBackground();

        for(int i = 0; i < myBoard.mySnake.getSize(); i++) {
            Circle snakeSegmentImg = new Circle(segmentSize / 2);
            snakeSegmentImg.setCenterX(GameBoard.mySnake.get(i).getX() - segmentSize / 2);
            snakeSegmentImg.setCenterY(GameBoard.mySnake.get(i).getY() - segmentSize / 2);
            if (i % 2 == 0)
                snakeSegmentImg.setFill(GameSettings.SNAKE_COLOR_ONE);
            else
                snakeSegmentImg.setFill(GameSettings.SNAKE_COLOR_TWO);
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