package programmers.pccp;

import java.util.PriorityQueue;

/**
 * 신입사원 교육
 * https://school.programmers.co.kr/learn/courses/15009/lessons/121688
 */
public class Programmers121688 {

    public int solution(int[] ability, int number) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : ability) {
            pq.add(num);
        }

        for (int i=0; i<number; i++) {
            Integer value1 = pq.poll();
            Integer value2 = pq.poll();
            Integer afterValue = value1 + value2;
            pq.add(afterValue);
            pq.add(afterValue);
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            answer += pq.poll();
        }
        return answer;
    }
}
