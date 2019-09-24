package comp1110.lectures.O04;

public final class Human extends Mammal {
    //private String name; // DON'T DO THIS

    public Human(String name) {
        super(name);
        //this.name = name + " the Great";
    }

    /*
    @Override
    public String toString() {
        return super.name;
    }
    */

    @Override
    public String toString() {
        return super.toString() + " who likes dogs";
    }
}
