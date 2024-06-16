package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 최소 힙
 * https://www.acmicpc.net/problem/1927
 */
public class BOJ1927 {

    static class MinHeap {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        public MinHeap(){};

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
        MinHeap minHeap = new MinHeap();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            minHeap.operate(num);
        }
    }
}
