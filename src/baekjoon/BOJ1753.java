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
public class BOJ1753 {

    private static int INF = Integer.MAX_VALUE;
    private static String INFSTRING = "INF";

    public static class Node implements Comparable<Node> {
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
        String[] split = br.readLine().split(" ");
        int V = Integer.valueOf(split[0]);
        int E = Integer.valueOf(split[1]);
        int start = Integer.valueOf(br.readLine());

        ArrayList<Node>[] graph = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) { graph[i] = new ArrayList<>(); }

        for (int i = 0; i < E; i++) {
            split = br.readLine().split(" ");
            int from = Integer.valueOf(split[0]);
            int to = Integer.valueOf(split[1]);
            int cost = Integer.valueOf(split[2]);
            graph[from].add(new Node(to, cost));
        }

        boolean[] check = new boolean[V + 1];
        int[] dist = new int[V + 1];
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

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == INF) {
                System.out.println(INFSTRING);
            } else {
                System.out.println(dist[i]);
            }
        }
    }
}
