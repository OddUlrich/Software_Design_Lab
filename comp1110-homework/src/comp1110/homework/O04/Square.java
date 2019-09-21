package comp1110.homework.O04;

public class Square extends Shape{
    private double sideLength;

    public double leftX;
    public double rightX;
    public double upY;
    public double downY;

    Square(double sideLength) {
        this.sideLength = sideLength;
    }

    Square(double length, double x, double y) {
        this.sideLength = length;
        this.x = x;
        this.y = y;
        leftX  = x - sideLength/2;
        rightX = x + sideLength/2;
        upY    = y + sideLength/2;
        downY  = y - sideLength/2;
    }

    public double getSideLength() {
        return sideLength;
    }

    @Override
    public double perimeter() {
        return sideLength * 4;
    }

    @Override
    public double area() {
        return Math.pow(sideLength, 2);
    }

    @Override
    public boolean isPointInside(double xPos, double yPos) {
        if ((xPos > leftX) && (xPos < rightX)
            && (yPos > downY) && (yPos < upY)) {
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
        Square smallSquare;
        Square largeSquare;

        if (other instanceof Square) {
            if (((Square) other).sideLength > sideLength) {
                smallSquare = this;
                largeSquare = (Square) other;
            } else {
                smallSquare = (Square) other;
                largeSquare = this;
            }

            /*
            *   1. Small square has one angel point in the big square.
            *   2. If their sides overlap, the middle point of one side in small square must be inside the big square.
            *   3. If they perfectly overlap, the center point must be inside the other one.
             */
            if (largeSquare.isPointInside(smallSquare.leftX, smallSquare.upY)
                || largeSquare.isPointInside(smallSquare.rightX, smallSquare.upY)
                || largeSquare.isPointInside(smallSquare.leftX, smallSquare.downY)
                || largeSquare.isPointInside(smallSquare.rightX, smallSquare.downY)
                || largeSquare.isPointInside(smallSquare.x, smallSquare.upY)
                || largeSquare.isPointInside(smallSquare.x, smallSquare.downY)
                || largeSquare.isPointInside(smallSquare.leftX, smallSquare.y)
                || largeSquare.isPointInside(smallSquare.rightX, smallSquare.y)
                || largeSquare.isPointInside(smallSquare.x, smallSquare.y)) {
                return true;
            }
        }
        else if (other instanceof Circle) {
            if (isPointInside(other.x, other.y)) {
                return true;
            } else if ((other.x > leftX) && (other.x < rightX)) {  // in x range
                if (Math.abs(other.y - y) < sideLength/2 + ((Circle) other).getRadius()) {
                    return true;
                }
            } else if ((other.y > downY) && (other.y < upY)) {  // in y range
                if (Math.abs(other.x - x) < sideLength / 2 + ((Circle) other).getRadius()) {
                    return true;
                }
            } else if (((Circle) other).isPointInside(leftX, upY)
                        || ((Circle) other).isPointInside(rightX, upY)
                        || ((Circle) other).isPointInside(leftX, downY)
                        || ((Circle) other).isPointInside(rightX, downY)) {
                return true;
            }
        }

        return false;
    }
}
