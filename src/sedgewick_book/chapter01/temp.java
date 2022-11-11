package sedgewick_book.chapter01;

import java.util.Arrays;
import java.util.stream.IntStream;

public class temp {
    public static void main(String[] args) {
        int a[] = new int[4];
        a[2] = 22;
        int[] b = a;
        System.out.println(a);
        System.out.println(b);
        int[] temp = Arrays.stream(a)
                .filter(item -> item != 0)
                .toArray();
        System.out.println(temp.length);


    }
}
