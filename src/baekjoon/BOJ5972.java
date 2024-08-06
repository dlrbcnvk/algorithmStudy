package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 택배 배송
 * https://www.acmicpc.net/problem/5972
 */
public class BOJ5972 {

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
            Vertex fromVertex = vertexMap.get(from);
            Vertex toVertex = vertexMap.get(to);
            adj.get(from).add(new Edge(toVertex, weight));
            adj.get(to).add(new Edge(fromVertex, weight));
        }
    }

    static class Vertex {
        int id;
        long d = Long.MAX_VALUE;
        Vertex parent;

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
        int M = split[1];
        Graph graph = new Graph();
        for (int i = 1; i <= N; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < M; i++) {
            split = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int a = split[0];
            int b = split[1];
            int s = split[2];
            // a -> b
            graph.addEdge(a, b, s);
        }
        Vertex startVertex = graph.vertexMap.get(1);
        startVertex.d = 0L;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(startVertex.id);
        while (!pq.isEmpty()) {
            int from = pq.poll();
            Vertex fromVertex = graph.vertexMap.get(from);
            for (Edge edge : graph.adj.get(fromVertex.id)) {
                Vertex toVertex = edge.toVertex;
                if (toVertex.parent == fromVertex && edge.weight == 0) {
                    continue;
                }
                if (toVertex.d > fromVertex.d + edge.weight) {
                    toVertex.d = fromVertex.d + edge.weight;
                    toVertex.parent = fromVertex;
                    pq.add(edge.toVertex.id);
                }
            }
        }
        Vertex result = graph.vertexMap.get(N);
        System.out.println(result.d);
    }
}
