package comp1110.lectures.J05;

public class ControlFlowSwitch {
    public static void main(String[] args) {
        int c = 1;
        switch (c) {
            case 0:
                System.out.println("c == 0");
                break;
            case 1:
                System.out.println("c == 1");
                break;
            case 2:
                System.out.println("c == 2");
                break;
            default:
                System.out.println("It was something else");
        }
    }
}
