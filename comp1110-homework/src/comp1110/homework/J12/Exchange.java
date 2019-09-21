package comp1110.homework.J12;

import java.util.Arrays;

public class Exchange {
    public static <T> T[] swap(T[] a, int i, int j) {
        T[] arrTmp;
        T tmp;

        if (i < 0 || i >= a.length
            || j < 0 || j >= a.length) {
            return a;
        }

        arrTmp = Arrays.copyOf(a, a.length);
        tmp = arrTmp[i];
        arrTmp[i] = arrTmp[j];
        arrTmp[j] = tmp;

        return arrTmp;
    }
}
