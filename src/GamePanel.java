import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
    private final int gameSegmentSize = 50;
    private Point2D.Double mousePosition = new Point2D.Double();
    private double gameSpeed = 2.5;
    private boolean speedUp = false;
    private boolean mouseInWindow = false;
    private Timer timer = new Timer(0, this);
    private GameBoard gameBoard;

    public GamePanel() {
        gameBoard = new GameBoard(800, 800, gameSegmentSize);
        this.setBackground(Color.BLACK);
        gameBoard.snake = new GameBoard.Snake(gameBoard.boardWidth, gameBoard.boardHeight, gameSegmentSize);
        addMouseListener(this);
        addMouseMotionListener(this);
        timer.start();
    }

    private void checkBorderCollision() {
        double distFromTop = gameBoard.snake.bodySegments.getFirst().getY();
        double distFromBottom = gameBoard.boardHeight - distFromTop;
        double distFromLeft = gameBoard.snake.bodySegments.getFirst().getX();
        double distFromRight = gameBoard.boardWidth - distFromLeft;
        if(distFromTop <= gameSegmentSize/2) {
            System.out.println("Game Over: collision with top border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", gameBoard.snake.bodySegments.getFirst().getX(), gameBoard.snake.bodySegments.getFirst().getY());
            System.exit(1);
        }
        if(distFromBottom <= gameSegmentSize/2) {
            System.out.println("Game Over: collision with bottom border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", gameBoard.snake.bodySegments.getFirst().getX(), gameBoard.snake.bodySegments.getFirst().getY());
            System.exit(1);
        }
        if(distFromLeft <= gameSegmentSize/2) {
            System.out.println("Game Over: collision with left border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", gameBoard.snake.bodySegments.getFirst().getX(), gameBoard.snake.bodySegments.getFirst().getY());
            System.exit(1);
        }
        if(distFromRight <= gameSegmentSize/2) {
            System.out.println("Game Over: collision with right border");
            System.out.printf("Coordinates of head when game ended: (%f, %f)\n", gameBoard.snake.bodySegments.getFirst().getX(), gameBoard.snake.bodySegments.getFirst().getY());
            System.exit(1);
        }
    }

    private boolean checkFood() {
        double xDiffSqr = (gameBoard.snake.bodySegments.getFirst().getX()-gameBoard.food.getX())*(gameBoard.snake.bodySegments.getFirst().getX()-gameBoard.food.getX());
        double yDiffSqr = (gameBoard.snake.bodySegments.getFirst().getY()-gameBoard.food.getY())*(gameBoard.snake.bodySegments.getFirst().getY()-gameBoard.food.getY());
        double distance = Math.sqrt(xDiffSqr + yDiffSqr);
        return distance < gameSegmentSize/2;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(mouseInWindow) {
            mousePosition.setLocation(e.getX(), e.getY());
        }
        if(!speedUp) {
            gameSpeed *= 2;
            speedUp = true;
        }
        //System.out.println("mouseDragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(mouseInWindow) {
            mousePosition.setLocation(e.getX(), e.getY());
        }
        //System.out.println("mouseMoved");
        //repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouseClicked");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseInWindow = true;
        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseInWindow = false;
        System.out.println("mouseExited");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!speedUp) {
            gameSpeed *= 2;
            speedUp = true;
        }
        //System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(speedUp) {
            gameSpeed /= 2;
            speedUp = false;
        }
        //System.out.println("mouseReleased");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GREEN);
        Ellipse2D.Double snakeSegmentImg;
        for(int i = 0; i < gameBoard.snake.bodySegments.size(); i++) {
            snakeSegmentImg = new Ellipse2D.Double(gameBoard.snake.bodySegments.get(i).getX() - gameSegmentSize/2, gameBoard.snake.bodySegments.get(i).getY() - gameSegmentSize/2, gameSegmentSize, gameSegmentSize);
            g2d.fill(snakeSegmentImg);
        }
        Ellipse2D.Double foodImg = new Ellipse2D.Double(gameBoard.food.getX() - gameSegmentSize/2, gameBoard.food.getY() - gameSegmentSize/2, gameSegmentSize, gameSegmentSize);
        g2d.setColor(Color.RED);
        g2d.fill(foodImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(mouseInWindow) {
            double vectorX = mousePosition.getX() - gameBoard.snake.bodySegments.getFirst().getX();
            double vectorY = mousePosition.getY() - gameBoard.snake.bodySegments.getFirst().getY();
            double vectorLength = Math.sqrt(vectorX*vectorX + vectorY*vectorY);
            double dirX = vectorX/vectorLength;
            double dirY = vectorY/vectorLength;
            gameBoard.snake.bodySegments.getFirst().setLocation(gameBoard.snake.bodySegments.getFirst().getX() + dirX*gameSpeed, gameBoard.snake.bodySegments.getFirst().getY() + dirY*gameSpeed);
        }
        checkBorderCollision();
        if(checkFood()) {
            System.out.printf("Food at (%f, %f) location collected by head at (%f, %f) location\n", gameBoard.food.getX(), gameBoard.food.getY(), gameBoard.snake.bodySegments.getFirst().getX(), gameBoard.snake.bodySegments.getFirst().getY());
            gameBoard.respawnFood(gameSegmentSize);
            gameBoard.snake.addBodySegment();
        }
        gameBoard.snake.move();
        repaint();
    }
}
