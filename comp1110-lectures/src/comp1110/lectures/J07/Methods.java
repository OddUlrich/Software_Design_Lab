package comp1110.lectures.J07;

public class Methods {

    static void boo() {
        System.out.println("Boo!");
    }

    private static int add(int x, int y) {
        x = 1000;
        return x + y;
    }

    private static void changeArray(int[] a) {
        a[0] = 1000;
        for (int v : a) {
            System.out.println("inside changeArray method: " + v);
        }
    }

    public static void main(String[] args) {
        System.out.println("Start of main method");
        Methods.boo();
        int x = 8;
        int y = 3;
        int result = Methods.add(x, y);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("x + y = " + result);

        int[] a = {0, 1, 2, 3};
        Methods.changeArray(a);
        for (int v : a) {
            System.out.println("in main method: " + v);
        }
    }
}
