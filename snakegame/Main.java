package snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Snake");
        primaryStage.setScene(new Scene(root, 576.0, 415.0));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}