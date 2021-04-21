package snakegame;

import javafx.scene.paint.Color;
import javafx.stage.Screen;

public interface GameSettings {
    double HEIGHT = Screen.getPrimary().getBounds().getHeight();
    double WIDTH = Screen.getPrimary().getBounds().getWidth();
    Color snakeColor = Color.DARKGREEN;
    Color foodColor = Color.DARKRED;
    double segmentSize = 20;
}
