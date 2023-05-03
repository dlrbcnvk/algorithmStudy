package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 두 큐 합 같게 만들기
 * 미해결
 */
public class Programmers118667 {

    private static int result = -2;

    public int solution(int[] queue1, int[] queue2) {

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int i : queue1) sum1 += i;
        for (int i : queue2) sum2 += i;
        long sum = sum1 + sum2;
        if (sum % 2 == 1) return -1;
        long target = sum / 2;

        go(q1, sum1, q2, sum2, target, 0, 0, q1.size(), q2.size());





        int answer = -2;
        return answer;
    }

    private void go(Queue<Integer> q1, long sum1, Queue<Integer> q2, long sum2, long target, int PopCount1, int PopCount2, int initialQ1Size, int initialQ2Size) {
        // 종료조건
        if (sum1 == target && sum2 == target) {

        }
    }

    public static void main(String[] args) {
        Programmers118667 programmers118667 = new Programmers118667();
//        int solution = programmers118667.solution();
//        System.out.println(solution);
    }
}
