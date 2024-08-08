package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주간 미팅
 * https://www.acmicpc.net/problem/12834
 */
public class BOJ12834 {

    private static int N;
    private static int V;
    private static int E;
    private static int A;
    private static int B;


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

    public static int dijkstra(int s, Graph graph) {
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

        Vertex aVertex = graph.vertexMap.get(A);
        Vertex bVertex = graph.vertexMap.get(B);
        if (aVertex.d == Integer.MAX_VALUE) {
            aVertex.d = -1;
        }
        if (bVertex.d == Integer.MAX_VALUE) {
            bVertex.d = -1;
        }
        return aVertex.d + bVertex.d;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        N = split[0];
        V = split[1];
        E = split[2];

        split = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        A = split[0];
        B = split[1];

        int[] houses = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Graph graph = new Graph();
        for (int i = 1; i <= V; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < E; i++) {
            split = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int a = split[0];
            int b = split[1];
            int weight = split[2];
            // a -> b
            graph.addEdge(a, b, weight);
            graph.addEdge(b, a, weight);
        }

        int sum = 0;
        for (int i = 0; i < houses.length; i++) {
            sum += dijkstra(houses[i], graph);
        }

        System.out.println(sum);
    }
}

