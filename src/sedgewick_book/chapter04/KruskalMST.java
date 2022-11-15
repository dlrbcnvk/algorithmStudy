package sedgewick_book.chapter04;

import sedgewick_book.chapter01.UF;
import sedgewick_book.chapter01.WeightedQuickUnionUF;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph graph) {
        mst = new LinkedList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e : graph.edges()) {
            pq.add(e);
        }
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(graph.V());

        while (!pq.isEmpty()) {
            Edge e = pq.poll(); // pq에서 최소 가중치 간선과
            int v = e.either(), w = e.other(v); // 그 정점들 얻기
            if (uf.connected(v, w)) { // 부적합 간선은 무시
                continue;
            }
            uf.union(v, w); // 컴포넌트 병합
            mst.add(e); // mst에 간선 추가
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

        KruskalMST kruskalMST = new KruskalMST(edgeWeightedGraph);
        while (!kruskalMST.mst.isEmpty()) {
            Edge poll = kruskalMST.mst.poll();
            int either = poll.either();
            System.out.println(either + " - " + poll.other(either));
        }
    }
}
