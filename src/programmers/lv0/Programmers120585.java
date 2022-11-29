package programmers.lv0;

import java.util.Arrays;

/**
 * 머쓱이보다 키 큰 사람
 * 이분탐색 gt min 으로 풀어봤음
 */
public class Programmers120585 {
    public int solution(int[] array, int height) {

        Arrays.sort(array);
        // find gt min
        int lo = 0, hi = array.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (array[mid] <= height) {
                lo = mid + 1;
            } else if (array[mid] > height) {
                hi = mid;
            }
        }

        return array.length - hi;
    }

    public static void main(String[] args) {
        Programmers120585 programmers120585 = new Programmers120585();
        int solution = programmers120585.solution(
                new int[]{2,4,6,8,10,10,10,10,12,14,16,18,20}, 17
        );
        System.out.println(solution);
    }
}
