import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class Food {
    private final int safeHeight, safeWidth, segmentSize;
    private Point2D.Double position;

    public Food(int width, int height, int segment) {
        this.segmentSize = segment;
        this.safeWidth = width - this.segmentSize;
        this.safeHeight = height - this.segmentSize;
        this.position = new Point2D.Double(ThreadLocalRandom.current().nextDouble(this.segmentSize, safeWidth),
                ThreadLocalRandom.current().nextDouble(this.segmentSize, safeHeight));
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public void respawn() {
        this.position.setLocation(ThreadLocalRandom.current().nextDouble(this.segmentSize, this.safeWidth),
                ThreadLocalRandom.current().nextDouble(this.segmentSize, this.safeHeight));
    }
}
