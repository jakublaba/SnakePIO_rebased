package snakegame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Snake {

    private final Vector velocity;
    private final float maxSpeed;

    private final double segmentSize;
    public static ArrayList<Vector> bodySegments = new ArrayList<>();

    public Snake(double segmentSize) {
        bodySegments.add(new Vector(ThreadLocalRandom.current().nextDouble(segmentSize, GameSettings.WIDTH - segmentSize), ThreadLocalRandom.current().nextDouble(segmentSize, GameSettings.HEIGHT - segmentSize)));
        velocity = new Vector(0, 0);
        maxSpeed = 5;
        this.segmentSize = segmentSize;
    }

    public void updateHeadLocation(Vector mouse) {
        Vector dir = Vector.subtract(mouse, bodySegments.get(0));
        dir.normalize();
        dir.multiply(0.5);
        velocity.add(dir);
        velocity.limit(maxSpeed);
        bodySegments.get(0).add(velocity);
    }

    public void move() {
        for (int i = bodySegments.size() - 1; i > 0; i--) {
            bodySegments.get(i).set(bodySegments.get(i - 1).getX(), bodySegments.get(i - 1).getY());
        }
    }

    public void checkBorders() {
        if(bodySegments.get(0).getX() > GameSettings.WIDTH) {
            bodySegments.get(0).setX(0);
        } else if (bodySegments.get(0).getX() < 0) {
            bodySegments.get(0).setX(GameSettings.WIDTH);
        }

        if (bodySegments.get(0).getY() > GameSettings.HEIGHT) {
            bodySegments.get(0).setY(0);
        } else if (bodySegments.get(0).getY() < 0) {
            bodySegments.get(0).setY(GameSettings.HEIGHT);
        }
    }

    public void checkTailCollision() {
        if(bodySegments.size() > 10) {
            for(int i = 10; i < bodySegments.size(); i++) {
                Vector distance = new Vector(bodySegments.get(0).getX(), bodySegments.get(0).getY());
                distance.subtract(bodySegments.get(i));
                if(distance.length() < segmentSize / 2) {
                    System.out.printf("Game Over: Collision with tail segment number %d\n", i);
                    System.exit(1);
                }
            }
        }
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