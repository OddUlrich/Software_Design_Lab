package comp1110.lectures.A01;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public abstract class ListTest {

    public abstract <T> List<T> createList();

    @Test
    public void testAdd() {
        List<Integer> list = createList();
        assertEquals(list.size(), 0);
        list.add(42);
        assertEquals(list.size(), 1);
        list.add(17);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testGet() {
        List<Integer> list = createList();
        list.add(42);
        assertEquals(list.get(0), Integer.valueOf(42));
        list.add(17);
        assertEquals(list.get(1), Integer.valueOf(17));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmptyList() {
        List<Integer> list = createList();
        list.get(0); // throws an exception
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBounds() {
        List<Integer> list = createList();
        list.add(42);
        list.get(1); // throws an exception
    }

    @Test
    public void testRemove() {
        List<Integer> list = createList();
        list.add(42);
        Integer value = list.remove(0);
        assertEquals(list.size(), 0);
        assertEquals(value, Integer.valueOf(42));

        list.add(17);
        list.add(34);
        Integer value2 = list.remove(0);
        assertEquals(value2, Integer.valueOf(17));
        assertEquals(list.get(0), Integer.valueOf(34));
        assertEquals(list.size(), 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveEmptyList() {
        List<Integer> list = createList();
        list.remove(0); // throws an exception
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexOutOfBounds() {
        List<Integer> list = createList();
        list.add(42);
        list.remove(3); // throws an exception
    }

    @Test
    public void testReverse() {
        List<Integer> list = createList();
        list.add(17);
        list.add(34);
        list.reverse();

        assertEquals(list.get(0), Integer.valueOf(34));
        assertEquals(list.get(1), Integer.valueOf(17));
        assertEquals(list.size(), 2);
        // TODO check that no element 3, 4 etc.

        List<Integer> listOfOne = createList();
        listOfOne.add(11);
        listOfOne.reverse();
        assertEquals(listOfOne.get(0), Integer.valueOf(11));
        assertEquals(listOfOne.size(), 1);

        List<Integer> listOfZero = createList();
        listOfZero.reverse();
        assertEquals(listOfZero.size(), 0);
    }

}
