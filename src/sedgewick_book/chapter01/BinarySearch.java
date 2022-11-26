package sedgewick_book.chapter01;

import java.util.Arrays;

public class BinarySearch {

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (key < a[mid]) {
                hi = mid + 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int findFirst(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi) {
            int mid = (hi + lo) / 2 + 1;
            if (key > a[mid]) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public static int findLast(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi) {
            int mid = (hi + lo) / 2;
            if (key < a[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }



    public static void main(String[] args) {
        int[] ints = {98, 84, 77, 68, 57, 54, 48, 33, 29, 23, 18, 16, 14, 14, 12, 12, 12, 12, 12, 11, 10};
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
        int result = findLast(98, ints);
        System.out.println(result);
    }
}
