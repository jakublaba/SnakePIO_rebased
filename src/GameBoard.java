
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.concurrent.ThreadLocalRandom;

public class GameBoard extends Pane {
    public final double boardHeight, boardWidth;
    public static Snake snake;
    public static Vector food;
    private final double segmentSize;

    public GameBoard(double boardHeight, double boardWidth, double segmentSize) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.segmentSize = segmentSize;
        food = new Vector(ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardWidth - segmentSize / 2), ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardHeight - segmentSize / 2));
        snake = new Snake(GameSettings.segmentSize);
    }

    void respawnFood() {
        Vector newFood = new Vector(ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardWidth - segmentSize / 2), ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardHeight - segmentSize / 2));
        newFood.set(ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardWidth - segmentSize / 2), ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardHeight - segmentSize / 2));
        food = newFood;
    }

    public void checkFood() {
        Vector distance = new Vector(snake.bodySegments.get(0).getX(), snake.bodySegments.get(0).getY());
        distance.subtract(food);
        if(distance.length() < segmentSize / 1.5) {
            respawnFood();
            //na potrzeby testowania wąż rośnie szybciej, żeby przy każdym uruchomieniu nie musieć zbierać żarcia w nieskończoność xd
            for(int i = 0; i < 5; i++) { snake.addBodySegment(); }
        }
    }
    public void show() {
        getChildren().clear();
        setBackground();

        for(int i = 0; i < snake.bodySegments.size(); i++) {
            Circle snakeSegmentImg = new Circle(segmentSize / 2);
            snakeSegmentImg.setCenterX(snake.bodySegments.get(i).getX() - segmentSize / 2);
            snakeSegmentImg.setCenterY(snake.bodySegments.get(i).getY() - segmentSize / 2);
            snakeSegmentImg.setFill(GameSettings.snakeColor);
            getChildren().add(snakeSegmentImg);
        }


        Circle foodImg = new Circle(segmentSize / 2);
        foodImg.setCenterX(food.getX());
        foodImg.setCenterY(food.getY());
        foodImg.setFill(GameSettings.foodColor);
        getChildren().add(foodImg);
    }
    public void setBackground() {
        int sideLength = 40;
        int rest = GameSettings.HEIGHT%sideLength;
        int numberOfSquare = (GameSettings.HEIGHT - GameSettings.HEIGHT%sideLength) / sideLength;

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

}