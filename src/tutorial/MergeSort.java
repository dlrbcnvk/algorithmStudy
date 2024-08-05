package tutorial;

public class MergeSort {

    public int[] mergeSort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }


        int mid = arr.length / 2;
        int[] front = new int[mid];
        int[] back = new int[arr.length - mid];
        for (int i = 0; i < mid; i++)
            front[i] = arr[i];
        for (int i = mid; i < arr.length; i++)
            back[i - mid] = arr[i];

        int[] first = mergeSort(front);
        int[] second = mergeSort(back);
        int firstIdx = 0;
        int secondIdx = 0;
        int curIdx = 0;
        int[] result = new int[first.length + second.length];
        while (firstIdx < first.length && secondIdx < second.length) {
            if (first[firstIdx] < second[secondIdx]) {
                result[curIdx] = first[firstIdx];
                firstIdx++;
            } else {
                result[curIdx] = second[secondIdx];
                secondIdx++;
            }
            curIdx++;
        }
        if (firstIdx == first.length) {
            while (secondIdx < second.length) {
                result[curIdx] = second[secondIdx];
                secondIdx++;
                curIdx++;
            }
        }
        if (secondIdx == second.length) {
            while (firstIdx < first.length) {
                result[curIdx] = first[firstIdx];
                firstIdx++;
                curIdx++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = new int[]{3, 2, 8, 4, 6, 5, 1, 9, 10, 2};
        int[] result = mergeSort.mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(result[i] + " ");
        }

//        int[] streamArr = Arrays.stream(arr).sorted().toArray();
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(streamArr[i] + " ");
//        }


//        Arrays.sort(arr);
    }
}
