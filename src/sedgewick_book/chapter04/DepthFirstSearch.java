package sedgewick_book.chapter04;

/**
 * 깊이 우선 탐색(재귀 이용)
 * 무방향 그래프
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true; // 방문했다는 표시를 한다.
        count++;
        for (int w : graph.adj(v)) { // 인접한 정점 중에
            if (!marked[w]) { // 방문하지 않은 정점을
                System.out.println(v + " -> " + w);
                dfs(graph, w); // 재귀적으로 방문한다. 호출 '스택'에 쌓이므로 깊이 우선으로 동작함
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 2);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(0, 1);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(0, 2);

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph, 0);
        System.out.println("depthFirstSearch.count() = " + depthFirstSearch.count());

    }
}
