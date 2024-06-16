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
public class BOJ1504 {

    private static int INF = Integer.MAX_VALUE;

    private static int N;
    private static ArrayList<Node>[] graph;

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
        N = Integer.valueOf(split[0]);
        int E = Integer.valueOf(split[1]);

        graph = new ArrayList[N + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            split = br.readLine().split(" ");
            int u = Integer.valueOf(split[0]);
            int v = Integer.valueOf(split[1]);
            int cost = Integer.valueOf(split[2]);

            graph[u].add(new Node(v, cost));
            graph[v].add(new Node(u, cost));
        }

        split = br.readLine().split(" ");
        int m1 = Integer.valueOf(split[0]);
        int m2 = Integer.valueOf(split[1]);
        int[] startDist = dijkstra(1);
        int[] m1Dist = dijkstra(m1);
        int[] m2Dist = dijkstra(m2);

        int case1 = INF;
        int case2 = INF;
        if (startDist[m1] != INF && m1Dist[m2] != INF && m2Dist[N] != INF) {
            case1 = startDist[m1] + m1Dist[m2] + m2Dist[N];
        }
        if (startDist[m2] != INF && m2Dist[m1] != INF && m1Dist[N] != INF) {
            case2 = startDist[m2] + m2Dist[m1] + m1Dist[N];
        }

        if (case1 != case2) {
            System.out.println(Math.min(case1, case2));
        } else if (case1 == INF) {
            System.out.println(-1);
        } else {
            System.out.println(case1);
        }
    }

    private static int[] dijkstra(int start) {
        boolean[] check = new boolean[N + 1];
        int[] dist = new int[N + 1];
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
        return dist;
    }
}
