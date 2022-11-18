package sedgewick_book.chapter06;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
    private boolean[] marked;           // 경로 s->v가 잔여 그래프에 존재하는가?
    private FlowEdge[] edgeTo;          // 최단 s->v의 최단경로에서 마지막 간선
    private double value;               // 현재까지 맥스-플로우 값

    public FordFulkerson(FlowNetwork G, int s, int t) {
        // s에서 t로의 흐름 네트워크 G에서 맥스-플로우 찾기
        while (hasAugmentingPath(G, s, t)) {
            // 보강-경로가 존재한다면 이용한다.

            // 병목 용량을 계산한다.
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }

            // 보강 흐름
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }

            value += bottle;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        marked = new boolean[G.V()];
        edgeTo = new FlowEdge[G.V()];
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (FlowEdge edge : G.adj(v)) {
                int w = edge.other(v);
                if (edge.residualCapacityTo(w) > 0 && !marked[w]) {
                    // 표시되지 않은 정점으로의 모든 간선에 대해 (잔여 네트워크에서)
                    edgeTo[w] = edge;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
        return marked[t];
    }

    public double value() { return value; }
    public boolean inCut(int v) { return marked[v]; }

    public static void main(String[] args) {
        FlowEdge edge1 = new FlowEdge(0, 1, 2);
        FlowEdge edge2 = new FlowEdge(0, 2, 3);
        FlowEdge edge3 = new FlowEdge(1, 3, 3);
        FlowEdge edge4 = new FlowEdge(1, 4, 1);
        FlowEdge edge5 = new FlowEdge(2, 3, 1);
        FlowEdge edge6 = new FlowEdge(2, 4, 1);
        FlowEdge edge7 = new FlowEdge(3, 5, 2);
        FlowEdge edge8 = new FlowEdge(4, 5, 3);

        FlowNetwork G = new FlowNetwork(6);
        G.addEdge(edge1);
        G.addEdge(edge2);
        G.addEdge(edge3);
        G.addEdge(edge4);
        G.addEdge(edge5);
        G.addEdge(edge6);
        G.addEdge(edge7);
        G.addEdge(edge8);

        int s = 0, t = G.V() - 1;
        FordFulkerson fordFulkerson = new FordFulkerson(G, s, t);

        System.out.println("Max flow from " + s + " to " + t);
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge edge : G.adj(v)) {
                if (v == edge.from() && edge.flow() > 0) {
                    System.out.println(" " + edge);
                }
            }
        }
        System.out.println("Max flow value = " + fordFulkerson.value);

    }
}
