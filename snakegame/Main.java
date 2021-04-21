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
    GameBoard myBoard;

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

        Scene scene = new Scene(root, GameSettings.WIDTH, GameSettings.HEIGHT, Color.BLACK);
        primaryStage.setTitle("SnakeFX");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        GamePane gamePane = new GamePane();
        gameField.getChildren().add(gamePane);

        scene.addEventFilter(MouseEvent.ANY, e -> mousePosition.set(e.getX(), e.getY()));

        myBoard = new GameBoard();
        //jeszcze to poprawię - pf
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                myBoard.mySnake.updateHeadLocation(mousePosition);
                myBoard.mySnake.move();
                myBoard.checkBorders();
                myBoard.checkTailCollision();
                myBoard.checkFood();
                //System.out.printf("Current snake length: %d\n", GamePane.mySnake.bodySegments.size());
                gamePane.show(myBoard);
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}