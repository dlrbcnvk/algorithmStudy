package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 택배
 * https://www.acmicpc.net/problem/1719
 */
public class BOJ1719 {

    private static int N;
    private static int M;

    static class Graph {
        int V;
        int E;
        Map<Integer, Vertex> vertexMap = new HashMap<>();
        Map<Integer, List<Edge>> adj = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }

        public Graph() {
        }

        public void addVertex(int id) {
            Vertex vertex = new Vertex(id);
            vertexMap.put(id, vertex);
            adj.put(id, new ArrayList<>());
        }

        public void addEdge(int from, int to, int weight) {
            Edge edge = new Edge(from, to, weight);
            adj.get(from).add(edge);
            adj.get(to).add(edge);
        }
    }

    static class Vertex {
        int id;
        int d = Integer.MAX_VALUE;
        Vertex parent;


        public Vertex(int id) {
            this.id = id;
        }
    }

    static class Edge {
        int v;
        int w;
        int weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int other(int value) {
            if (value == v) {
                return w;
            } else if (value == w) {
                return v;
            } else {
                throw new IllegalArgumentException("zzz");
            }
        }
    }

    public static void dijkstra(int s, Graph graph) {
        graph.vertexMap.values().forEach(vertex -> {
            vertex.d = Integer.MAX_VALUE;
            vertex.parent = null;
        });
        Vertex startVertex = graph.vertexMap.get(s);
        startVertex.d = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparing(v -> v.d));
        queue.add(startVertex);
        while (!queue.isEmpty()) {
            Vertex fromVertex = queue.poll();
            int from = fromVertex.id;
            for (Edge edge : graph.adj.get(fromVertex.id)) {
                int to = edge.other(from);
                Vertex toVertex = graph.vertexMap.get(to);

                if (toVertex.d > fromVertex.d + edge.weight) {
                    toVertex.d = fromVertex.d + edge.weight;
                    toVertex.parent = fromVertex;
                    queue.add(toVertex);
                }
            }
        }

        for (int i=1; i<=N; i++) {
            if (s == i || graph.vertexMap.get(i).d == Integer.MAX_VALUE) {
                System.out.print("-");
            }

            Vertex vertex = graph.vertexMap.get(i);
            while (vertex.parent != null && vertex.parent.id != s) {
                vertex = vertex.parent;
            }
            if (vertex.parent != null) {
                System.out.print(vertex.id);
            }
            if (i < N) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];

        Graph graph = new Graph();
        for (int i = 1; i <= N; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < M; i++) {
            split = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int a = split[0];
            int b = split[1];
            int weight = split[2];
            // a -> b
            graph.addEdge(a, b, weight);
            graph.addEdge(b, a, weight);
        }
        for (int s = 1; s <= N; s++) {
            dijkstra(s, graph);
        }
    }
}

