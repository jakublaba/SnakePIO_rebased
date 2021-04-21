package snakegame;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.concurrent.ThreadLocalRandom;

public class GamePane extends Pane {
    private final double boardHeight, boardWidth;
    public static Snake snake;
    private static Vector food;
    private final double segmentSize;

    public GamePane(double boardHeight, double boardWidth, double segmentSize) {
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

}