package comp1110.lectures.J13;

import java.util.function.IntFunction;
import java.util.function.Predicate;

public class TypeInference {
    public static class ThingHolder<T> {
        private T value;

        public ThingHolder(T value) {
            this.value = value;
        }

        public static <U> ThingHolder<U> makeHolder(U value) {
            return new ThingHolder(value);
        }

        /*
        // invalid - can't infer return value
        public var returnAString() {
            return "This is a String";
        }
        */

        /*
        // invalid - can't return parameter type of method
        public int doSomething(var x) {
            return x + 1;
        }
        */

        @Override
        public String toString() {
            return "value: " + value;
        }
    }

    public static void main(String[] args) {
        var theAnswer = 42;

        ThingHolder<String> holder = new ThingHolder<>("Hello");
        var anotherHolder = new ThingHolder<>("Hi again");
        var yetAnotherHolder = ThingHolder.makeHolder(new Integer(42));

        Predicate<String> nonEmptyString = x -> x.length() > 0;

        //var increment = x -> x + 1; // invalid - can't infer type of x
        //var increment = (int x) -> x + 1; // invalid - can't infer functional interface of lambda expression
        IntFunction increment = x -> x + 1;

        //var nothing = null; // invalid - null has no type
        //var mystery; // invalid - can't infer type without initializer

    }
}
