package snakegame;
import javafx.scene.paint.Color;

public interface GameSettings {
    double HEIGHT = 800;
    double WIDTH = 800;
    int sizeMultiplier = 5;
    Color snakeColorOne = Color.LIGHTSKYBLUE; //był darksalmon
    Color snakeColorTwo = Color.CORNFLOWERBLUE;
    Color snakeEdgeColor = Color.LIGHTSTEELBLUE; // był Color.web("#654321")
    Color foodColor = Color.RED;
    Color bgColorOne = Color.GREEN;
    Color bgColorTwo = Color.DARKGREEN;
    double segmentSize = 30;
    double maxSpeed = 4;
}
