package comp1110.lectures.A01;

public class ArrayListTest extends ListTest {
    @Override
    public <T> List<T> createList() {
        return new ArrayList();
    }
}
