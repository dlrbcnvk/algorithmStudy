package programmers.lv3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 네트워크
 * 깊이/너비 우선 탐색
 */
public class Programmers43162 {

    static class Graph {
        private int V;              // 정점 개수
        private int E;              // 간선 개수
        List<Integer>[] adj;        // 인접 리스트

        public Graph(int n) {
            this.V = n;
            this.E = 0;
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int i, int j) {
            adj[i].add(j);
            adj[j].add(i);
            E++;
        }
    }

    public int solution(int n, int[][] computers) {

        int count = 0;
        Graph graph = new Graph(n);
        boolean[] marked = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1 && i != j) {
                    graph.addEdge(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            Queue<Integer> queue = new LinkedList<>();
            if (!marked[i]) {
                marked[i] = true;
                count++;
            }
            queue.add(i);

            while (!queue.isEmpty()) {
                Integer from = queue.poll();
                for (int to : graph.adj[from]) {
                    if (!marked[to]) {
                        marked[to] = true;
                        queue.add(to);
                    }
                }
            }

        }
        return count;
    }

    public static void main(String[] args) {
        Programmers43162 programmers43162 = new Programmers43162();
        int solution = programmers43162.solution(
            3, new int[][]{{1,1,0}, {1,1,1}, {0,1,1}}
        );
        System.out.println(solution);

    }
}
