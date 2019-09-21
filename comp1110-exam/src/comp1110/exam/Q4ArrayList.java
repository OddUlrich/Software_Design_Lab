package comp1110.exam;


import java.util.Arrays;

/**
 * COMP1110 Final Exam, Question 4
 */
public class Q4ArrayList<T> {
    private static final int INITIAL_SIZE = 2;
    private static final double GROWTH_FACTOR = 1.5;

    T[] values = (T[]) new Object[INITIAL_SIZE];
    int elements = 0;

    /**
     * Add a value to the tail of the list.
     *
     * @param value The value to be added.
     */
    public void add(T value) {
        /* Unimplemented.  Q4 i) [7 Marks] */
        int newSize = elements + 1;
        T[] newValues = (T[]) new Object[newSize];

        newValues = Arrays.copyOf(values, newSize);
        newValues[newSize - 1] = value;
        values = newValues;
        elements++;
    }

    /**
     * Remove the value at the specified index from the list.
     *
     * @param index
     */
    public void remove(int index) {
        /* Unimplemented. Q4 ii) [7 Marks] */
        if (index >= elements || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            int newSize = elements - 1;
            T[] newValues = (T[]) new Object[newSize];

            int cnt = 0;
            for (int i = 0; i < elements; i++) {
                if (i == index) {
                    continue;
                } else {
                    newValues[cnt] = values[i];
                    cnt++;
                }
            }
            elements--;
            values = newValues;
        }
    }

    /**
     * @param index
     * @return The value at the specified index.
     */
    public T get(int index) {
        if (index >= elements || index < 0)
            throw new IndexOutOfBoundsException();
        return values[index];
    }

    /**
     * @return the current size of the list.
     */
    public int size() {
        return elements;
    }

    /**
     * Reverse the order of the elements of the list.
     */
    public void reverse() {
	    /* Unimplemented. Q4 iii) [6 Marks] */
        T[] revValues = (T[]) new Object[elements];

        for (int i = 0; i < elements; i++) {
            revValues[i] = values[elements-1-i];
        }
        values = revValues;
    }

    /**
     * @return A string representation of the list.
     */
    public String toString() {
        String rtn = "";
        for (int i = 0; i < elements; i++) {
            rtn += ((i != 0) ? " " : "") + values[i];
        }
        return rtn;
    }
}
