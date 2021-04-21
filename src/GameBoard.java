import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class GameBoard {
    private int boardX, boardY;
    private Snake snake;
    private LinkedList<Point2D.Double> obstacles;
    private Point2D.Double food;

    public GameBoard() {

    }

    private void respawnFood(Point2D.Double prevFood) {

    }

    private void checkFood(Point2D.Double snakeHead, Point2D.Double food) {

    }

    private void generateObstacles(int mode) {

    }

    private class Snake {
        private LinkedList<Point2D.Double> bodySegments;

        public Snake() {

        }

        private void move(void) {

        }

        private void checkDirection(Point2D.Double snakeHead, Point2D.Double mousePosition) {

        }

        private void addSegment(Point2D.Double newSegment) {

        }
    }
}
