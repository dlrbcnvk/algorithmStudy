package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 다익스트라
 */
public class BOJ1916 {

    private static int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int m = Integer.valueOf(br.readLine());

        ArrayList<Node>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

        String[] split;
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int from = Integer.valueOf(split[0]);
            int to = Integer.valueOf(split[1]);
            int cost = Integer.valueOf(split[2]);
            graph[from].add(new Node(to, cost));
        }
        split = br.readLine().split(" ");
        int start = Integer.valueOf(split[0]);
        int end = Integer.valueOf(split[1]);

        boolean[] check = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if (check[nowVertex]) continue;
            check[nowVertex] = true;

            for (Node next : graph[nowVertex]) {
                if (dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;
                    pq.add(new Node(next.index, dist[next.index]));
                }
            }
        }

        System.out.println(dist[end]);
    }
}
