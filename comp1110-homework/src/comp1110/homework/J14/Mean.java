package comp1110.homework.J14;

import java.util.ArrayList;
import java.util.Scanner;

public class Mean {
    public static void main(String[] args) {
        ArrayList<Double> doubles = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        double sum = 0;

        while(in.hasNext()) {
            doubles.add(in.nextDouble());
        }

        for (double num: doubles) {
            sum += num;
        }

        System.out.println(sum / doubles.size());
    }

}
