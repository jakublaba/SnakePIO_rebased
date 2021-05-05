package snakegame;

public class Saw {
    private final PointVector location;
    private double speedX, speedY;
    private double size;
    private double maxTop, maxBottom, maxLeft, maxRight;

    public Saw(double speed, double size) {
        location = new PointVector(GameSettings.BOARD_WIDTH / 2, GameSettings.BOARD_HEIGHT / 2);
        this.speedX = this.speedY = speed;
        this.size = size;
        this.maxTop = this.maxLeft = size / 2;
        this.maxBottom = this.maxRight = GameSettings.BOARD_HEIGHT - size / 2;
    }

    public PointVector getLocation() { return location; }

    public double getX() { return location.getX(); }

    public double getY() { return location.getY(); }

    public double getSize() { return size; }

    public void verticalMove() {
        if(location.getY() <= maxTop || location.getY() >= maxBottom) speedY *= -1;
        location.setY(location.getY() + speedY);
    }
    public void horizontalMove() {
        if(location.getX() <= maxLeft || location.getX() >= maxRight) speedX *= -1;
        location.setX(location.getX() + speedX);
    }

    public void diagonalMoveUp() {
        if(location.getX() <= maxLeft || location.getX() >= maxRight) speedX *= -1;
        if(location.getY() <= maxTop || location.getY() >= maxBottom) speedY *= -1;
        location.setX(location.getX() + speedX);
        location.setY(location.getY() - speedY);
    }

    public void diagonalMoveDown() {
        if(location.getX() <= maxLeft || location.getX() >= maxRight) speedX *= -1;
        if(location.getY() <= maxTop || location.getY() >= maxBottom) speedY *= -1;
        location.setX(location.getX() + speedX);
        location.setY(location.getY() + speedY);
    }
}
