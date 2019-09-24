package comp1110.lectures.O01;

public class Objects {
    public static void main(String[] args) {
        Person mary = new Person("Mary", 22);
        Person fred = new Person("Fred", 20);
        System.out.println("What I know about mary: " + mary.toString());
        System.out.println("What I know about fred: " + fred.toString());
    }
}
