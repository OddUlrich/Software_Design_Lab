package comp1110.lectures.A04;

import java.util.LinkedList;

public class HashSet<T> implements Set<T> {
    private static final int DEFAULT_SIZE = 4;
    private LinkedList<T>[] table;
    private int numElements = 0;

    public HashSet() {
        this(DEFAULT_SIZE);
    }

    public HashSet(int size) {
        table = new LinkedList[size];
    }

    @Override
    public boolean add(T element) {
        if (element == null) return false;
        int hashCode = element.hashCode();
        int bucket = Math.abs(hashCode % 4);
        if (table[bucket] == null) table[bucket] = new LinkedList<>();
        if (!table[bucket].contains(element)) {
            table[bucket].add(element);
            numElements++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(T element) {
        if (element == null) return false;
        int hashCode = element.hashCode();
        int bucket = hashCode % 4;
        if (table[bucket] != null) {
            boolean removed = table[bucket].remove(element);
            if (removed) numElements--;
            return removed;
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) return false;
        int hashCode = element.hashCode();
        int bucket = Math.abs(hashCode % 4);
        if (table[bucket] != null) {
            return table[bucket].contains(element);
        }
        return false;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public Object[] toArray() {
        Object[] elements = new Object[numElements];
        int i = 0;
        for (LinkedList<T> bucket : table) {
            if (bucket != null) {
                for (T entry : bucket) {
                    elements[i++] = entry;
                }
            }
        }
        return elements;
    }

    @Override
    public Set<T> newInstance() {
        return new HashSet<>();
    }
}
