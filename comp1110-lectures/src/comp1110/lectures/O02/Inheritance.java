package comp1110.lectures.O02;

import comp1110.lectures.O01.Person;

public class Inheritance {
    public static void main(String[] args) {
        COMP1110Student fred = new COMP1110Student("Fred", 23, "u1234567", 4, 4, 5, 4, 5, 5, 45);
        System.out.println(fred + " got final grade " + fred.getGrade());
        Person mary = new COMP1110Student("Mary", 25, "u3233567", 4, 4, 5, 4, 5, 5, 49);
        System.out.println(mary);
    }
}
