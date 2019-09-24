package comp1110.lectures.C01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MergeSort {

    public static List<Integer> mergeSort(List<Integer> unsorted) {
        // an empty list, or a list of size 1, is already 'sorted'
        if (unsorted.size() <= 1) {
            return unsorted;
        }

        // split the list into two sublists and sort them
        var size = unsorted.size();
        var left = unsorted.subList(0, size/2);
        var right = unsorted.subList(size/2, size);
        List<Integer> sortedLeft = MergeSort.mergeSort(left);
        List<Integer> sortedRight = MergeSort.mergeSort(right);

        // merge the sorted sublists
        int l = 0;
        int r = 0;
        List<Integer> result = new ArrayList<>();
        while(l < sortedLeft.size() || r < sortedRight.size()) {
            if (r > sortedRight.size()-1 || (l < sortedLeft.size() && sortedLeft.get(l) < sortedRight.get(r))) {
                result.add(sortedLeft.get(l));
                l++;
            } else {
                result.add(sortedRight.get(r));
                r++;
            }
        }
        return result;
    }

    @Test
    public void testSort() {
        Integer[] unsorted = {16, 12, 1, 13, 98, 25, 0};
        Integer[] sorted = {0, 1, 12, 13, 16, 25, 98};
        assertTrue(Arrays.asList(sorted).equals(mergeSort(Arrays.asList(unsorted))));
    }

}
