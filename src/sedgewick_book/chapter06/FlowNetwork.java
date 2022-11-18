package sedgewick_book.chapter06;

import java.util.ArrayList;

public class FlowNetwork {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private ArrayList<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices in a Graph must be Non-Negative");
        }
        this.V = V;
        this.E = 0;
        adj = new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public int V() { return V; }
    public int E() { return E; }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    public void addEdge(FlowEdge edge) {
        int v = edge.from();
        int w = edge.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public Iterable<FlowEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public Iterable<FlowEdge> edges() {
        ArrayList<FlowEdge> list = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            for (FlowEdge edge : adj(v)) {
                if (edge.to() != v) {
                    list.add(edge);
                }
            }
        }
        return list;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (FlowEdge edge : adj[v]) {
                if (edge.to() != v) {
                    s.append(edge + " ");
                }
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
