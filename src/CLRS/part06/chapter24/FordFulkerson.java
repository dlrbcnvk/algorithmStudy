package CLRS.part06.chapter24;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FordFulkerson {

    private int totalFlow;

    private Map<String, Boolean> marked;
    private Map<String, Edge> edgeTo;

    class Graph {
        int V;
        int E;

        Map<String, List<Edge>> adj = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }

        public void addVertex(String value) {
            adj.put(value, new ArrayList<>());
        }

        public void addEdge(String from, String to, int capacity) {
            Edge edge = new Edge(from, to, capacity);
            adj.get(from).add(edge);
            adj.get(to).add(edge);
        }
    }

    class Edge {
        final String from;
        final String to;
        final int capacity;
        int flow = 0;

        public Edge(String from, String to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }

        public String other(String v) {
            if (v.equals(from)) {
                return to;
            } else if (v.equals(to)) {
                return from;
            } else {
                throw new IllegalArgumentException("간선에 해당 정점이 존재하지 않습니다.");
            }
        }

        public int residualCapacity(String vertex) {
            if (vertex.equals(from)) {
                // 역방향
                return flow;
            } else if (vertex.equals(to)) {
                // 정방향
                return capacity - flow;
            } else {
                throw new IllegalArgumentException("간선에 해당 정점이 존재하지 않습니다.");
            }
        }

        public void addResidualFlowTo(String vertex, int value) {
            if (vertex.equals(from)) {
                flow -= value;
            } else if (vertex.equals(to)) {
                flow += value;
            } else {
                throw new IllegalArgumentException("간선에 해당 정점이 존재하지 않습니다.");
            }
        }
    }


    public void solution(String[] vertices, String[][] edges, String s, String t) {
        // init
        Graph graph = new Graph(vertices.length, edges.length);
        for (String value : vertices) {
            graph.addVertex(value);
        }
        for (int i = 0; i < edges.length; i++) {
            String from = edges[i][0];
            String to = edges[i][1];
            int capacity = Integer.parseInt(edges[i][2]);

            graph.addEdge(from, to, capacity);
        }

        // flow 추가할 경로를 찾는다.
        while (hasAugmentingPath(graph, s, t)) {
            // 찾아낸 경로 중 가능한 가장 작은 값으로 경로의 간선 각각에 flow 를 추가한다.
            int bottle = Integer.MAX_VALUE;
            for (String v = t; !v.equals(s); v = edgeTo.get(v).other(v)) {
                bottle = Math.min(bottle, edgeTo.get(v).residualCapacity(v));
            }

            for (String v = t; !v.equals(s); v = edgeTo.get(v).other(v)) {
                edgeTo.get(v).addResidualFlowTo(v, bottle);

                System.out.print(v);
                System.out.print(" <- ");
            }
            System.out.print(s + " (" + bottle + ")\n");
            totalFlow += bottle;
        }

        System.out.println(totalFlow);
    }

    public boolean hasAugmentingPath(Graph graph, String s, String t) {
        marked = new HashMap<>();
        for (String key : graph.adj.keySet()) {
            marked.put(key, false);
        }
        edgeTo = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        marked.put(s, true);
        queue.add(s);
        while (!queue.isEmpty()) {
            String v = queue.poll();
            for (Edge edge : graph.adj.get(v)) {
                String w = edge.other(v);
                if (!marked.get(w) && edge.residualCapacity(w) > 0) {
                    edgeTo.put(w, edge);
                    marked.put(w, true);
                    queue.add(w);
                }
            }
        }
        return marked.get(t);
    }


    public static void main(String[] args) {
        FordFulkerson fordFulkerson = new FordFulkerson();
        fordFulkerson.solution(
                new String[]{"0", "1", "2", "3", "4", "5"},
                new String[][]{
                        {"0", "1", "2"},
                        {"0", "2", "3"},
                        {"1", "3", "3"},
                        {"1", "4", "1"},
                        {"2", "3", "1"},
                        {"2", "4", "1"},
                        {"3", "5", "2"},
                        {"4", "5", "3"},
                },
                "0", "5"
        );
    }
}
