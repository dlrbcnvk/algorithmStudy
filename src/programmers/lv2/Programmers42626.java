package programmers.lv2;

import java.util.PriorityQueue;

/**
 * 더 맵게
 * priority queue
 */
public class Programmers42626 {
    public int solution(int[] scoville, int K) {
        int count = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }
        while (!pq.isEmpty()) {
            if (pq.peek() >= K) {
                return count;
            }
            if (pq.size() == 1) {
                return -1;
            }
            Integer first = pq.poll();
            Integer second = pq.poll();
            pq.add(first + second * 2);
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        Programmers42626 programmers42626 = new Programmers42626();
        int solution = programmers42626.solution(
                new int[]{1,2,3,9,10,12}, 7
        );
        System.out.println(solution);
    }
}
