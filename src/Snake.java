import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Snake {
    public ArrayList<Point2D.Double> bodySegments;

    Snake(int x, int y) {
        bodySegments = new ArrayList<>();
        bodySegments.add(0, new Point2D.Double(x, y) );
    }

    public void move() {
        for (int i = bodySegments.size() - 1; i > 0; i--) {
            bodySegments.get(i).setLocation(bodySegments.get(i - 1));
        }
    }

    public void addBodySegment() {
        Point2D.Double newBodySegment;
        newBodySegment = new Point2D.Double(bodySegments.get(bodySegments.size() - 1).getX(),
                bodySegments.get(bodySegments.size() - 1).getY());
        bodySegments.add(newBodySegment);
    }
}
