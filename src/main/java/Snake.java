import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

public final class Snake {
    private final double safeHeight, safeWidth, segmentSize;
    private final int sizeMultiplier;
    private final PointVector velocity;
    private final List<PointVector> bodySegments;

    public Snake() {
        sizeMultiplier = GameSettings.FOOD_MULTIPLIER;
        segmentSize = GameSettings.SEGMENT_SIZE;
        safeWidth = GameSettings.BOARD_WIDTH - segmentSize;
        safeHeight = GameSettings.BOARD_HEIGHT - segmentSize;

        bodySegments = new ArrayList<>();
        bodySegments.add(new PointVector(ThreadLocalRandom.current().nextDouble(this.segmentSize, this.safeWidth),
                ThreadLocalRandom.current().nextDouble(this.segmentSize, this.safeHeight)));
        this.velocity = new PointVector(0, 0);
    }
    public Snake(List<PointVector> preparedSnake){
        sizeMultiplier = GameSettings.FOOD_MULTIPLIER;
        segmentSize = GameSettings.SEGMENT_SIZE;
        safeWidth = GameSettings.BOARD_WIDTH - segmentSize;
        safeHeight = GameSettings.BOARD_HEIGHT - segmentSize;
        bodySegments = preparedSnake;
        this.velocity = new PointVector(0, 0);
    }
    public List<PointVector> getBodySegments() {
        return bodySegments;
    }

    public void move (PointVector mouse) {
        ListIterator<PointVector> mySnakeIterator = bodySegments.listIterator(); //creates the iterator

        var oldLocation = mySnakeIterator.next();   //save head location to temp
        var temporarySegment = oldLocation;

        var dir = PointVector.subtract(mouse, temporarySegment);
        dir.normalize();
        dir.multiply(0.5);
        velocity.add(dir);
        velocity.setConstantSpeed(GameSettings.MAX_SPEED);
        temporarySegment.add(velocity);
        mySnakeIterator.set(temporarySegment); //save new head location

        while (mySnakeIterator.hasNext()) {
            temporarySegment = oldLocation;
            oldLocation = mySnakeIterator.next();
            mySnakeIterator.set(new PointVector(temporarySegment.getX(), temporarySegment.getY()));
        }
    }

    // Returns size of bodySegments list divided by multiplier (user score)
    public int getSizeForUser() {
        return bodySegments.size() / sizeMultiplier;
    }

    // Returns actual size of bodySegments list
    public int getActualSize() {
        return bodySegments.size();
    }

    // Returns snake's head (first body segment)
    public PointVector getHead() {
        return bodySegments.get(0);
    }

    // Returns snake's tail (last body segment)
    public PointVector getTail() {
        return bodySegments.get(getActualSize() - 1);
    }

    // Adds n = GameSettings.SIZE_MULTIPLIER segments to the end of the snake
    public void addBodySegments() {
        for (int i = 0; i < GameSettings.FOOD_MULTIPLIER; i++) {
            var newBodySegment = new PointVector(getTail());
            bodySegments.add(newBodySegment);
        }
    }
}