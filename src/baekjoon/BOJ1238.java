package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 파티
 * https://www.acmicpc.net/problem/1238
 * 다익스트라
 */
public class BOJ1238 {

    private static ArrayList<Node>[] graph;
    private static int N;
    private static int M;
    private static int X;
    private static int maxDist = -1;


    static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        X = Integer.parseInt(split[2]);
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int cost = Integer.parseInt(split[2]);
            graph[from].add(new Node(to, cost));
        }

        // X -> 각자의 집까지 소요시간 구하기
        int[] toHome = dijkstra(X);


        // 각자의 집 -> X
        // dijkstra 를 N-1 번 하며 왕복거리 최대값 갱신하기
        for (int i = 1; i < N + 1; i++) {
            if (i == X) continue;
            int[] toParty = dijkstra(i);
            maxDist = Math.max(maxDist, toParty[X] + toHome[i]);
        }

        System.out.println(maxDist);
    }

    private static int[] dijkstra(int start) {
        boolean[] check = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            int cur = pq.poll().index;
            if (check[cur]) continue;
            check[cur] = true;

            for (Node next : graph[cur]) {
                if (dist[cur] + next.cost < dist[next.index]) {
                    dist[next.index] = dist[cur] + next.cost;
                    pq.add(new Node(next.index, dist[next.index]));
                }
            }
        }

        return dist;
    }
}
