package comp1110.lectures.A01;

public class ArrayList<T> implements List<T> {

    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[0];
    }

    @Override
    public void add(T value) {
        // TODO grow the array by more, so we don't need to add an element every time
        T[] newValues = (T[]) new Object[values.length + 1];
        System.arraycopy(values, 0, newValues, 0, values.length);
        newValues[newValues.length - 1] = value;
        values = newValues;
    }

    @Override
    public T get(int index) {
        return values[index];
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public T remove(int index) {
        if (values.length == 0) {
            throw new IndexOutOfBoundsException("Can't remove an element from an empty ArrayList!");
        }
        T[] newValues = (T[]) new Object[values.length - 1];
        // could replace with a single for loop
        System.arraycopy(values, 0, newValues, 0, index);
        System.arraycopy(values, index + 1, newValues, index, values.length - index - 1);

        T v = values[index];
        values = newValues;
        return v;
    }

    @Override
    public void reverse() {
        T[] newValues = (T[]) new Object[values.length];
        // could replace with a single for loop
        for (int i=values.length-1; i>=0; i--) {
           newValues[values.length-i-1] = values[i];
        }
        values = newValues;
    }
}
