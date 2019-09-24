package comp1110.lectures.J12;

/**
 * A container that holds an int.
 */
public class IntHolder {
    private int value;

    IntHolder(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    public static void main(String[] args) {
        IntHolder h = new IntHolder(17);
        System.out.println(h);
    }

}
