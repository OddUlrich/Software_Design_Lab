package comp1110.lectures.C01;

public class MaternalLine {

    /**
     * Return a string representation of our maternal ancestor
     * n generations in the past.
     *
     * @param n number of generations >= 1 e.g. 1 = mother 2 = grandmother
     * @return
     * @throws IllegalArgumentException if n < 1
     */
    static String maternalAncestor(int n) {
        //assert n >= 1;
        if (n < 1) {
            throw new IllegalArgumentException("Can't get an ancestor for n = " + n);
        }
        if (n == 1) {
            return "mother";
        } else if (n == 2) {
            return "grandmother";
        } else {
            return "great-" + maternalAncestor(n-1);
        }
    }

    static void maternalAncestorPrintLn(int n) {
        //assert n >= 1;
        if (n < 1) {
            throw new IllegalArgumentException("Can't get an ancestor for n = " + n);
        }
        if (n == 1) {
            System.out.print("mother");
        } else if (n == 2) {
            System.out.print("grandmother");
        } else {
            System.out.print("great-");
            maternalAncestorPrintLn(n-1);
        }
    }

    public static void main(String[] args) {
        System.out.println("My maternal ancestor order 4 is my " + maternalAncestor(4));
        //System.out.println("My maternal ancestor order 0 is my " + maternalAncestor(0)); // error
        System.out.print("My maternal ancestor order 5 is my ");
        maternalAncestorPrintLn(5);

    }
}
