package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 특정 거리의 도시 찾기
 * https://www.acmicpc.net/problem/18352
 * 다익스트라
 * 틀렸음...
 */
public class BOJ18352 {

    private static int INF = Integer.MAX_VALUE;
    private static String NEXT_LINE = "\n";

    private static int N;
    private static int M;
    private static int K;
    private static int X;

    public static class Node implements Comparable<Node> {
        int index;
        int cost = 1;

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
        N = Integer.valueOf(split[0]);
        M = Integer.valueOf(split[1]);
        K = Integer.valueOf(split[2]);
        X = Integer.valueOf(split[3]);

        ArrayList<Node>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            split = br.readLine().split(" ");
            int from = Integer.valueOf(split[0]);
            int to = Integer.valueOf(split[1]);
            graph[from].add(new Node(to, 1));
        }

        boolean[] check = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X, 0));

        while (!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if (check[nowVertex]) continue;
            check[nowVertex] = true;

            for (Node next : graph[nowVertex]) {
                if (dist[next.index] > dist[nowVertex] + 1) {
                    dist[next.index] = dist[nowVertex] + 1;
                    pq.add(new Node(next.index, dist[next.index]));
                }
            }
        }

        ArrayList<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == K) {
                resultList.add(i);
            }
        }

        int[] result = resultList.stream().sorted().mapToInt(i -> i).toArray();

        if (result.length == 0) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]);
            if (i != result.length - 1) {
                sb.append(NEXT_LINE);
            }
        }
        System.out.println(sb);
    }
}
