package snakegame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    Pane gameField;
    Vector mousePosition = new Vector(0,0);

    @Override
    public void init() {
        //Tu powinny być rzeczy ustawiające plansze
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        StackPane layerPane = new StackPane();

        gameField = new Pane();

        layerPane.getChildren().addAll(gameField);
        root.setCenter(layerPane);

        Scene scene = new Scene(root, GameSettings.WIDTH, GameSettings.HEIGHT);
        primaryStage.setTitle("SnakeFX");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        GameBoard gameBoard = new GameBoard(GameSettings.HEIGHT, GameSettings.WIDTH, GameSettings.segmentSize);
        gameField.getChildren().add(gameBoard);

        // capture mouse position
        scene.addEventFilter(MouseEvent.ANY, e -> mousePosition.set(e.getX(), e.getY()));
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GameBoard.snake.updateHeadLocation(mousePosition);
                GameBoard.snake.move();
                GameBoard.snake.checkBorders();
                GameBoard.snake.checkTailCollision();
                gameBoard.checkFood();
                System.out.printf("Current snake length: %d\n", gameBoard.snake.bodySegments.size());
                gameBoard.show();
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}