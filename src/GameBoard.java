import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard {
    public int boardHeight, boardWidth;
    public static Snake snake;
    public LinkedList<Point2D.Double> obstacles;
    public Point2D.Double food;

    public GameBoard(int boardHeight, int boardWidth, int segmentSize) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.food = new Point2D.Double(ThreadLocalRandom.current().nextDouble(segmentSize/2, boardWidth - segmentSize/2), ThreadLocalRandom.current().nextDouble(segmentSize/2, boardHeight - segmentSize/2));
    }

    void respawnFood(int segmentSize) {
        Point2D.Double newFood = new Point2D.Double(ThreadLocalRandom.current().nextDouble(segmentSize/2, boardWidth - segmentSize/2), ThreadLocalRandom.current().nextDouble(segmentSize/2, boardHeight - segmentSize/2));
        while(food.equals(newFood) || food.equals(snake.bodySegments.getFirst())) {
            newFood.setLocation(ThreadLocalRandom.current().nextDouble(segmentSize/2, boardWidth - segmentSize/2), ThreadLocalRandom.current().nextDouble(segmentSize/2, boardHeight - segmentSize/2));
        }
        food = newFood;
    }

    public static class Snake {
        public LinkedList<Point2D.Double> bodySegments;

        Snake(int boardWidth, int boardHeight, int segmentSize) {
            bodySegments = new LinkedList<>();
            bodySegments.add(0, new Point2D.Double(ThreadLocalRandom.current().nextDouble(segmentSize/2, boardWidth - segmentSize/2), ThreadLocalRandom.current().nextDouble(segmentSize/2, boardHeight - segmentSize/2)));
        }

        public void addBodySegment() {
            double vectorX = bodySegments.get(bodySegments.size() - 2).getX() - bodySegments.getLast().getX();
            double vectorY = bodySegments.get(bodySegments.size() - 2).getY() - bodySegments.getLast().getY();
            Point2D.Double newBodySegment = new Point2D.Double(bodySegments.getLast().getX() + vectorX, bodySegments.getLast().getY() + vectorY);
            bodySegments.add(newBodySegment);
        }
    }
}
