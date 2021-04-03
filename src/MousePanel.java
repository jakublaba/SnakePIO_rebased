import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class MousePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
    private Point2D.Double mousePosition = new Point2D.Double();
    private Point2D.Double shapePosition = new Point2D.Double(new Random().nextDouble() * 800, new Random().nextDouble() * 800);
    private double gameSpeed = 1;
    private Timer timer = new Timer(0, this);
    public MousePanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        timer.start();
    }



    public void positiveAlterPointWithLineEquation(Point2D.Double pointToAlter, Point2D.Double linePoint1, Point2D.Double linePoint2) {
        double a = (linePoint1.getY() - linePoint2.getY())/(linePoint1.getX() - linePoint2.getX());
        double b = linePoint1.getY() - a*linePoint1.getX();
        Point2D.Double newPoint = new Point2D.Double(pointToAlter.getX() + gameSpeed, a * (pointToAlter.getX() + gameSpeed) + b);
        pointToAlter.setLocation(newPoint);
    }

    public void negativeAlterPointWithLineEquation(Point2D.Double pointToAlter, Point2D.Double linePoint1, Point2D.Double linePoint2) {
        double a = (linePoint1.getY() - linePoint2.getY())/(linePoint1.getX() - linePoint2.getX());
        double b = linePoint1.getY() - a*linePoint1.getX();
        Point2D.Double newPoint = new Point2D.Double(pointToAlter.getX() - gameSpeed, a * (pointToAlter.getX() - gameSpeed) + b);
        pointToAlter.setLocation(newPoint);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
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
        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePosition.setLocation(e.getX(), e.getY());
        System.out.println("mousePressed");
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        Ellipse2D.Double ell = new Ellipse2D.Double(shapePosition.getX() - 25, shapePosition.getY() - 25, 50, 50);
        g2d.fill(ell);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        double shapeX = shapePosition.getX();
        double shapeY = shapePosition.getY();
        if(shapeX < mousePosition.getX())
            shapeX += gameSpeed;
        if(shapeX > mousePosition.getX())
            shapeX -= gameSpeed;
        if(shapeY < mousePosition.getY())
            shapeY += gameSpeed;
        if(shapeY > mousePosition.getY())
            shapeY -= gameSpeed;
        shapePosition.setLocation(shapeX, shapeY);
        */
        if(shapePosition.getX() < mousePosition.getX())
            positiveAlterPointWithLineEquation(shapePosition, shapePosition, mousePosition);
        else if(shapePosition.getX() > mousePosition.getX())
            negativeAlterPointWithLineEquation(shapePosition, shapePosition, mousePosition);
        repaint();
    }
}
