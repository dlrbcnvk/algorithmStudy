package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 해킹
 * https://www.acmicpc.net/problem/10282
 */
public class BOJ10282 {

    static class Graph {
        int V;
        int E;
        Map<Integer, Vertex> vertexMap = new HashMap<>();
        Map<Integer, List<Edge>> adj = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }

        public void addVertex(int id) {
            Vertex vertex = new Vertex(id);
            vertexMap.put(id, vertex);
            adj.put(id, new ArrayList<>());
        }

        public void addEdge(int from, int to, int weight) {
            Vertex toVertex = vertexMap.get(to);
            Edge edge = new Edge(toVertex, weight);
            adj.get(from).add(edge);
        }
    }

    static class Vertex {
        int id;
        Long d = Long.MAX_VALUE;

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

    public static void solution(BufferedReader br) throws IOException {
        int[] split = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int V = split[0];
        int E = split[1];
        int c = split[2];
        Graph graph = new Graph(V, E);
        for (int i = 1; i <= V; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < E; i++) {
            split = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
            int a = split[0];
            int b = split[1];
            int s = split[2];
            // b -> a
            graph.addEdge(b, a, s);
        }
        Vertex startVertex = graph.vertexMap.get(c);
        startVertex.d = 0L;
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingLong(vertex -> vertex.d));
        pq.add(startVertex);
        while (!pq.isEmpty()) {
            Vertex fromVertex = pq.poll();
            for (Edge edge : graph.adj.get(fromVertex.id)) {
                Vertex toVertex = edge.toVertex;
                if (toVertex.d > fromVertex.d + edge.weight) {
                    toVertex.d = fromVertex.d + edge.weight;
                    pq.remove(toVertex);
                    pq.add(toVertex);
                }
            }
        }
        int count = 0;
        Long finalTime = 0L;
        for (Vertex vertex : graph.vertexMap.values()) {
            if (vertex.d.equals(Long.MAX_VALUE)) {
                continue;
            }
            count++;
            finalTime = Math.max(finalTime, vertex.d);
        }
        System.out.println(count + " " + finalTime);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            solution(br);
        }
    }
}
