package tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {

    private static int INF = Integer.MAX_VALUE;

    public class Node implements Comparable<Node> {
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

    public int[] solution(int n, int start, int[][] edges) {

        // 인접 리스트
        ArrayList<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new Node(edge[1], edge[2]));
        }

        boolean[] check = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if (check[nowVertex]) continue;
            check[nowVertex] = true;

            // index의 연결된 정점 비교
            for (Node next : graph[nowVertex]) {
                if (dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;

                    pq.add(new Node(next.index, dist[next.index]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        int n = 5;
        int start = 3;
        int[][] edges = new int[][]{
                {0,1,10},
                {0,2,5},
                {1,2,2},
                {2,0,1},
                {2,1,13},
                {3,0,8},
                {3,4,3},
                {4,3,9},
                {4,1,31}
        };

        int[] solution = dijkstra.solution(n, start, edges);

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
