package sedgewick_book.chapter04;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 다익스트라 알고리즘은 음수 가중치가 없는 가중 간선 방향 그래프에서 단일 원점 최단 경로 문제를 해결한다.
 * 다익스트라 알고리즘은 E개의 간선과 V개의 정점을 가지는 가중 간선 방향 그래프에서, 단일 원점최단 경로를 찾는 데
 * V에 비례하는 추가 공간과 ElogV에 비례하는(최악조건) 시간을 소요한다.
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo; // 원점 ~ 목적지 현재까지 결정된 최단 거리
    private PriorityQueue<PQNode> pq; // key, value(우선순위) 담는 우선순위 큐

    public DijkstraSP(EdgeWeightedDigraph graph, int s) {
        edgeTo = new DirectedEdge[graph.V()];
        distTo = new double[graph.V()];
        pq = new PriorityQueue<>(graph.V());

        for (int v = 0; v < graph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;
        PQNode node = new PQNode(s, 0);
        pq.add(node);
        while (!pq.isEmpty()) {
            PQNode poll = pq.poll();
            int key = poll.getKey();
            relax(graph, key);
        }
    }

    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                // 거리 갱신
                distTo[w] = distTo[v] + e.getWeight();
                // edge relaxing
                edgeTo[w] = e;

                // vertex relaxing
                // pq.remove(Object o) -> o.equals()가 true로 나오는 것들 지움
                pq.remove(new PQNode(w, 0));
                pq.add(new PQNode(w, (int) distTo[w]));
            }
        }
    }

    private double distTo(int v) {
        return distTo[v];
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
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 2, 0.40));
        edgeWeightedDigraph.addEdge(new DirectedEdge(3, 6, 0.52));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 0, 0.58));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 4, 0.93));

        DijkstraSP dijkstraSP = new DijkstraSP(edgeWeightedDigraph, 0);

        Stack<DirectedEdge> stack = dijkstraSP.pathTo(6);
        while (!stack.isEmpty()) {
            DirectedEdge pop = stack.pop();
            System.out.println(pop.from() + " -> " + pop.to());
        }
    }
}
