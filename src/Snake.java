import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Snake {
    public LinkedList<Point2D.Double> bodySegments;

    Snake(int x, int y) {
        bodySegments = new LinkedList<>();
        bodySegments.add(0, new Point2D.Double(x, y) );
    }

    public void move() {
        for (int i = bodySegments.size() - 1; i > 0; i--) {
            bodySegments.get(i).setLocation(bodySegments.get(i - 1));
        }
    }

    public void addBodySegment() {
        Point2D.Double newBodySegment;
        newBodySegment = new Point2D.Double(bodySegments.getLast().getX(), bodySegments.getLast().getY());
        bodySegments.addLast(newBodySegment);
    }
}
