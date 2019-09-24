package comp1110.lectures.J06;

public class ControlFlowWhile {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 5) {
            System.out.println("while -- i is " + i);
            i++;
        }

        i = 5;
        do {
            System.out.println("do while -- i is " + i);
            i = i + 1;
        } while (i <= 5);
    }
}
