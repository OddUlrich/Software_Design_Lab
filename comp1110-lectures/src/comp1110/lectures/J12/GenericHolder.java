package comp1110.lectures.J12;

/**
 * A container that can hold an object of a particular (reference) type.
 * @param <T> type parameter: the type of object that this container can hold
 */
public class GenericHolder<T> {
    static class A {
        @Override
        public String toString() {
            return "This is an A";
        }
    }

    private T value;

    GenericHolder(T value) {
        this.value = value;
    }

    T getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    public static void main(String[] args) {
        GenericHolder<Integer> h = new GenericHolder<>(17);
        System.out.println(h);

        GenericHolder<String> s = new GenericHolder<>("Hello");
        System.out.println(s);

        GenericHolder<A> a = new GenericHolder<>(new A());
        System.out.println(a);
    }
}
