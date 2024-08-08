package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Apple Delivery
 * https://www.acmicpc.net/problem/5944
 *
 * 미해결 - 메모리 초과
 */
public class BOJ5944 {

    private static int N;
    private static int M;
    private static int S;
    private static int A;
    private static int B;

    static class Graph {
        Map<Integer, Vertex> vertexMap = new HashMap<>();
        Map<Integer, List<Edge>> adj = new HashMap<>();

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
                    queue.add(toVertex);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        N = split[1];
        M = split[0];
        S = split[2];
        A = split[3];
        B = split[4];

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

        dijkstra(S, graph);
        int minSA = graph.vertexMap.get(A).d;
        int minSB = graph.vertexMap.get(B).d;
        dijkstra(A, graph);
        int minAB = graph.vertexMap.get(B).d;
        dijkstra(B, graph);
        int minBA = graph.vertexMap.get(A).d;
        System.out.println(Math.min(minSA + minAB, minSB + minBA));
    }
}

