package comp1110.lectures.A01;

public interface List<T> {
    void add(T value);

    /**
     * Gets the element at index i
     * @param index the index of the element to get
     * @return the element
     * @throws IndexOutOfBoundsException if the specified element does not exist
     */
    T get(int index);

    int size();
    T remove(int index);
    void reverse();
}
