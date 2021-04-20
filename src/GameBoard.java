import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard {
    public int boardHeight, boardWidth;
    public static Snake mySnake;
    public static Food myFood; // będzie do przerobienia na le klasę

    //konstruktor
    public GameBoard(int boardHeight, int boardWidth, int segmentSize) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.myFood = new Food(boardWidth, boardHeight, segmentSize);
        this.mySnake = new Snake(boardWidth, boardHeight, segmentSize);
    }

    //defaultowe wartości
    public GameBoard() {
        this(800, 800, 20);
    }

    void respawnFood() {
        myFood.respawn();
    }

    public void checkBorderCollision(double gameSegmentSize) {
        double distFromTop = mySnake.bodySegments.get(0).getY();
        double distFromBottom = boardHeight - distFromTop;
        double distFromLeft = mySnake.bodySegments.get(0).getX();
        double distFromRight = boardWidth - distFromLeft;
        if (distFromTop <= gameSegmentSize / 2) {
            System.out.println("Game Over: collision with top border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", mySnake.bodySegments.get(0).getX(), mySnake.bodySegments.get(0).getY());
            System.exit(1);
        }
        if (distFromBottom <= gameSegmentSize / 2) {
            System.out.println("Game Over: collision with bottom border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", mySnake.bodySegments.get(0).getX(), mySnake.bodySegments.get(0).getY());
            System.exit(1);
        }
        if (distFromLeft <= gameSegmentSize / 2) {
            System.out.println("Game Over: collision with left border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", mySnake.bodySegments.get(0).getX(), mySnake.bodySegments.get(0).getY());
            System.exit(1);
        }
        if (distFromRight <= gameSegmentSize / 2) {
            System.out.println("Game Over: collision with right border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", mySnake.bodySegments.get(0).getX(), mySnake.bodySegments.get(0).getY());
            System.exit(1);
        }
    }

    public void checkTailCollision(double gameSegmentSize) {
        if (mySnake.bodySegments.size() > 10) { //magic number bruh
            for (int i = 10; i < mySnake.bodySegments.size(); i++) { //BRUH
                if (Math.abs(mySnake.bodySegments.get(0).distance(mySnake.bodySegments.get(i))) < gameSegmentSize / 2) {
                    System.out.printf("Game Over: collision with tail segment number %d\n", i);
                    System.exit(1);
                }
            }
        }
    }

    public boolean checkFood(double gameSegmentSize) {
        double xDiffSqr = (mySnake.bodySegments.get(0).getX() - myFood.getX()) * (mySnake.bodySegments.get(0).getX() - myFood.getX());
        double yDiffSqr = (mySnake.bodySegments.get(0).getY() - myFood.getY()) * (mySnake.bodySegments.get(0).getY() - myFood.getY());
        double distance = Math.sqrt(xDiffSqr + yDiffSqr);
        return distance < gameSegmentSize / 2;
    }

}