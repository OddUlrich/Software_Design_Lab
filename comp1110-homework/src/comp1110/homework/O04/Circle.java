package comp1110.homework.O04;

public class Circle extends Shape{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    Circle(double length, double x, double y) {
        this.radius = length;
        this.x = x;
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public boolean isPointInside(double xPos, double yPos) {
        if (distanceOfPoint(xPos, yPos, x, y) < radius) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double distanceOfPoint(double x1, double y1, double x2, double y2) {
        double dist;
        dist = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        return dist;
    }

    @Override
    public boolean overlaps(Shape other) {
        if (other instanceof Circle) {
            double sumLen = ((Circle) other).radius + radius;
            if (sumLen > distanceOfPoint(other.x, other.y, x, y)) {
                return true;
            }
        } else if (other instanceof Square) {
            if (((Square) other).isPointInside(x, y)) {
                return true;
            } else if ((x > ((Square) other).leftX) && (x < ((Square) other).rightX)) {  // in x range
                if (Math.abs(((Square) other).y - y) < ((Square) other).getSideLength()/2 + radius) {
                    return true;
                }
            } else if ((y > ((Square) other).downY) && (y < ((Square) other).upY)) {  // in y range
                if (Math.abs(((Square) other).x - x) < ((Square) other).getSideLength() / 2 + radius) {
                    return true;
                }
            } else if (isPointInside(((Square) other).leftX, ((Square) other).upY)
                    || isPointInside(((Square) other).rightX, ((Square) other).upY)
                    || isPointInside(((Square) other).leftX, ((Square) other).downY)
                    || isPointInside(((Square) other).rightX, ((Square) other).downY)) {
                return true;
            }
        }
        return false;
    }
}
