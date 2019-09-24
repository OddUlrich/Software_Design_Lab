package comp1110.lectures.J06;

public class ControlFlowFor {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            System.out.println("i is " + i);
        }
        for (int j = 10; j > 0; j--) {
            System.out.println("j is " + j);
        }

        int[] a = { 3, 5, 7, 11, 13, 17};
        for (int i = 0; i < a.length; i++) {
            System.out.println("a["+i+"] is " + a[i]);
        }

        System.out.println("Now enhanced");
        for (int v : a) {
            System.out.println("next value is " + v);
        }
    }
}
