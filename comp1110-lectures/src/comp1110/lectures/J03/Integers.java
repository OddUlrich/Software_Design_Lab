package comp1110.lectures.J03;

public class Integers {
    public static void main(String[] args) {
        int x = 123_456;
        System.out.println("x: " + x);
        int y = 0b110000;
        System.out.println("y: " + y);
        String s = "56";
        //s = s * 2;
        int z = Integer.parseInt(s);
        z = z * 2;
        System.out.println("z: " + z);
    }
}
