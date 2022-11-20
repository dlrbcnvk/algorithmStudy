package sedgewick_book.chapter04;

/**
 * 가중 간선 DAG에서의 최단 경로
 * 위상 순서로 정점들을 이완함. 이 작업만으로 최단 경로가 구해짐.
 */
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;

//        Topological topological = new Topological(G);
//
//        for (int v : topological.order()) {
//            relax(G, v);
//        }
    }
}
