package sedgewick_book.chapter04;

public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph graph, int s) {
        marked = new boolean[graph.V()];
        dfs(graph, s);
    }

    private void dfs(Digraph graph, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                System.out.println(v + " -> " + w);
                dfs(graph, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(13);
        graph.addEdge(4, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 2);
        graph.addEdge(6, 0);
        graph.addEdge(0, 1);
        graph.addEdge(2, 0);
        graph.addEdge(11, 12);
        graph.addEdge(12, 9);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(8, 9);
        graph.addEdge(10, 12);
        graph.addEdge(11, 4);
        graph.addEdge(4, 3);
        graph.addEdge(3, 5);
        graph.addEdge(7, 8);
        graph.addEdge(8, 7);
        graph.addEdge(5, 4);
        graph.addEdge(0, 5);
        graph.addEdge(6, 4);
        graph.addEdge(6, 9);
        graph.addEdge(7, 6);

        DirectedDFS directedDFS = new DirectedDFS(graph, 0);

    }


}
