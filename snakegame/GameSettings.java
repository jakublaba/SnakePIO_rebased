package snakegame;

import javafx.scene.paint.Color;

public interface GameSettings {
    /*
     *  GAME BOARD SIZE
     */
    double HEIGHT = 800;
    double WIDTH = 800;

    /*
     *  SNAKE PROPERTIES
     */
    int SIZE_MULTIPLIER = 10;
    double SEGMENT_SIZE = 30;
    double MAX_SPEED = 4;

    /*
     * GRAPHICS
     */
    Color SNAKE_COLOR_ONE = Color.LIGHTSKYBLUE;
    Color SNAKE_COLOR_TWO = Color.CORNFLOWERBLUE;
    Color SNAKE_EDGE_COLOR = Color.LIGHTSTEELBLUE;
    Color FOOD_COLOR = Color.RED;
    Color BG_COLOR_ONE = Color.GREEN;
    Color BG_COLOR_TWO = Color.DARKGREEN;



}
