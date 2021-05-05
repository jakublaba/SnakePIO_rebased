package snakegame;

import javafx.scene.paint.Color;

public interface GameSettings {
    /*
     *  WINDOW SIZES
     */
    double BOARD_HEIGHT = 800;
    double BOARD_WIDTH = 800;
    double MENU_HEIGHT = 415;
    double MENU_WIDTH = 576;

    /*
     *  SNAKE PROPERTIES
     */
    int FOOD_MULTIPLIER = 2;
    int SPECIAL_FOOD_MULTIPLIER = 5;
    double SEGMENT_SIZE = 30;
    double SMALL_SAW_SIZE = 40;
    double BIG_SAW_SIZE = 70;
    double MAX_SPEED = 4;

    /*
     * GRAPHICS AND ANIMATIONS
     */
    Color SNAKE_COLOR_ONE = Color.DARKBLUE;
    Color SNAKE_COLOR_TWO = Color.CORNFLOWERBLUE;
    Color SNAKE_EDGE_COLOR = Color.LIGHTSTEELBLUE;
    Color BG_COLOR_ONE = Color.web("#7ed967");
    Color BG_COLOR_TWO = Color.web("#12743c");

    String APPLE_IMG = "resources/img/apple.png";
    String RUBY_IMG = "resources/img/ruby.png";
    String SMALL_SAW_IMG = "resources/img/SawSmall.png";
    String BIG_SAW_IMG = "resources/img/SawBig.png";

    int FAST_ROTATION = 10;
    int SLOW_ROTATION = 5;
}
