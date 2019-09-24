package comp1110.lectures.A02;

import comp1110.lectures.A01.List;

public class LinkedList<T> implements List<T> {

    private LLNode first, last;

    class LLNode {
        T value;
        LLNode next;

        LLNode(T value, LLNode next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            if (next != null) {
                return value.toString() + " " + next.toString();
            } else {
                return value.toString();
            }
        }

        T getLL(int index) {
            if (index == 0) {
                return value;
            } else {
                if (next == null) {
                    throw new IndexOutOfBoundsException("Tried to traverse past the end of the list");
                }
                return next.getLL(index - 1);
            }
        }

        T removeLL(int index) {
            if (next == null) {
                throw new IndexOutOfBoundsException("Tried to traverse past the end of the list");
            }
            if (index == 1) {
                T value = next.value;
                next = next.next;
                return value;
            } else if (index > 1) {
                return next.removeLL(index - 1);
            } else {
                throw new IndexOutOfBoundsException("Tried to remove from before the start of the list");
            }
        }

        void reverse(LLNode newNext) {
            if (next != null) {
                next.reverse(this);
            }
            this.next = newNext;
        }
    }

    @Override
    public void add(T value) {
        LLNode newNode = new LLNode(value, null);
        if (first == null) {
            // list is empty
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    @Override
    public T get(int index) {
        if (first == null) {
            throw new IndexOutOfBoundsException("Tried to get from empty list");
        }
        return first.getLL(index);
        /*
        // non-recursive version
        int i = 0;
        LLNode node = first;
        while (node != null) {
            if (i == index) {
                return node.value;
            }
            node = node.next;
            i++;
        }
        throw new IndexOutOfBoundsException("Invalid index:" + index);
        */
    }

    @Override
    public int size() {
        int size = 0;
        LLNode node = first;
        while (node != null) {
            node = node.next;
            size++;
        }
        return size;
    }

    @Override
    public T remove(int index) {
        if (first == null) {
            throw new IndexOutOfBoundsException("Tried to remove from empty list");
        }
        if (index == 0) {
            T value = first.value;
            first = first.next;
            return value;
        }
        return first.removeLL(index);
    }

    @Override
    public void reverse() {
        if (first == null) {
            return;
        }
        first.reverse(null);
        LLNode tmp = last;
        last = first;
        first = tmp;
    }
}
