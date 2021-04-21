package snakegame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Snake {
    private final double safeHeight, safeWidth, segmentSize;
    private final int sizeMultiplier;
    private final Vector velocity;
    private ArrayList<Vector> bodySegments;

    public Snake() {
        this.sizeMultiplier = GameSettings.sizeMultiplier;
        this.segmentSize = GameSettings.segmentSize;
        this.safeWidth = GameSettings.WIDTH - this.segmentSize;
        this.safeHeight = GameSettings.HEIGHT - this.segmentSize;
        bodySegments = new ArrayList<>();
        bodySegments.add(new Vector(ThreadLocalRandom.current().nextDouble(this.segmentSize, this.safeWidth),
                ThreadLocalRandom.current().nextDouble(this.segmentSize, this.safeHeight)));
        this.velocity = new Vector(0, 0);
        //this.segmentSize = segmentSize;
    }

    public void updateHeadLocation(Vector mouse) {
        Vector dir = Vector.subtract(mouse, bodySegments.get(0));
        dir.normalize();
        dir.multiply(0.5);
        velocity.add(dir);
        velocity.limit(GameSettings.maxSpeed);
        bodySegments.get(0).add(velocity);
    }

    public void move() {
        for (int i = bodySegments.size() - 1; i > 0; i--) {
            bodySegments.get(i).set(bodySegments.get(i - 1).getX(), bodySegments.get(i - 1).getY());
        }
    }

    public int getSize() {
        return bodySegments.size();
    }

    public Vector get(int i) {
        return bodySegments.get(i);
    }




    public void addBodySegment() {
        Vector newBodySegment;
        if(bodySegments.size() == 0) {
            newBodySegment = new Vector(bodySegments.get(0).getX(), bodySegments.get(0).getY());
        } else {
            newBodySegment = new Vector(bodySegments.get(bodySegments.size() - 1).getX(), bodySegments.get(bodySegments.size() - 1).getY());
        }
        bodySegments.add(newBodySegment);
    }
}