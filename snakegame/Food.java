package snakegame;

import java.util.concurrent.ThreadLocalRandom;

public class Food {
    protected final PointVector position;

    public Food() {
        final var segmentSize = GameSettings.SEGMENT_SIZE;
        final var safeWidth = GameSettings.WIDTH - segmentSize;
        final var safeHeight = GameSettings.HEIGHT - segmentSize;

        this.position = new PointVector(ThreadLocalRandom.current().nextDouble(segmentSize, safeWidth),
                ThreadLocalRandom.current().nextDouble(segmentSize, safeHeight));
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
        final var safeWidth = GameSettings.WIDTH - segmentSize;
        final var safeHeight = GameSettings.HEIGHT - segmentSize;

        this.position.set(ThreadLocalRandom.current().nextDouble(segmentSize, safeWidth),
                ThreadLocalRandom.current().nextDouble(segmentSize, safeHeight));
    }
}
