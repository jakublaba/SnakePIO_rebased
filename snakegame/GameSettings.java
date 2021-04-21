package snakegame;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public interface GameSettings {
    double HEIGHT = 800;
    double WIDTH = 800;
    int sizeMultiplier = 5;
    Color snakeColor = Color.DARKSALMON;
    Color snakeEdgeColor = Color.web("#654321");
    Color foodColor = Color.DARKRED;
    Color bgColorOne = Color.GREEN;
    Color bgColorTwo = Color.DARKGREEN;
    double segmentSize = 30;
    double maxSpeed = 7;
}
