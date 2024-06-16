package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 미확인 도착지
 * https://www.acmicpc.net/problem/9370
 * 다익스트라
 * 미해결
 */
public class BOJ9370 {

    private static int INF = Integer.MAX_VALUE;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

    private static int T;
    private static int n;
    private static int m;
    private static int t;
    private static int s;
    private static int g;
    private static int h;

    public static void main(String[] args) throws IOException {

        T = Integer.valueOf(br.readLine());
        for (int i=0; i<T; i++) {
            test();
        }


    }

    private static void test() throws IOException {
        String[] split = br.readLine().split(" ");
        n = Integer.valueOf(split[0]);
        m = Integer.valueOf(split[1]);
        t = Integer.valueOf(split[2]);
        split = br.readLine().split(" ");
        s = Integer.valueOf(split[0]);
        g = Integer.valueOf(split[1]);
        h = Integer.valueOf(split[2]);

        Queue<Node>[] graph = new LinkedList[n + 1];
        for (int i=0; i<graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i=0; i<m; i++) {
            split = br.readLine().split(" ");
            int from = Integer.valueOf(split[0]);
            int to = Integer.valueOf(split[1]);
            int d = Integer.valueOf(split[2]);

            graph[from].add(new Node(to, d));
            graph[to].add(new Node(from, d));
        }

        boolean[] check = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if (check[nowVertex]) continue;
            check[nowVertex] = true;

            for (Node next : graph[nowVertex]) {
                if (dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;
                }
            }
        }
    }
}
