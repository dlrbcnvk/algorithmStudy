package programmers.lv3;

import java.util.*;

/**
 * 가장 먼 노드
 * 시작점으로부터의 길이 배열 만들고 bfs 하면서 유지관리
 * 최대값들 개수 뽑아내기
 */
public class Programmers49189 {

    public static class Graph {
        private final int V;
        private int E;
        private List<Integer>[] adj;

        public Graph(int V) {
            this.V = V;
            this.E = 0;
            adj = new ArrayList[V + 1];
            for (int i = 0; i <= V; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public int V() { return V; }
        public int E() { return E; }

        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
            E++;
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;
        Graph graph = new Graph(n);
        for (int[] e : edge) {
            graph.addEdge(e[0], e[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[n + 1];


        marked[1] = true;
        int[] lengthFromStart = new int[n + 1];
        lengthFromStart[0] = -1;
        lengthFromStart[1] = 0;
        queue.add(1);

        while (!queue.isEmpty()) {
            Integer from = queue.poll();
            for (int to : graph.adj(from)) {
                if (!marked[to]) {
                    marked[to] = true;
                    lengthFromStart[to] = lengthFromStart[from] + 1;
                    queue.add(to);
                }
            }
        }

        Arrays.sort(lengthFromStart);

        int idx = n;
        int maxValue = lengthFromStart[n];
        do {
            if (lengthFromStart[idx] == maxValue) {
                idx--;
                answer += 1;
            }
        } while (lengthFromStart[idx] == maxValue);


        return answer;
    }

    public static void main(String[] args) {
        Programmers49189 programmers49189 = new Programmers49189();
        int solution = programmers49189.solution(
                6, new int[][]{{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}}
        );
        System.out.println(solution);
    }
}
