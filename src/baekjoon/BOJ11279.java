package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 최대 힙
 * https://www.acmicpc.net/problem/11279
 */
public class BOJ11279 {

    static class MaxHeap {
        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o2 - o1));

        public MaxHeap(){};

        public void operate(int num) {
            if (num > 0) {
                pq.add(num);
            } else if (num == 0) {
                remove();
            }
        }

        private void remove() {
            if (!pq.isEmpty()) {
                int poll = pq.poll();
                System.out.println(poll);
            } else {
                System.out.println(0);
            }
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        MaxHeap maxHeap = new MaxHeap();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            maxHeap.operate(num);
        }
    }
}
