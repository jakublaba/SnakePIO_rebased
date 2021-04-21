package snakegame;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public interface GameSettings {
    int HEIGHT = 800;
    int WIDTH = 800;
    Color snakeColor = Color.DARKSALMON;
    Color snakeEdgeColor = Color.web("#654321");
    Color foodColor = Color.DARKRED;
    Color background_one = Color.GREEN;
    Color getBackground_two = Color.DARKGREEN;
    double segmentSize = 30;
    float maxSpeed = 5;
}
