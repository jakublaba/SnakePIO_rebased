package snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GamePane extends Pane {
    private final double segmentSize;

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
        Vector distance = new Vector(mySnake.get(0).getX(), mySnake.get(0).getY());
        distance.subtract(myFood.getPosition());
        if(distance.length() < (segmentSize / 1.2)) {
            myFood.respawn();
            //na potrzeby testowania wąż rośnie szybciej, żeby przy każdym uruchomieniu nie musieć zbierać żarcia w nieskończoność xd
            for(int i = 0; i < 5; i++) { mySnake.addBodySegment(); }
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
                snakeSegmentImg.setFill(GameSettings.snakeColorOne);
            else
                snakeSegmentImg.setFill(GameSettings.snakeColorTwo);
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