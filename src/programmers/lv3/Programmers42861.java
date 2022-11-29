package programmers.lv3;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 섬 연결하기
 * Prim MST
 * 'edge pq'로 bfs
 * 방문하면서 인접한 간선을 pq에 다 넣어
 */
public class Programmers42861  {

    public class Edge implements Comparable<Edge> {
        private final int v;
        private final int w;
        private final int cost;

        public Edge(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }

        public int either() {
            return v;
        }

        public int other(int vertex) {
            if (vertex == v) {
                return w;
            } else if (vertex == w) {
                return v;
            } else {
                throw new RuntimeException("Inconsistent Edge");
            }
        }

        @Override
        public int compareTo(Edge edge) {
            if (this.cost < edge.cost) {
                return -1;
            } else if (this.cost > edge.cost) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public class EdgeCostGraph {
        private final int V; // 정점 개수
        private int E; // 간선 개수
        private Queue<Edge>[] adj; // 인접 리스트

        public EdgeCostGraph(int V) {
            this.V = V;
            this.E = 0;
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(Edge edge) {
            int v = edge.either(), w = edge.other(v);
            adj[v].add(edge);
            adj[w].add(edge);
            E++;
        }

        private int V() { return V; }
        private int E() { return E; }

        public Iterable<Edge> adj(int v) { return adj[v]; }
    }

    public class PrimMST {
        private boolean[] marked; // MST 정점들
        private Queue<Edge> mst; // MST 간선들
        private PriorityQueue<Edge> pq; // 횡단 간선들(부적합 포함)

        public PrimMST(EdgeCostGraph graph) {
            pq = new PriorityQueue<>();
            marked = new boolean[graph.V()];
            mst = new LinkedList<>();
            visit(graph, 0);

            while (!pq.isEmpty()) {
                Edge e = pq.poll(); // pq로부터 최저 가중치
                int v = e.either(), w = e.other(v); // 간선 얻기
                if (marked[v] && marked[w]) // 부적합 간선이면(두 점 모두 이미 찍은 점들이면) 무시
                    continue;

                mst.add(e); // 트리에 간선 추가
                if (!marked[v]) // 트리에 정점 추가. 점 찍기
                    visit(graph, v);
                if (!marked[w]) // v 또는 w 둘 다
                    visit(graph, w);
            }
        }

        private void visit(EdgeCostGraph graph, int v) {
            // v에 점 찍고, v에 연결되었으면서 점 찍히지 않은 정점으로의 모든 간선을 pq에 추가
            marked[v] = true;
            for (Edge e : graph.adj(v)) {
                if (!marked[e.other(v)])
                    pq.add(e);
            }
        }
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;

        EdgeCostGraph graph = new EdgeCostGraph(n);
        for (int i = 0; i < costs.length; i++) {
            int[] cost = costs[i];
            graph.addEdge(new Edge(cost[0], cost[1], cost[2]));
        }
        PrimMST mst = new PrimMST(graph);
        Queue<Edge> queue = mst.mst;
        while (!queue.isEmpty()) {
            Edge poll = queue.poll();
            answer += poll.cost;
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers42861 programmers42861 = new Programmers42861();
        int solution = programmers42861.solution(
                4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}
        );
        System.out.println(solution);
    }
}
