package sedgewick_book.chapter04;

import jongman.BellmanFord;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 벨만-포드 최단 경로 알고리즘
 * 음수 가중치 고려
 *
 */
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
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.poll();
            onQ[v] = false;
            relax(graph, v);
        }
    }

    private boolean hasNegativeCycle() {
        return cycle != null;
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

    public static void main(String[] args) {
        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(8);
        edgeWeightedDigraph.addEdge(new DirectedEdge(4, 5, 0.35));
        edgeWeightedDigraph.addEdge(new DirectedEdge(5, 4, 0.35));
        edgeWeightedDigraph.addEdge(new DirectedEdge(4, 7, 0.37));
        edgeWeightedDigraph.addEdge(new DirectedEdge(5, 7, 0.28));
        edgeWeightedDigraph.addEdge(new DirectedEdge(7, 5, 0.28));
        edgeWeightedDigraph.addEdge(new DirectedEdge(5, 1, 0.32));
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 4, 0.38));
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 2, 0.26));
        edgeWeightedDigraph.addEdge(new DirectedEdge(7, 3, 0.39));
        edgeWeightedDigraph.addEdge(new DirectedEdge(1, 3, 0.29));
        edgeWeightedDigraph.addEdge(new DirectedEdge(2, 7, 0.34));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 2, -1.20));
        edgeWeightedDigraph.addEdge(new DirectedEdge(3, 6, 0.52));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 0, -1.40));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 4, -1.25));

        BellmanFordSP bellmanFord = new BellmanFordSP(edgeWeightedDigraph, 0);
        for (int i = 0; i < 8; i++) {
            System.out.printf("%.2f ", bellmanFord.distTo[i]);
        }
        System.out.println();

//        Stack<DirectedEdge> stack = bellmanFord.pathTo(6);
//        while (!stack.isEmpty()) {
//            DirectedEdge pop = stack.pop();
//            System.out.println(pop.from() + " -> " + pop.to());
//        }
    }
}
