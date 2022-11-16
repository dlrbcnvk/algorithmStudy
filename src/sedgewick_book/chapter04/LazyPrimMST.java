package sedgewick_book.chapter04;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LazyPrimMST {
    private boolean[] marked; // MST 정점들
    private Queue<Edge> mst; // MST 간선들
    private PriorityQueue<Edge> pq; // 횡단 간선들(부적합 포함)

    public LazyPrimMST(EdgeWeightedGraph graph) {
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
            System.out.println(e);
            if (!marked[v]) // 트리에 정점 추가. 점 찍기
                visit(graph, v);
            if (!marked[w]) // v 또는 w 둘 다
                visit(graph, w);
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        // v에 점 찍고, v에 연결되었으면서 점 찍히지 않은 정점으로의 모든 간선을 pq에 추가
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            if (!marked[e.other(v)])
                pq.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {

        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(8);
        edgeWeightedGraph.addEdge(new Edge(4, 5, 0.35));
        edgeWeightedGraph.addEdge(new Edge(4, 7, 0.37));
        edgeWeightedGraph.addEdge(new Edge(5, 7, 0.28));
        edgeWeightedGraph.addEdge(new Edge(0, 7, 0.16));
        edgeWeightedGraph.addEdge(new Edge(1, 5, 0.32));
        edgeWeightedGraph.addEdge(new Edge(0, 4, 0.38));
        edgeWeightedGraph.addEdge(new Edge(2, 3, 0.17));
        edgeWeightedGraph.addEdge(new Edge(1, 7, 0.19));
        edgeWeightedGraph.addEdge(new Edge(0, 2, 0.26));
        edgeWeightedGraph.addEdge(new Edge(1, 2, 0.36));
        edgeWeightedGraph.addEdge(new Edge(1, 3, 0.29));
        edgeWeightedGraph.addEdge(new Edge(2, 7, 0.34));
        edgeWeightedGraph.addEdge(new Edge(6, 2, 0.40));
        edgeWeightedGraph.addEdge(new Edge(3, 6, 0.52));
        edgeWeightedGraph.addEdge(new Edge(6, 0, 0.58));
        edgeWeightedGraph.addEdge(new Edge(6, 4, 0.93));

        LazyPrimMST lazyPrimMST = new LazyPrimMST(edgeWeightedGraph);
        while (!lazyPrimMST.mst.isEmpty()) {
            Edge poll = lazyPrimMST.mst.poll();
            int either = poll.either();
            System.out.println(either + " - " + poll.other(either));
        }
    }
}
