package comp1110.lectures.J10;

public class Boxing {
    public static void main(String[] args) {
        Integer a = new Integer(6);
        Integer b = 6;

        Object z = a;
        Integer y = (Integer)(z);

        int c = a;
        int d = new Integer(6);

        if (a == b) {
            System.out.println("a("+a+") == b("+b+")");
        } else {
            System.out.println("a("+a+") != b("+b+")");
        }

        if (a.equals(b)) {
            System.out.println("a("+a+") equals b("+b+")");
        } else {
            System.out.println("a("+a+") does not equal b("+b+")");
        }

        if (a == c) {
            System.out.println("a("+a+") == c("+c+")");
        } else {
            System.out.println("a("+a+") != c("+c+")");
        }

        if (a.equals(c)) {
            System.out.println("a("+a+") equals c("+c+")");
        } else {
            System.out.println("a("+a+") does not equal c("+c+")");
        }
    }
}
