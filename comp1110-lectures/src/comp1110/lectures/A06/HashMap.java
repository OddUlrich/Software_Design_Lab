package comp1110.lectures.A06;

import comp1110.lectures.A04.HashSet;
import comp1110.lectures.A04.Set;

import java.util.LinkedList;

/**
 * An implementation of the Map abstract data type using a hash table.
 * @param <K> the key type for the map
 * @param <V> the value type for the map
 */
public class HashMap<K, V> implements Map<K, V> {
    class KVPair {
        K key;
        V value;

        KVPair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int DEFAULT_SIZE = 4;
    private LinkedList<KVPair>[] table;
    private int numElements = 0;

    public HashMap() {
        table = new LinkedList[DEFAULT_SIZE];
    }

    @Override
    public V put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("key is null");
        if (value == null) throw new IllegalArgumentException("value is null");
        int hashCode = key.hashCode();
        int bucket = Math.abs(hashCode % table.length);
        if (table[bucket] == null) table[bucket] = new LinkedList<>();
        for (KVPair pair : table[bucket]) {
            if (pair.key.equals(key)) {
                V previousValue = pair.value;
                pair.value = value;
                return previousValue;
            }
        }
        KVPair newPair = new KVPair(key, value);
        table[bucket].add(newPair);
        numElements++;
        return null;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("key is null");
        int hashCode = key.hashCode();
        int bucket = Math.abs(hashCode % table.length);
        if (table[bucket] != null) {
            for (KVPair pair : table[bucket]) {
                if (pair.key.equals(key)) {
                    return pair.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) throw new IllegalArgumentException("key is null");
        int hashCode = key.hashCode();
        int bucket = Math.abs(hashCode % table.length);
        if (table[bucket] != null) {
            for (int i = 0; i < table[bucket].size(); i++) {
                KVPair pair = table[bucket].get(i);
                if (pair.key.equals(key)) {
                    table[bucket].remove(i);
                    numElements--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int bucket = 0; bucket < table.length; bucket++) {
            table[bucket] = null;
        }
        numElements = 0;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public Set<K> getKeys() {
        Set<K> keys = new HashSet<>();
        for (LinkedList<KVPair> bucket : table) {
            if (bucket != null) {
                for (KVPair pair : bucket) {
                    keys.add(pair.key);
                }
            }
        }
        return keys;
    }
}
