package snakegame;

import java.util.concurrent.ThreadLocalRandom;

public class Food {
    private final double safeHeight, safeWidth, segmentSize;
    private Vector position;

    public Food() {
        this.segmentSize = GameSettings.segmentSize;
        this.safeWidth = GameSettings.WIDTH - this.segmentSize;
        this.safeHeight = GameSettings.HEIGHT - this.segmentSize;
        this.position = new Vector(ThreadLocalRandom.current().nextDouble(this.segmentSize, safeWidth),
                ThreadLocalRandom.current().nextDouble(this.segmentSize, safeHeight));
    }

    public Vector getPosition() {
        return this.position;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }


    public void respawn() {
        this.position.set(ThreadLocalRandom.current().nextDouble(segmentSize, safeWidth),
                ThreadLocalRandom.current().nextDouble(segmentSize, safeHeight));
    }
}
