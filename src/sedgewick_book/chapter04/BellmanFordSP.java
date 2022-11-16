package sedgewick_book.chapter04;

import java.util.LinkedList;
import java.util.Queue;

public class BellmanFordSP {
    private double[] distTo; // v로의 경로 길이
    private DirectedEdge[] edgeTo; // v로의 경로에서 마지막 간선
    private boolean[] onQ; // 정점이 큐에 존재하는가?
    private Queue<Integer> queue; // 이완되는 정점들
    private int cost; // relax()의 호출 횟수
    private Iterable<DirectedEdge> cycle; // edgeTo[]에 음의 순환이 있는가?

    public BellmanFordSP(EdgeWeightedDigraph graph, int s) {
        distTo = new double[graph.V()];
        edgeTo = new DirectedEdge[graph.V()];
        onQ = new boolean[graph.V()];
        queue = new LinkedList<>();
        for (int v = 0; v < graph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;
        queue.add(s);
        onQ[s] = true;
//        while (!queue.isEmpty() && !hasNegativeCycle()) {
//            int v = queue.poll();
//            onQ[v] = false;
//            relax(graph, v);
//        }
    }

    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.add(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % graph.V() == 0) {
                findNegativeCycle();
            }
        }
    }

    // 벨만-포드 알고리즘에서의 음의 순환 검출 메서드
    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt;
        spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            if (edgeTo[v] != null) {
                spt.addEdge(edgeTo[v]);
            }
        }


    }
}
