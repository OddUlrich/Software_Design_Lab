package comp1110.lectures.O04;

public class Inheritance {
    public static void main(String[] args) {
        Human jane = new Human("Jane");

        Animal[] animals = new Animal[]{
                jane,
                new Snake("Simon"),
                new Toad("Donald")
        };

        Reptile someReptile = new Snake("Fred");

        for (Animal a : animals) {
            if (a instanceof Poisonous) {
                if (a instanceof Reptile) {
                    System.out.println(a + " and a scary poisonous reptilian overlord");
                } else {
                    System.out.println(a + " and a scary thing");
                }
            } else {
                System.out.println(a);
            }
        }
    }
}
