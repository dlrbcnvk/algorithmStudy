package sedgewick_book.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * 무방향 그래프
 */
public class Graph {
    private final int V; // 정점 개수
    private int E; // 간선 개수
    private List<Integer>[] adj; // 인접 리스트

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
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

    public List<Integer> adj(int v) {
        return adj[v];
    }

    public String toString() {
        StringBuilder s = new StringBuilder().append(V).append(" vertices, ").append(E).append(" edges\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : this.adj(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // 주어진 정점의 차수 구하기
    public int degree(int v) {
        return adj[v].size();
    }

    public int maxDegree() {
        int max = 0;
        for (List<Integer> arr : adj) {
            if (max < arr.size()) {
                max = arr.size();
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        System.out.println(graph);
        System.out.println("degree(1) = " + graph.degree(2));
        System.out.println("maxDegree = " + graph.maxDegree());
    }

}
