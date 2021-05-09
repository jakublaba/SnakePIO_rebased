package snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public final class Main extends Application {
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            primaryStage.setTitle("SnakeFX");
            primaryStage.getIcons().add(new Image(getClass().getResource(GameSettings.SNAKE_ICON).toExternalForm()));
            primaryStage.setScene(new Scene(root, GameSettings.MENU_WIDTH, GameSettings.MENU_HEIGHT));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}