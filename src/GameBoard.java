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
        newFood.setLocation(ThreadLocalRandom.current().nextDouble(segmentSize/2, boardWidth - segmentSize/2), ThreadLocalRandom.current().nextDouble(segmentSize/2, boardHeight - segmentSize/2));
        food = newFood;
    }

    public static class Snake {
        public LinkedList<Point2D.Double> bodySegments;

        Snake(int boardWidth, int boardHeight, int segmentSize) {
            bodySegments = new LinkedList<>();
            bodySegments.add(0, new Point2D.Double(ThreadLocalRandom.current().nextDouble(segmentSize/2, boardWidth - segmentSize/2), ThreadLocalRandom.current().nextDouble(segmentSize/2, boardHeight - segmentSize/2)));
        }

        public void move() {
            for(int i = 1; i < bodySegments.size(); i++) {
                bodySegments.get(i).setLocation(bodySegments.get(i - 1));
            }
        }

        public void addBodySegment() {
            Point2D.Double newBodySegment;
            //jeżeli jest sama głowa
            if(bodySegments.size() == 1) {
                newBodySegment = new Point2D.Double(bodySegments.getFirst().getX(), bodySegments.getFirst().getY());
                bodySegments.addLast(newBodySegment);
            }
            //jeżeli są już jakieś segmenty poza głową
            else {
                newBodySegment = new Point2D.Double(bodySegments.getLast().getX(), bodySegments.getLast().getY());
                bodySegments.addLast(newBodySegment);
            }
        }
    }
}
