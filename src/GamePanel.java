import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
    private final int gameSegmentSize = 50;
    private Point2D.Double mousePosition = new Point2D.Double();
    private double gameSpeed = 2.5;
    private boolean speedUp = false;
    private boolean mouseInWindow = true;
    private Timer timer = new Timer(0, this);
    private GameBoard gameBoard;

    public GamePanel() {
        gameBoard = new GameBoard(800, 800, gameSegmentSize);
        Random rand = new Random();
        gameBoard.snake = new GameBoard.Snake(gameBoard.boardWidth, gameBoard.boardHeight, gameSegmentSize);
        addMouseListener(this);
        addMouseMotionListener(this);
        timer.start();
    }

    private void checkBorderCollision() {
        if(gameBoard.snake.bodySegments.getFirst().getX() >= 800 - gameSegmentSize/2 || gameBoard.snake.bodySegments.getFirst().getX() <= gameSegmentSize/2 || gameBoard.snake.bodySegments.getFirst().getY() >= 800 - gameSegmentSize/2) {
            System.out.println("Game Over");
            System.exit(1);
        }
    }

    private boolean checkFood() {
        if(gameBoard.snake.bodySegments.getFirst().getX() - gameBoard.food.getX() <= gameSegmentSize/2 && gameBoard.snake.bodySegments.getFirst().getY() - gameBoard.food.getY() <= gameSegmentSize/2) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition.setLocation(e.getX(), e.getY());
        if(!speedUp) {
            gameSpeed *= 2;
            speedUp = true;
        }
        System.out.println("mouseDragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.setLocation(e.getX(), e.getY());
        System.out.println("mouseMoved");
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
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
        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(speedUp) {
            gameSpeed /= 2;
            speedUp = false;
        }
        System.out.println("mouseReleased");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        Ellipse2D.Double snakeHeadImg = new Ellipse2D.Double(gameBoard.snake.bodySegments.getFirst().getX() - gameSegmentSize/2, gameBoard.snake.bodySegments.getFirst().getY() - gameSegmentSize/2, gameSegmentSize, gameSegmentSize);
        Ellipse2D.Double foodImg = new Ellipse2D.Double(gameBoard.food.getX() - gameSegmentSize/2, gameBoard.food.getY() - gameSegmentSize/2, gameSegmentSize, gameSegmentSize);
        g2d.fill(snakeHeadImg);
        g2d.setColor(Color.RED);
        g2d.fill(foodImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double vectorX = mousePosition.getX() - gameBoard.snake.bodySegments.getFirst().getX();
        double vectorY = mousePosition.getY() - gameBoard.snake.bodySegments.getFirst().getY();
        double vectorLength = Math.sqrt(vectorX*vectorX + vectorY*vectorY);
        double dirX = vectorX/vectorLength;
        double dirY = vectorY/vectorLength;
        gameBoard.snake.bodySegments.getFirst().setLocation(gameBoard.snake.bodySegments.getFirst().getX() + dirX*gameSpeed, gameBoard.snake.bodySegments.getFirst().getY() + dirY*gameSpeed);
        checkBorderCollision();
        if(checkFood()) {
            gameBoard.respawnFood(gameSegmentSize);
        }
        repaint();
    }
}
