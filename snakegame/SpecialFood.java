package snakegame;

import java.util.concurrent.ThreadLocalRandom;

public class SpecialFood extends Food {
    private final int MAX_LONGEVITY = 300; //maximum longevity in "ticks"
    private final PointVector velocity;
    private int longevity; //this is a time in "ticks", which determines for how long will it be visible

    public SpecialFood() {
        super();
        this.velocity = new PointVector(0, 0);
        this.longevity = MAX_LONGEVITY;
    }

    public void move() {
        if (longevity > 0) {
            //there is a chance of not changing the velocity
            if (ThreadLocalRandom.current().nextInt(0, 10) >= 8) {
                var movementVector = generateRandomizedVector();
                velocity.add(movementVector);
            }

            this.position.add(velocity);
            velocity.setConstantSpeed(GameSettings.MAX_SPEED / 2.0);
            checkBordersAndTeleport();

            this.longevity -= 1;
        }
    }

    /*
     * generates a randomized vector which will be added to velocity
     */
    private PointVector generateRandomizedVector() {
        var randomVector = new PointVector(ThreadLocalRandom.current().nextDouble(-1.0, 1.0),
                ThreadLocalRandom.current().nextDouble(-1.0, 1.0));
        randomVector.multiply(0.5);
        return randomVector;
    }

    private void checkBordersAndTeleport() {
        if (this.position.getX() > GameSettings.WIDTH) {
            this.position.setX(0);
        } else if (this.position.getX() < 0) {
            this.position.setX(GameSettings.WIDTH);
        }

        if (this.position.getY() > GameSettings.HEIGHT) {
            this.position.setY(0);
        } else if (this.position.getY() < 0) {
            this.position.setY(GameSettings.HEIGHT);
        }
    }

    public int getLongevity() {
        return this.longevity;
    }

    public boolean isAlive() {
        return (this.longevity != 0);
    }

    public void setLongevity(int longevity) {
        this.longevity = longevity;
    }

    @Override
    public void respawn() {
        //20 percent chance of le epic food appearing
        if (ThreadLocalRandom.current().nextInt(0, 1000) >= 998) {
            final var segmentSize = GameSettings.SEGMENT_SIZE;
            final var safeWidth = GameSettings.WIDTH - segmentSize;
            final var safeHeight = GameSettings.HEIGHT - segmentSize;

            this.position.set(ThreadLocalRandom.current().nextDouble(segmentSize, safeWidth),
                    ThreadLocalRandom.current().nextDouble(segmentSize, safeHeight));
            this.longevity = MAX_LONGEVITY;
        }

    }

}
