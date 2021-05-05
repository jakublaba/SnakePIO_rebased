package snakegame;

public class Saw {
    private final PointVector location;
    private double speed;
    private double size;

    public Saw(double speed, double size) {
        location = new PointVector(GameSettings.BOARD_WIDTH / 2 - GameSettings.SEGMENT_SIZE / 2, GameSettings.BOARD_HEIGHT / 2 - GameSettings.SEGMENT_SIZE / 2);
        this.speed = speed;
        this.size = size;
    }

    public PointVector getLocation() { return location; }

    public double getX() { return location.getX(); }

    public double getY() { return location.getY(); }

    public double getSize() { return size; }

    //0 - vertical saw, 1 - horizontal saw
    public void move(int mode) {
        if(mode == 0) {
            double maxTop = size / 2;
            double maxBottom = GameSettings.BOARD_HEIGHT - size / 2;
            if(location.getY() <= maxTop || location.getY() >= maxBottom) {
                speed *= -1;
            }
            location.setY(location.getY() + speed);
        }
        if(mode == 1) {
            double maxLeft = size / 2;
            double maxRight = GameSettings.BOARD_WIDTH - size / 2;
            if(location.getX() <= maxLeft || location.getX() >= maxRight) {
                speed *= -1;
            }
            location.setX(location.getX() + speed);
        }
    }
}
