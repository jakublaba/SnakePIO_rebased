import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
    private final int gameSegmentSize = 20;
    private Point2D.Double mousePosition = new Point2D.Double();
    private double gameSpeed = 3;
    private boolean speedUp = false;
    private boolean mouseInWindow = false;
    private Timer timer = new Timer(0, this);
    private GameBoard gameBoard;

    public GamePanel() {
        gameBoard = new GameBoard(800, 800, gameSegmentSize);
        this.setBackground(Color.BLACK);
        GameBoard.snake = new Snake(ThreadLocalRandom.current().nextInt(gameSegmentSize, 800 - gameSegmentSize),
                ThreadLocalRandom.current().nextInt(gameSegmentSize, 800 - gameSegmentSize));
        addMouseListener(this);
        addMouseMotionListener(this);
        timer.start();
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
        for(int i = 0; i < GameBoard.snake.bodySegments.size(); i++) {
            snakeSegmentImg = new Ellipse2D.Double(GameBoard.snake.bodySegments.get(i).getX() - gameSegmentSize/2, GameBoard.snake.bodySegments.get(i).getY() - gameSegmentSize/2, gameSegmentSize, gameSegmentSize);
            g2d.fill(snakeSegmentImg);
        }
        Ellipse2D.Double foodImg = new Ellipse2D.Double(gameBoard.food.getX() - gameSegmentSize/2, gameBoard.food.getY() - gameSegmentSize/2, gameSegmentSize, gameSegmentSize);
        g2d.setColor(Color.RED);
        g2d.fill(foodImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(mouseInWindow) {
            double mouseVectorX = mousePosition.getX() - GameBoard.snake.bodySegments.getFirst().getX();
            double mouseVectorY = mousePosition.getY() - GameBoard.snake.bodySegments.getFirst().getY();
            double mouseVectorLength = Math.sqrt(mouseVectorX*mouseVectorX + mouseVectorY*mouseVectorY);
            double dirX = mouseVectorX/mouseVectorLength;
            double dirY = mouseVectorY/mouseVectorLength;
            if (GameBoard.snake.bodySegments.size() > 2) {
                double bodyVectorX = GameBoard.snake.bodySegments.get(2).getX() - GameBoard.snake.bodySegments.getFirst().getX();
                double bodyVectorY = GameBoard.snake.bodySegments.get(2).getY() - GameBoard.snake.bodySegments.getFirst().getY();
                double bodyVectorLength = Math.sqrt(bodyVectorX * bodyVectorX + bodyVectorY * bodyVectorY);
                double scalarProd = mouseVectorX * bodyVectorX + mouseVectorY * bodyVectorY;
                double cosVectors = scalarProd / (mouseVectorLength * bodyVectorLength);
                /*
                System.out.printf("Mouse vector = [%f, %f]\n", mouseVectorX, mouseVectorY);
                System.out.printf("Body vector = [%f, %f]\n", bodyVectorX, bodyVectorY);
                System.out.printf("Scalar prod of body and mouse vectors: %f\n", scalarProd);
                System.out.printf("cos of an angle between vectors: %f\n", cosVectors);
                 */
                if (cosVectors > 0.5) { //magic number - przybliżona wartość cosinusa 60 stopni
                    dirX = -bodyVectorX / bodyVectorLength;
                    dirY = -bodyVectorY / bodyVectorLength;
                }
            }
            GameBoard.snake.bodySegments.getFirst().setLocation(GameBoard.snake.bodySegments.getFirst().getX() + dirX*gameSpeed, GameBoard.snake.bodySegments.getFirst().getY() + dirY*gameSpeed);

            gameBoard.checkBorderCollision(gameSegmentSize);
            gameBoard.checkTailCollision(gameSegmentSize);
            if(gameBoard.checkFood(gameSegmentSize)) {
                System.out.printf("Food at (%f, %f) location collected by head at (%f, %f) location\n", gameBoard.food.getX(), gameBoard.food.getY(), GameBoard.snake.bodySegments.getFirst().getX(), GameBoard.snake.bodySegments.getFirst().getY());
                gameBoard.respawnFood(gameSegmentSize);
                GameBoard.snake.addBodySegment();

                //do sprawdzania pozycji części snejka
                System.out.printf("SIZE: (%d)\n", GameBoard.snake.bodySegments.size());
                for (int i = GameBoard.snake.bodySegments.size() - 1; i > 1; i--) {
                    System.out.printf("Segment %d at (%f, %f) location\n", i, GameBoard.snake.bodySegments.get(i).getX(), GameBoard.snake.bodySegments.get(i).getY());
                }
            }
            GameBoard.snake.move();
            repaint();
        }
    }
}