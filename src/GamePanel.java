import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
    private final int gameSegmentSize = 20;
    private final Point2D.Double mousePosition = new Point2D.Double();
    private double gameSpeed = 3;
    private boolean speedUp = false;
    private Timer timer = new Timer(16, this); /* timer, to nawet nie jest on */
    //private GameBoard gameBoard;
    private JLabel scoreText = new JLabel("Score: 0");

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());

        scoreText.setFont(new Font("Arial", Font.PLAIN, 32));
        scoreText.setBackground(Color.WHITE);
        this.add(scoreText);

        addMouseListener(this);
        addMouseMotionListener(this);
        timer.start();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        mousePosition.setLocation(e.getX(), e.getY());

        if (!speedUp) {
            gameSpeed *= 2;
            speedUp = true;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        mousePosition.setLocation(e.getX(), e.getY());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouseClicked");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!speedUp) {
            gameSpeed *= 2;
            speedUp = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (speedUp) {
            gameSpeed /= 2;
            speedUp = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GREEN);
        Ellipse2D.Double snakeSegmentImg;

        for (int i = 0; i < GameBoard.snake.bodySegments.size(); i++) {
            snakeSegmentImg = new Ellipse2D.Double(GameBoard.snake.bodySegments.get(i).getX() - gameSegmentSize / 2,
                    GameBoard.snake.bodySegments.get(i).getY() - gameSegmentSize / 2, gameSegmentSize, gameSegmentSize);
            g2d.fill(snakeSegmentImg);
        }

        Ellipse2D.Double foodImg = new Ellipse2D.Double(GameBoard.food.getX() - gameSegmentSize / 2,
                GameBoard.food.getY() - gameSegmentSize / 2, gameSegmentSize, gameSegmentSize);
        g2d.setColor(Color.RED);
        g2d.fill(foodImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateDirection();
        repaint();
    }

    private void updateDirection() {
        double mouseVectorX = mousePosition.getX() - GameBoard.snake.bodySegments.get(0).getX();
        double mouseVectorY = mousePosition.getY() - GameBoard.snake.bodySegments.get(0).getY();
        double mouseVectorLength = Math.sqrt(mouseVectorX * mouseVectorX + mouseVectorY * mouseVectorY);
        double dirX = mouseVectorX / mouseVectorLength;
        double dirY = mouseVectorY / mouseVectorLength;

        if (GameBoard.snake.bodySegments.size() > 2) {
            double bodyVectorX = GameBoard.snake.bodySegments.get(2).getX() - GameBoard.snake.bodySegments.get(0).getX();
            double bodyVectorY = GameBoard.snake.bodySegments.get(2).getY() - GameBoard.snake.bodySegments.get(0).getY();
            double bodyVectorLength = Math.sqrt(bodyVectorX * bodyVectorX + bodyVectorY * bodyVectorY);
            double scalarProd = mouseVectorX * bodyVectorX + mouseVectorY * bodyVectorY;
            double cosVectors = scalarProd / (mouseVectorLength * bodyVectorLength);

            if (cosVectors > 0.5) { //magic number - przybliżona wartość cosinusa 60 stopni
                dirX = -bodyVectorX / bodyVectorLength;
                dirY = -bodyVectorY / bodyVectorLength;
            }
        }
        GameBoard.snake.bodySegments.get(0).setLocation(GameBoard.snake.bodySegments.get(0).getX() + dirX * gameSpeed, GameBoard.snake.bodySegments.get(0).getY() + dirY * gameSpeed);
        scoreText.setText("Score: " + (GameBoard.snake.bodySegments.size() - 1));
    }
}