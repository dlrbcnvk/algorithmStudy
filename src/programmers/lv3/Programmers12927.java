package programmers.lv3;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 야근 지수
 * stream 쓰니까 효율성 통과 못 함. pq.stream().reduce(0, (a, b) -> a + b * b)
 * pq에서 하나씩 빼면서 제곱하여 더하니까 효율성 통과.
 * 그러나 메모리 사용량, 걸린 시간 차이가 stream 썼을 때에 비해 조금 적은 듯
 */
public class Programmers12927 {
    public long solution(int n, int[] works) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : works) {
            pq.add(i);
        }

        while (!pq.isEmpty() && n > 0) {

            Integer max = pq.poll();
            if (!pq.isEmpty()) {
                Integer second = pq.peek();
                Integer value = second - 1;
                if (second != 1) {
                    pq.add(max - value >= n ? max - n : value);
                }
                n = max - value >= n ? 0 : n - (max - value);
            } else {
                if (max <= n) {
                    return 0;
                } else {
                    return (long) (max - n) * (max - n);
                }
            }
        }

        long sum = 0;
        while (!pq.isEmpty()) {
            long poll = (long) pq.poll();
            sum += poll * poll;
        }
        return sum;
    }

    public static void main(String[] args) {
        Programmers12927 programmers12927 = new Programmers12927();
        long solution = programmers12927.solution(
                4, new int[]{4,3,3}
        );
        System.out.println(solution);
    }
}
