package comp1110.lectures.O04;

public abstract class Animal {
    protected String name;

    public String getName() {
        return name;
    }

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " is a " + getClass().getSimpleName();
    }
}
