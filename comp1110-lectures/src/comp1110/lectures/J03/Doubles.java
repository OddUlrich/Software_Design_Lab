package comp1110.lectures.J03;

public class Doubles {
    public static void main(String[] args) {
        double x = 3.2;
        System.out.println("x: " + x);
        double y = 1.234e-2;
        System.out.println("y: " + y);
        String s = "5.4123";
        double z = Double.parseDouble(s);
        z = z + 1.0;
        System.out.println("z: " + z);
    }
}
