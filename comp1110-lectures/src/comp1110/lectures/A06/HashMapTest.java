package comp1110.lectures.A06;

public class HashMapTest extends MapTest {
    @Override
    public <K extends Comparable<K>, V> Map<K, V> createMap() {
        return new HashMap<>();
    }
}
