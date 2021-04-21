package snakegame;

public class Vector {

    private double x;
    private double y;

    public Vector(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }

    public double getY() { return y; }

    public void normalize() {
        double l = length();
        if(l != 0 && l != 1) {
            divide(l);
        }
    }

    public void divide(double value) {
        x /= value;
        y /= value;
    }

    public void multiply(double value) {
        x *= value;
        y *= value;
    }

    public void add(Vector v) {
        x += v.x;
        y += v.y;
    }

    public void subtract(Vector v) {
        x -= v.x;
        y -= v.y;
    }

    public void limit(double max) {
        if(length() > max) {
            normalize();
            multiply(max);
        }
    }

    public double length() {
        return Math.sqrt(x*x + y*y);
    }

    public static Vector subtract(Vector v1, Vector v2) {
        return subtract(v1, v2, null);
    }

    public static Vector subtract(Vector v1, Vector v2, Vector target) {
        if(target == null) {
            target = new Vector(v1.x - v2.x, v1.y - v2.y);
        }
        else {
            target.set(v1.x - v2.x, v1.y - v2.y);
        }
        return target;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }
}
