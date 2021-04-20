package snakegame;

import javafx.scene.paint.Color;

public interface GameSettings {
    int HEIGHT = 800;
    int WIDTH = 800;
    Color snakeColor = Color.DARKGREEN;
    Color foodColor = Color.DARKRED;
    double segmentSize = 20;
}
