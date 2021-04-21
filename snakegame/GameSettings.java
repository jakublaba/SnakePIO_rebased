package snakegame;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public interface GameSettings {
    double HEIGHT = Screen.getPrimary().getBounds().getHeight();
    double WIDTH = Screen.getPrimary().getBounds().getWidth();
    Color snakeColor = Color.DARKSALMON;
    Color snakeEdgeColor = Color.web("#654321");
    Color foodColor = Color.DARKRED;
    Color background_one = Color.GREEN;
    Color getBackground_two = Color.DARKGREEN;
    double segmentSize = 30;
    float maxSpeed = 5;
}
