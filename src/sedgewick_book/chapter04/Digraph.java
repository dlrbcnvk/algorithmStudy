package sedgewick_book.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * 방향 그래프
 */
public class Digraph {
    private final int V; // 정점 개수
    private int E; // 간선 개수
    private List<Integer>[] adj; // 인접 리스트

    public Digraph(int V) {
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
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
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

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                // v -> w 하나씩 찾아서
                // w -> v로 셋팅
                R.addEdge(w, v);
            }
        }
        return R;
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        System.out.println(graph);
        System.out.println("degree(1) = " + graph.degree(2));
        System.out.println("maxDegree = " + graph.maxDegree());
    }
}
