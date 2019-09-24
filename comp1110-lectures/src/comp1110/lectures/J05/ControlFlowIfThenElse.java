package comp1110.lectures.J05;

public class ControlFlowIfThenElse {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        if (a) {
            if (b) {
                System.out.println("a is true and b is true");
            } else {
                System.out.println("a is true and b is false");
            }
        }
        if (b) {
            System.out.println("b is true");
        }

        if (a && b) {
            System.out.println("Both are true");
        } else {
            System.out.println("At least one is false");
        }
    }
}
