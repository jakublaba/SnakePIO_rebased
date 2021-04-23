package snakegame;

import java.util.concurrent.ThreadLocalRandom;

public class Food {
    private final double safeHeight, safeWidth, segmentSize;
    private PointVector position;

    public Food() {
        this.segmentSize = GameSettings.segmentSize;
        this.safeWidth = GameSettings.WIDTH - this.segmentSize;
        this.safeHeight = GameSettings.HEIGHT - this.segmentSize;
        this.position = new PointVector(ThreadLocalRandom.current().nextDouble(this.segmentSize, safeWidth),
                ThreadLocalRandom.current().nextDouble(this.segmentSize, safeHeight));
    }

    public PointVector getPosition() {
        return this.position;
    }

    public double getX() {
        return this.position.getX();
    }

    public double getY() {
        return this.position.getY();
    }

    public void respawn() {
        final var segmentSize = GameSettings.SEGMENT_SIZE;
        final var safeWidth = GameSettings.BOARD_WIDTH - segmentSize;
        final var safeHeight = GameSettings.BOARD_HEIGHT - segmentSize;

        this.position.set(ThreadLocalRandom.current().nextDouble(segmentSize, safeWidth),
                ThreadLocalRandom.current().nextDouble(segmentSize, safeHeight));
    }
}
