package snakegame;


import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private javafx.scene.control.Button settingsButton;

    @FXML
    public void startButtonAction(ActionEvent event) {
        Stage Menu = (Stage) settingsButton.getScene().getWindow();
        Menu.hide();
        Pane gameField;
        Vector mousePosition = new Vector(0,0);
        Stage primaryStage = new Stage();

            BorderPane root = new BorderPane();
            StackPane layerPane = new StackPane();

            gameField = new Pane();

            layerPane.getChildren().addAll(gameField);
            root.setCenter(layerPane);

            Scene scene = new Scene(root, GameSettings.WIDTH, GameSettings.HEIGHT, Color.BLACK);
            primaryStage.setTitle("SnakeFX");
            primaryStage.setScene(scene);
            primaryStage.show();

            GamePane GamePane = new GamePane();
            gameField.getChildren().add(GamePane);

            // capture mouse position
            /* to chwilowo bo mi sie nie chcialo przepisywac
            scene.addEventFilter(MouseEvent.ANY, e -> mousePosition.set(e.getX(), e.getY()));

            AnimationTimer gameLoop = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    GamePane.mySnake.updateHeadLocation(mousePosition);
                    GamePane.mySnake.move();
                    GamePane.checkBorders();
                    GamePane.checkTailCollision();
                    GamePane.checkFood();
                    //System.out.printf("Current snake length: %d\n", GamePane.mySnake.bodySegments.size());
                    GamePane.show();
                }
            };
            gameLoop.start();*/
        }


    @FXML
    public void exitButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void settingsButtonAction(ActionEvent event) throws Exception {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Settings Snake");
            stage.setScene(new Scene(root, 576.0, 415.0));
            stage.show();
            Stage stage1 = (Stage) settingsButton.getScene().getWindow();
            stage1.hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    public void backButtonAction(ActionEvent event) {
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

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
    }
}
