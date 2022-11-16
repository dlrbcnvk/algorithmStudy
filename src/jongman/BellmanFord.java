package jongman;

import sedgewick_book.chapter04.DirectedEdge;
import sedgewick_book.chapter04.EdgeWeightedDigraph;

import java.util.Stack;


public class BellmanFord {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    boolean updated;

    public BellmanFord(EdgeWeightedDigraph graph, int src) {
        edgeTo = new DirectedEdge[graph.V()];
        distTo = new double[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[src] = 0;

        // V번 순회한다
        for (int iter = 0; iter < graph.V(); iter++) {
            updated = false;
            for (int from = 0; from < graph.V(); from++) {
                for (DirectedEdge edge : graph.adj(from)) {
                    int to = edge.to();
                    double cost = edge.getWeight();
                    // (here, there) 간선을 따라 완화를 시도한다
                    if (distTo[to] > distTo[from] + cost) {
                        // 성공
                        distTo[to] = distTo[from] + cost;
                        edgeTo[to] = edge;
                        updated = true;
                    }
                }
            }
            // 모든 간선에 대해 완화가 실패했을 경우 V-1번도 돌 필요 없이 곧장 종료한다.
            if(!updated)
                break;
        }
        // V번째 순회에서도 완화가 성공했다면 음수 사이클이 있다.
        if(updated)
            distTo = new double[graph.V()];
    }

    private boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Stack<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
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

        BellmanFord bellmanFord = new BellmanFord(edgeWeightedDigraph, 0);
        for (int i = 0; i < 8; i++) {
            System.out.printf("%.2f ", bellmanFord.distTo[i]);
        }
        System.out.println();

        Stack<DirectedEdge> stack = bellmanFord.pathTo(6);
        while (!stack.isEmpty()) {
            DirectedEdge pop = stack.pop();
            System.out.println(pop.from() + " -> " + pop.to());
        }
    }
}
