package sedgewick_book.chapter02;

public class Merge extends Example {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) // 1개짜리는 merge할 필요 없으니 패스
            return;

        int mid = (hi + lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {

        int i = lo, j = mid + 1; // 각각 출발 위치

        // a[lo..mid]와 a[mid+1..hi]를 병합
        for (int k = lo; k <= hi; k++) { // a를 aux에 복제
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) // i를 다 뿌린 경우
                a[k] = aux[j++];
            else if (j > hi) // j를 다 뿌린 경우
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{7, 5, 4, 4, 7, 98, 76, 3, 22, 32, 40, 17, 16};
        sort(a);

        assert isSorted(a);
        show(a);
    }
}
