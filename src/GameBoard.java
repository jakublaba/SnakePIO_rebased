import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard {
    public int boardHeight, boardWidth;
    public static Snake snake;
    public Point2D.Double food;

    public GameBoard(int boardHeight, int boardWidth, int segmentSize) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.food = new Point2D.Double(ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardWidth - segmentSize / 2),
                ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardHeight - segmentSize / 2));
    }

    void respawnFood(int segmentSize) {
        Point2D.Double newFood = new Point2D.Double(ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardWidth - segmentSize / 2),
                ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardHeight - segmentSize / 2));
        newFood.setLocation(ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardWidth - segmentSize / 2),
                ThreadLocalRandom.current().nextDouble(segmentSize / 2, boardHeight - segmentSize / 2));
        food = newFood;
    }

    public void checkBorderCollision(double gameSegmentSize) {
        double distFromTop = snake.bodySegments.get(0).getY();
        double distFromBottom = boardHeight - distFromTop;
        double distFromLeft = snake.bodySegments.get(0).getX();
        double distFromRight = boardWidth - distFromLeft;
        if (distFromTop <= gameSegmentSize / 2) {
            System.out.println("Game Over: collision with top border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", snake.bodySegments.get(0).getX(), snake.bodySegments.get(0).getY());
            System.exit(1);
        }
        if (distFromBottom <= gameSegmentSize / 2) {
            System.out.println("Game Over: collision with bottom border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", snake.bodySegments.get(0).getX(), snake.bodySegments.get(0).getY());
            System.exit(1);
        }
        if (distFromLeft <= gameSegmentSize / 2) {
            System.out.println("Game Over: collision with left border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", snake.bodySegments.get(0).getX(), snake.bodySegments.get(0).getY());
            System.exit(1);
        }
        if (distFromRight <= gameSegmentSize / 2) {
            System.out.println("Game Over: collision with right border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", snake.bodySegments.get(0).getX(), snake.bodySegments.get(0).getY());
            System.exit(1);
        }
    }

    public void checkTailCollision(double gameSegmentSize) {
        if(snake.bodySegments.size() > 10) { //magic number bruh
            for(int i = 10; i < snake.bodySegments.size(); i++) { //BRUH
                if(Math.abs(snake.bodySegments.getFirst().distance(snake.bodySegments.get(i))) < gameSegmentSize/2) {
                    System.out.printf("Game Over: collision with tail segment number %d\n", i);
                    System.exit(1);
                }
            }
        }
    }

    public boolean checkFood(double gameSegmentSize) {
        double xDiffSqr = (snake.bodySegments.get(0).getX() - food.getX()) * (snake.bodySegments.get(0).getX() - food.getX());
        double yDiffSqr = (snake.bodySegments.get(0).getY() - food.getY()) * (snake.bodySegments.get(0).getY() - food.getY());
        double distance = Math.sqrt(xDiffSqr + yDiffSqr);
        return distance < gameSegmentSize / 2;
    }

}