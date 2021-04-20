import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Snake {
    private final int safeHeight, safeWidth, segmentSize, sizeMultiplier;
    public ArrayList<Point2D.Double> bodySegments;

    Snake(int width, int height, int segmentSize, int sizeMultiplier) {
        this.sizeMultiplier = sizeMultiplier;
        this.segmentSize = segmentSize;
        this.safeWidth = width - this.segmentSize;
        this.safeHeight = height - this.segmentSize;
        bodySegments = new ArrayList<>();
        bodySegments.add(0, new Point2D.Double(ThreadLocalRandom.current().nextDouble(this.segmentSize, safeWidth),
                ThreadLocalRandom.current().nextDouble(this.segmentSize, safeHeight)) );
    }

    public void move() {
        for (int i = bodySegments.size() - 1; i > 0; i--) {
            bodySegments.get(i).setLocation(bodySegments.get(i - 1));
        }
    }

    public void addBodySegment() {
        for (int i = 0; i < sizeMultiplier; i++) {
            Point2D.Double newBodySegment;
            newBodySegment = new Point2D.Double(bodySegments.get(bodySegments.size() - 1).getX(),
                    bodySegments.get(bodySegments.size() - 1).getY());
            bodySegments.add(newBodySegment);
        }
    }
}
