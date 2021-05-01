package snakegame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class Snake {
    private final double safeHeight, safeWidth, segmentSize;
    private final int sizeMultiplier;
    private final pointVector velocity;
    private final List<pointVector> bodySegments;

    public Snake() {
        this.sizeMultiplier = GameSettings.sizeMultiplier;
        this.segmentSize = GameSettings.segmentSize;
        this.safeWidth = GameSettings.WIDTH - this.segmentSize;
        this.safeHeight = GameSettings.HEIGHT - this.segmentSize;
        bodySegments = new ArrayList<>();
        bodySegments.add(new pointVector(ThreadLocalRandom.current().nextDouble(this.segmentSize, this.safeWidth),
                ThreadLocalRandom.current().nextDouble(this.segmentSize, this.safeHeight)));
        this.velocity = new pointVector(0, 0);
    }

    public void updateHeadLocation(pointVector mouse) {
        pointVector dir = pointVector.subtract(mouse, bodySegments.get(0));
        dir.normalize();
        dir.multiply(0.5);
        velocity.add(dir);
        velocity.limit(GameSettings.maxSpeed);
        bodySegments.get(0).add(velocity);
    }

    //zamiana na operację agregacyjną, iterator lub pętlę forEach
    public void move() {
        for (int i = bodySegments.size() - 1; i > 0; i--) {
            bodySegments.get(i).set(bodySegments.get(i - 1).getX(), bodySegments.get(i - 1).getY());
        }
    }

    public int getSize() {
        return bodySegments.size();
    }

    public pointVector get(int i) {
        return bodySegments.get(i);
    }


    public void addBodySegment() {
        pointVector newBodySegment;
        newBodySegment = new pointVector(bodySegments.get(bodySegments.size() - 1).getX(), bodySegments.get(bodySegments.size() - 1).getY());
        bodySegments.add(newBodySegment);
    }
}