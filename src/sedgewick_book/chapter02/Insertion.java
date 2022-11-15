package sedgewick_book.chapter02;

public class Insertion extends Example {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{7, 5, 4, 4, 7, 98, 76, 3, 22, 32, 40, 17, 16};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
