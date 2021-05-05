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
    int SIZE_MULTIPLIER = 10;
    double SEGMENT_SIZE = 30;
    double SMALL_SAW_SIZE = 40;
    double BIG_SAW_SIZE = 70;
    double MAX_SPEED = 4;

    /*
     * GRAPHICS
     */
    Color SNAKE_COLOR_ONE = Color.LIGHTSKYBLUE;
    Color SNAKE_COLOR_TWO = Color.CORNFLOWERBLUE;
    Color SNAKE_EDGE_COLOR = Color.LIGHTSTEELBLUE;
    Color BG_COLOR_ONE = Color.GREEN;
    Color BG_COLOR_TWO = Color.DARKGREEN;

    /*
    * IMGS
    * */
    String APPLE_IMG = "resources/img/apple.png";
    String RUBY_IMG = "resources/img/ruby.png";
    String SAW_IMG1 = "resources/img/SawSmall.png";
    String SAW_IMG2 = "resources/img/SawBig.png";
}
