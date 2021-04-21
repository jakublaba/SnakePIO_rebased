
import javafx.scene.paint.Color;

public interface GameSettings {
    int HEIGHT = 800;
    int WIDTH = 800;
    Color snakeColor = Color.DARKSALMON;
    Color foodColor = Color.DARKRED;
    Color background_one = Color.GREEN;
    Color getBackground_two = Color.DARKGREEN;
    double segmentSize = 20;
}
