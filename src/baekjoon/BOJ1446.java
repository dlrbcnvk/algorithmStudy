package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 지름길
 * https://www.acmicpc.net/problem/1446
 */
public class BOJ1446 {

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
            if (!vertexMap.containsKey(from)) {
                addVertex(from);
            }
            if (!vertexMap.containsKey(to)) {
                addVertex(to);
            }
            Vertex toVertex = vertexMap.get(to);
            Edge edge = new Edge(toVertex, weight);
            adj.get(from).add(edge);
        }
    }

    static class Vertex {
        int id;
        long d = Long.MAX_VALUE;

        public Vertex(int id) {
            this.id = id;
        }
    }

    static class Edge {
        Vertex toVertex;
        int weight;

        public Edge(Vertex toVertex, int weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int N = split[0];
        int D = split[1];
        Graph graph = new Graph();
        for (int i = 0; i <= D; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i <= D; i++) {
            graph.addEdge(i, i + 1, 1);
        }
        for (int i = 0; i < N; i++) {
            split = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int a = split[0];
            int b = split[1];
            int s = split[2];
            // a -> b
            graph.addEdge(a, b, s);
        }
        Vertex startVertex = graph.vertexMap.get(0);
        startVertex.d = 0L;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingLong(id -> graph.vertexMap.get(id).d));
        pq.add(startVertex.id);
        while (!pq.isEmpty()) {
            int from = pq.poll();
            Vertex fromVertex = graph.vertexMap.get(from);
            for (Edge edge : graph.adj.get(fromVertex.id)) {
                Vertex toVertex = edge.toVertex;
                if (toVertex.d > fromVertex.d + edge.weight) {
                    toVertex.d = fromVertex.d + edge.weight;
                    pq.add(edge.toVertex.id);
                }
            }
        }
        Vertex result = graph.vertexMap.get(D);
        System.out.println(result.d);
    }
}
