package sedgewick_book.chapter02;

public class Selection extends Example {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{7, 5, 4, 4, 7, 98, 76, 3, 22, 32, 40, 17, 16};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
