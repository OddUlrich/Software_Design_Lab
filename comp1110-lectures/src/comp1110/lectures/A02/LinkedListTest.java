package comp1110.lectures.A02;

import comp1110.lectures.A01.ArrayList;
import comp1110.lectures.A01.List;
import comp1110.lectures.A01.ListTest;

public class LinkedListTest extends ListTest {
    @Override
    public <T> List<T> createList() {
        return new LinkedList();
    }
}
