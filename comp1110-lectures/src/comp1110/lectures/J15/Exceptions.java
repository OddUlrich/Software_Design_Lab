package comp1110.lectures.J15;

import java.util.Date;

public class Exceptions {
    private static void exceptionalMethod() {
        System.out.println("Start of exceptionalMethod()");
        try {
            Date d = null;
            System.out.println(d.toString()); // throws a NullPointerException
            int i = 7 / 0; // throws an ArithmeticException
            System.out.println(i);
        } catch (ArithmeticException e) {
            System.out.println("Caught an arithmetic exception!");
        } catch (NullPointerException e) {
            System.out.println("Caught a null pointer exception!");
        } catch (Exception e) {
            System.out.println("Caught a generic exception!");
        } finally {
            System.out.println("In finally clause");
        }
        System.out.println("End of exceptionalMethod()");
    }

    public static void main(String[] args) {
        System.out.println("Start of main()");
        exceptionalMethod();
        System.out.println("End of main()");
    }
}
