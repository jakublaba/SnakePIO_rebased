import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Random;

public class GameBoard {
    public int boardHeight, boardWidth;
    public static Snake snake;
    public LinkedList<Point2D.Double> obstacles;
    public Point2D.Double food;

    public GameBoard(int boardHeight, int boardWidth, int segmentSize) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        Random rand = new Random();
        this.food = new Point2D.Double((boardWidth - segmentSize)*rand.nextDouble(), (boardHeight - segmentSize)*rand.nextDouble());
    }

    void respawnFood(int segmentSize) {
        Random rand = new Random();
        Point2D.Double newFood = new Point2D.Double((boardWidth - segmentSize)*rand.nextDouble(), (boardHeight - segmentSize)*rand.nextDouble());
        while(food.equals(newFood) || food.equals(snake.bodySegments.getFirst())) {
            newFood.setLocation((boardWidth - segmentSize)*rand.nextDouble(), (boardHeight - segmentSize)*rand.nextDouble());
        }
        food = newFood;
    }

    public static class Snake {
        public LinkedList<Point2D.Double> bodySegments;

        Snake(int boardWidth, int boardHeight, int segmentSize) {
            bodySegments = new LinkedList<>();
            Random rand = new Random();
            bodySegments.add(0, new Point2D.Double((boardWidth - segmentSize)*rand.nextDouble(), (boardHeight - segmentSize)*rand.nextDouble()));
        }

        public void addBodySegment() {
            double vectorX = bodySegments.get(bodySegments.size() - 2).getX() - bodySegments.getLast().getX();
            double vectorY = bodySegments.get(bodySegments.size() - 2).getY() - bodySegments.getLast().getY();
            Point2D.Double newBodySegment = new Point2D.Double(bodySegments.getLast().getX() + vectorX, bodySegments.getLast().getY() + vectorY);
            bodySegments.add(newBodySegment);
        }
    }
}
