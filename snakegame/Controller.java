package snakegame;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private javafx.scene.control.Button settingsButton;
    @FXML
    private javafx.scene.control.Button closeButton;
    private boolean pause;

    @FXML
    public void startButtonAction() {
        Stage Menu = (Stage) settingsButton.getScene().getWindow();
        Menu.hide();
        Pane gameField;
        Vector mousePosition = new Vector(0, 0);
        Stage primaryStage = new Stage();
        BorderPane root = new BorderPane();
        StackPane layerPane = new StackPane();

        gameField = new Pane();
        Button button1 = new Button("Pause");
        button1.setTranslateX(GameSettings.WIDTH / 2 - 30);
        button1.setTranslateY(GameSettings.HEIGHT / 2 - 25);
        layerPane.getChildren().addAll(gameField);
        layerPane.getChildren().add(button1);
        root.setCenter(layerPane);

        Scene scene = new Scene(root, GameSettings.WIDTH, GameSettings.HEIGHT);
        primaryStage.setTitle("SnakeFX");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        GamePane GamePane = new GamePane();
        gameField.getChildren().add(GamePane);

        scene.addEventFilter(MouseEvent.ANY, e -> mousePosition.set(e.getX(), e.getY()));
        GameBoard myBoard = new GameBoard();
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                myBoard.updateGame(mousePosition); //process controls
                GamePane.show(myBoard); //render
            }
        };
        pause = false;
        gameLoop.start();
        button1.setOnAction(event -> {
            if (!pause) {
                gameLoop.stop();
                pause = true;
            } else {
                gameLoop.start();
                pause = false;
            }
        });
    }

    @FXML
    public void exitButtonAction() {
        System.exit(0);
    }

    @FXML
    public void settingsButtonAction() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Stage stage = new Stage();
            stage.setTitle("SnakeFX - Settings");
            stage.setScene(new Scene(root, 576.0, 415.0));
            stage.setResizable(false);
            stage.show();
            Stage stage1 = (Stage) settingsButton.getScene().getWindow();
            stage1.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage stage1 = new Stage();
            stage1.setTitle("Snake");
            stage1.setScene(new Scene(root, 576.0, 415.0));
            stage1.show();
            stage.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
