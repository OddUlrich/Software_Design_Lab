package comp1110.lectures.A06;

public class BSTMapTest extends MapTest {
    @Override
    public <K extends Comparable<K>, V> Map<K, V> createMap() {
        return new BSTMap<>();
    }
}
