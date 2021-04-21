package snakegame;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;



public class GamePane extends Pane {
    private final double segmentSize;
    //public static Snake mySnake;
    //private static Food myFood;

    public GamePane() {
        this.segmentSize = GameSettings.segmentSize;
        //myFood = new Food();
        //mySnake = new Snake();
    }
/*
    public void checkFood() {
        Vector distance = new Vector(mySnake.get(0).getX(), mySnake.get(0).getY());
        distance.subtract(myFood.getPosition());
        if(distance.length() < (segmentSize / 1.2)) {
            myFood.respawn();
            //na potrzeby testowania wąż rośnie szybciej, żeby przy każdym uruchomieniu nie musieć zbierać żarcia w nieskończoność xd
            for(int i = 0; i < 5; i++) { mySnake.addBodySegment(); }
        }
    }
*/
    /*
    public void checkTailCollision() {
        if(mySnake.getSize() > 10) {
            for(int i = 10; i < mySnake.getSize(); i++) {
                Vector distance = new Vector(mySnake.get(0).getX(), mySnake.get(0).getY());
                distance.subtract(mySnake.get(i));
                if(distance.length() < (segmentSize / 2)) {
                    System.out.printf("Game Over: Collision with tail segment number %d\n", i);
                    System.exit(1);
                }
            }
        }
    }
*/
    /*
    public void checkBorders() {
        if(mySnake.get(0).getX() > GameSettings.WIDTH) {
            mySnake.get(0).setX(0);
        } else if (mySnake.get(0).getX() < 0) {
            mySnake.get(0).setX(GameSettings.WIDTH);
        }

        if (mySnake.get(0).getY() > GameSettings.HEIGHT) {
            mySnake.get(0).setY(0);
        } else if (mySnake.get(0).getY() < 0) {
            mySnake.get(0).setY(GameSettings.HEIGHT);
        }
    }
*/
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
                    squareBackground.setFill(GameSettings.bgColorOne);
                } else {
                    squareBackground.setFill(GameSettings.bgColorTwo);
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
            snakeSegmentImg.setCenterX(myBoard.mySnake.get(i).getX() - segmentSize / 2);
            snakeSegmentImg.setCenterY(myBoard.mySnake.get(i).getY() - segmentSize / 2);
            snakeSegmentImg.setFill(GameSettings.snakeColor);
            snakeSegmentImg.setStroke(GameSettings.snakeEdgeColor);
            getChildren().add(snakeSegmentImg);
        }

        Circle foodImg = new Circle(segmentSize / 2);
        foodImg.setCenterX(myBoard.myFood.getX());
        foodImg.setCenterY(myBoard.myFood.getY());
        foodImg.setFill(GameSettings.foodColor);
        getChildren().add(foodImg);
    }

}