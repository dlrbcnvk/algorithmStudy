package programmers.lv3;

import java.util.Arrays;

/**
 * 입국심사
 * PQ 사용 -> 6~9 케이스 시간초과
 * [1,3,4,7,10] 인 경우 10초동안 최대 17명 심사
 * 11초->18명, 12초->21명. 함수 관계
 * 이분탐색
 */
public class Programmers43238 {

    public long solution(int n, int[] times) {

        Arrays.sort(times);

        long start = 1;
        long end = (long)n * times[times.length - 1];

        while (end > start) {
            long mid = (start + end) / 2;
            long midMax = getMax(mid, times);
            if (midMax >= n) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    public long getMax(long t, int[] times) {
        long sum = 0;
        for (int i = 0; i < times.length; i++) {
            sum += t / times[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Programmers43238 programmers43238 = new Programmers43238();
        long solution = programmers43238.solution(6, new int[]{7, 10});
        System.out.println(solution);
    }
}
