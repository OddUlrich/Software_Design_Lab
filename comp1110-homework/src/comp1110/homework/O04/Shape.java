package comp1110.homework.O04;

public abstract class Shape {
    protected double x;
    protected double y;

    abstract public double perimeter();
    abstract public double area();
    abstract public boolean overlaps(Shape other);

    abstract public boolean isPointInside(double xPos, double yPos);
    abstract public double distanceOfPoint(double x1, double y1, double x2, double y2);

    public static void main(String[] args) {
        Shape s1 = new Square(10);    // a square with sides of 10.0 units
        Shape s2 = new Circle(1.0);   // a circle of radius 1.0 units
        System.out.println(s1.perimeter());
        System.out.println(s1.area());
        System.out.println(s2.perimeter());
        System.out.println(s2.area());
    }
}
