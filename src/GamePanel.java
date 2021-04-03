import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
    private final int snakeSegmentSize = 50;
    private Point2D.Double mousePosition = new Point2D.Double();
    private Point2D.Double shapePosition;
    private double gameSpeed = 2.5;
    private boolean speedUp = false;
    private boolean mouseInWindow = true;
    private Timer timer = new Timer(0, this);
    private GameBoard gameBoard;

    public GamePanel() {
        gameBoard = new GameBoard(800, 800);
        Random rand = new Random();
        shapePosition = new Point2D.Double(rand.nextDouble() * 800, rand.nextDouble() * 800);
        addMouseListener(this);
        addMouseMotionListener(this);
        timer.start();
    }

    private void checkBorderCollision() {
        if(shapePosition.getX() >= 800 - snakeSegmentSize/2 || shapePosition.getX() <= snakeSegmentSize/2 || shapePosition.getY() >= 800 - snakeSegmentSize/2 || shapePosition.getY() <= snakeSegmentSize/2) {
            System.out.println("Game Over");
            System.exit(1);
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
        Ellipse2D.Double ell = new Ellipse2D.Double(shapePosition.getX() - snakeSegmentSize/2, shapePosition.getY() - snakeSegmentSize/2, snakeSegmentSize, snakeSegmentSize);
        g2d.fill(ell);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double vectorX = mousePosition.getX() - shapePosition.getX();
        double vectorY = mousePosition.getY() - shapePosition.getY();
        double vectorLength = Math.sqrt(vectorX*vectorX + vectorY*vectorY);
        double dirX = vectorX/vectorLength;
        double dirY = vectorY/vectorLength;
        shapePosition.setLocation(shapePosition.getX() + dirX*gameSpeed, shapePosition.getY() + dirY*gameSpeed);
        checkBorderCollision();
        repaint();
    }
}
