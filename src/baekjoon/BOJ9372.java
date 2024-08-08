package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 상근이의 여행
 */
public class BOJ9372 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] split;
        for (int t = 0; t < T; t++) {
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = split[0];
            int M = split[1];
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                adj.put(i, new ArrayList<>());
            }
            for (int i = 0; i < M; i++) {
                split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int a = split[0];
                int b = split[1];
                adj.get(a).add(b);
                adj.get(b).add(a);
            }
            boolean[] marked = new boolean[N + 1];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(1);
            int flights = 0;
            while (!queue.isEmpty()) {
                Integer from = queue.poll();
                if (marked[from]) {
                    continue;
                }
                marked[from] = true;
                if (from != 1) {
                    flights++;
                }
                for (Integer to : adj.get(from)) {
                    if (!marked[to]) {
                        queue.add(to);
                    }
                }
            }
            System.out.println(flights);
        }
    }
}
