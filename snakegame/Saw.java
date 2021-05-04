package snakegame;

public class Saw {
    private final PointVector location;
    private double speed;

    public Saw(double speed) {
        location = new PointVector(GameSettings.WIDTH / 2 - GameSettings.SEGMENT_SIZE / 2, GameSettings.HEIGHT / 2 - GameSettings.SEGMENT_SIZE / 2);
        this.speed = speed;
    }

    public PointVector getLocation() { return location; }

    public double getX() { return location.getX(); }

    public double getY() { return location.getY(); }

    //0 - vertical saw, 1 - horizontal saw
    public void move(int mode) {
        if(mode == 0) {
            double maxTop = 80;
            double maxBottom = 720;
            if(location.getY() <= maxTop || location.getY() >= maxBottom) {
                speed *= -1;
            }
            location.setY(location.getY() + speed);
        }
        if(mode == 1) {
            double maxLeft = 80;
            double maxRight = 720;
            if(location.getX() <= maxLeft || location.getX() >= maxRight) {
                speed *= -1;
            }
            location.setX(location.getX() + speed);
        }
    }
}
