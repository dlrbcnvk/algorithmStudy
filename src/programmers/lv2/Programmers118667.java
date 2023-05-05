package programmers.lv2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 두 큐 합 같게 만들기
 * 미해결. 스택오버플로
 */
public class Programmers118667 {

    private static int result = Integer.MAX_VALUE;

    public int solution(int[] queue1, int[] queue2) {

        Deque<Integer> d1 = new LinkedList<>();
        Deque<Integer> d2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int i : queue1) sum1 += i;
        for (int i : queue2) sum2 += i;
        long sum = sum1 + sum2;
        if (sum % 2 == 1) return -1;
        long target = sum / 2;

        Deque<Integer> initialD1 = new LinkedList<>();
        for (int i : queue1) {
            initialD1.add(i);
            d1.add(i);
        }
        Deque<Integer> initialD2 = new LinkedList<>();
        for (int i : queue2) {
            initialD2.add(i);
            d2.add(i);
        }

        go(d1, sum1, d2, sum2, target, 0, 0, initialD1, initialD2, 0);

        return result;
    }

    private void go(Deque<Integer> d1, long sum1, Deque<Integer> d2, long sum2, long target, int pollCount1, int pollCount2, Deque<Integer> initialD1, Deque<Integer> initialD2, int depth) {
        System.out.print("depth=" + depth + ", ");
        System.out.print("d1=[");
        for (int i : d1) {
            System.out.print(i + ", ");
        }
        System.out.print("], d2=[");
        for (int i : d2) {
            System.out.print(i + ", ");
        }
        System.out.print("]\n");
        // 종료조건
        if (sum1 == target && sum2 == target) {
            result = Math.min(result, pollCount1 + pollCount2);
            System.out.println(result);
            return;
        }
        if (d1.equals(initialD1) && d2.equals(initialD2)) {
            if (pollCount1 != 0 || pollCount2 != 0)
                return;
        }

        if (d1.isEmpty() || d2.isEmpty())
            return;

        // case 1: d1 -> d2
        Integer poll = d1.poll();
        d2.addLast(poll);
        go(d1, sum1 - poll, d2, sum2 + poll, target, pollCount1 + 1, pollCount2, initialD1, initialD2, depth + 1);
        d1.addFirst(d2.removeLast());

        // case 2: d2 -> d1
        poll = d2.poll();
        d1.addLast(poll);
        go(d1, sum1 + poll, d2, sum2 - poll, target, pollCount1, pollCount2 + 1, initialD1, initialD2, depth + 1);
        d2.addFirst(d1.removeLast());
    }

    public static void main(String[] args) {
        Programmers118667 programmers118667 = new Programmers118667();
        int solution = programmers118667.solution(
                new int[]{3,2,7,2}, new int[]{4,6,5,1}
        );
        System.out.println(solution);
    }
}
