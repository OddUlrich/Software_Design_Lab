package comp1110.homework.O04;

import java.util.ArrayList;
import java.util.Collection;

public class Sum extends ArrayList<Integer> {
    public int sum;

    public int sum() {
        return sum;
    }

    @Override
    public boolean add(Integer i) {
        sum += i;
        return super.add(i);
    }

    @Override
    public void add(int index, Integer i) {
        sum += i;
        super.add(index, i);
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        for (int i: c) {
            sum += i;
        }
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        for (int i: c) {
            sum += i;
        }
        return super.addAll(index, c);
    }
}
