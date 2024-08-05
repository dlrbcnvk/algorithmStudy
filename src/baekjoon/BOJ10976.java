package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 피난
 * https://www.acmicpc.net/problem/10976
 */
public class BOJ10976 {

    static Map<Integer, Boolean> marked;
    static Map<Integer, Edge> edgeTo;
    static List<Integer> result = new ArrayList<>();

    static class Graph {
        int V;
        int E;
        Map<Integer, List<Edge>> adj = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }

        public Graph() {
        }

        public void addEdge(int v, int w, int N) {
            Edge edge = null;
            if (v == 1 || w == N) {
                edge = new Edge(v, w, 1);
            } else {
                edge = new Edge(v, w, Integer.MAX_VALUE);
            }
            if (!adj.containsKey(v)) {
                adj.put(v, new ArrayList<>());
            }
            if (!adj.containsKey(w)) {
                adj.put(w, new ArrayList<>());
            }
            adj.get(v).add(edge);
            adj.get(w).add(edge);
        }
    }

    static class Edge {
        int v;
        int w;
        int capacity;
        int flow = 0;

        public Edge(int v, int w, int capacity) {
            this.v = v;
            this.w = w;
            this.capacity = capacity;
        }

        public int other(int vertex) {
            if (vertex == v) {
                return w;
            } else if (vertex == w) {
                return v;
            } else {
                throw new IllegalArgumentException("간선에 해당 정점이 존재하지 않습니다.");
            }
        }

        public int residualCapacity(int vertex) {
            if (vertex == v) {
                return flow;
            } else if (vertex == w) {
                return capacity - flow;
            } else {
                throw new IllegalArgumentException("간선에 해당 정점이 존재하지 않습니다.");
            }
        }

        public void addResidualFlowTo(int vertex, int value) {
            if (vertex == v) {
                flow -= value;
            } else if (vertex == w) {
                flow += value;
            } else {
                throw new IllegalArgumentException("간선에 해당 정점이 존재하지 않습니다.");
            }
        }
    }

    public static void solution(BufferedReader br, int N, int M) throws IOException {
        int s = 1;
        int t = N;
        Graph graph = new Graph();
        for (int i = 0; i < M; i++) {
            String[] split = br.readLine().split(" ");
            int v = Integer.parseInt(split[0]);
            int w = Integer.parseInt(split[1]);
            graph.addEdge(v, w, N);
        }
        int totalFlow = 0;
        while (hasAugmentingPath(graph, s, t)) {
            Integer bottle = Integer.MAX_VALUE;
            for (Integer v = t; !v.equals(s); v = edgeTo.get(v).other(v)) {
                bottle = Math.min(bottle, edgeTo.get(v).residualCapacity(v));
            }
            for (Integer v = t; !v.equals(s); v = edgeTo.get(v).other(v)) {
                edgeTo.get(v).addResidualFlowTo(v, bottle);
            }
            totalFlow += bottle;
        }
        result.add(totalFlow);
    }

    public static boolean hasAugmentingPath(Graph graph, int s, int t) {
        marked = new HashMap<>();
        for (Integer key : graph.adj.keySet()) {
            marked.put(key, false);
        }
        edgeTo = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        marked.put(s, true);
        queue.add(s);
        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            for (Edge edge : graph.adj.get(v)) {
                Integer w = edge.other(v);
                if (!marked.get(w) && edge.residualCapacity(w) > 0) {
                    edgeTo.put(w, edge);
                    marked.put(w, true);
                    queue.add(w);
                }
            }
        }
        return marked.get(t);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] split = br.readLine().split(" ");
            int N = Integer.parseInt(split[0]);
            int M = Integer.parseInt(split[1]);
            solution(br, N, M);
        }
        result.stream().forEach(System.out::println);
    }
}
