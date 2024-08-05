package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 최대 유량
 * 미해결
 */
public class BOJ6086 {

    private static Map<String, Boolean> marked;
    private static Map<String, Edge> edgeTo;
    private static long totalFlow = 0;

    static class Graph {
        int V;
        int E;
        Map<String, List<Edge>> adj = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }
        public Graph() {

        }

        public void addEdge(String v, String w, Long capacity) {
            if (!adj.containsKey(v)) {
                adj.put(v, new ArrayList<>());
            }
            if (!adj.containsKey(w)) {
                adj.put(w, new ArrayList<>());
            }
            Edge edge = new Edge(v, w, capacity);
            adj.get(v).add(edge);
            adj.get(w).add(edge);
        }
    }

    static class Edge {
        String v;
        String w;
        long capacity;
        long flow = 0;

        public Edge(String v, String w, long capacity) {
            this.v = v;
            this.w = w;
            this.capacity = capacity;
        }

        public String other(String vertex) {
            if (vertex.equals(v)) {
                return w;
            } else if (vertex.equals(w)) {
                return v;
            } else {
                throw new IllegalArgumentException("Inconsistent edge");
            }
        }

        public long residualCapacityTo(String vertex) {
            if (vertex.equals(v)) {
                return flow;
            } else if (vertex.equals(w)) {
                return capacity - flow;
            } else {
                throw new IllegalArgumentException("Inconsistent edge");
            }
        }

        public void addResidualFlowTo(String vertex, long delta) {
            if (vertex.equals(v)) {
                flow -= delta;
            } else if (vertex.equals(w)) {
                flow += delta;
            } else {
                throw new IllegalArgumentException("Inconsistent edge");
            }
        }
    }

    public static boolean hasAugmentingPath(Graph graph, String s, String t) {
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
                if (!marked.get(w) && edge.residualCapacityTo(w) > 0) {
                    edgeTo.put(w, edge);
                    marked.put(w, true);
                    queue.add(w);
                }
            }
        }
        return marked.get(t);
    }

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Map<String, Long>> edgeMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            String v = split[0];
            String w = split[1];
            Long capacity = Long.parseLong(split[2]);
            if (edgeMap.containsKey(v)) {
                if (edgeMap.get(v).containsKey(w)) {
                    Long beforeCapacity = edgeMap.get(v).get(w);
                    edgeMap.get(v).put(w, beforeCapacity + capacity);
                } else {
                    edgeMap.get(v).put(w, capacity);
                }
            }
            else if (edgeMap.containsKey(w) && edgeMap.get(w).containsKey(v)) {
                    Long beforeCapacity = edgeMap.get(w).get(v);
                    edgeMap.get(w).put(v, beforeCapacity + capacity);
            }
            else {
                edgeMap.put(v, new HashMap<>());
                edgeMap.get(v).put(w, capacity);
            }
        }
        edgeMap.keySet().forEach(v -> {
            edgeMap.get(v).keySet().forEach(w -> {
                Long capacity = edgeMap.get(v).get(w);
                graph.addEdge(v, w, capacity);
            });
        });
        String s = "A";
        String t = "Z";
        while (hasAugmentingPath(graph, s, t)) {
            long bottle = Integer.MAX_VALUE;
            for (String v = t; !v.equals(s); v = edgeTo.get(v).other(v)) {
                bottle = Math.min(bottle, edgeTo.get(v).residualCapacityTo(v));
            }
            for (String v = t; !v.equals(s); v = edgeTo.get(v).other(v)) {
                edgeTo.get(v).addResidualFlowTo(v, bottle);
            }
            totalFlow += bottle;
        }

        System.out.println(totalFlow);
    }
}
