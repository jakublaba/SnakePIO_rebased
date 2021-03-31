import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Random;

public class GameBoard {
    public int boardHeight, boardWidth;
    public Snake snake;
    public LinkedList<Point2D.Double> obstacles;
    public Point2D.Double food;

    public GameBoard(int boardHeight, int boardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.snake = new Snake();
        Random rand = new Random();
        this.food = new Point2D.Double(boardWidth*rand.nextDouble(), boardHeight*rand.nextDouble());
        System.out.printf("initial food location: (%f, %f)\n", food.getX(), food.getY());
    }

    void respawnFood() {
        Random rand = new Random();
        Point2D.Double newFood = new Point2D.Double(boardWidth*rand.nextDouble(), boardHeight*rand.nextDouble());
        while(food.equals(newFood)) {
            newFood.setLocation(boardWidth*rand.nextDouble(), boardHeight*rand.nextDouble());
        }
        food = newFood;
        System.out.printf("new food location: (%f, %f)\n", food.getX(), food.getY());
    }

    private class Snake {
        LinkedList<Point2D.Double> bodySegments;
        Snake() {
            bodySegments = new LinkedList<Point2D.Double>();
            Random rand = new Random();
            bodySegments.add(0, new Point2D.Double(boardWidth*rand.nextDouble(), boardHeight*rand.nextDouble()));
            System.out.printf("snake head location: (%f, %f)\n", bodySegments.get(0).getX(), bodySegments.get(0).getY());
        }
    }
}
