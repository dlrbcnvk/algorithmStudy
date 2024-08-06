package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * K번째 최단경로 찾기
 * https://www.acmicpc.net/problem/1854
 *
 * 미해결
 */
public class BOJ1854 {

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
            adj.get(from).add(new Edge(toVertex, weight));
        }
    }

    static class Vertex {
        int id;
        long d = Long.MAX_VALUE;
        List<Long> dList = new ArrayList<>();

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
        int k = split[2];
        int[][] edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            split = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int a = split[0];
            int b = split[1];
            int s = split[2];
            // a -> b
            edges[i] = new int[]{a, b, s};
        }
        for (int t = 1; t <= N; t++) {
            Graph graph = new Graph();
            for (int i = 1; i <= N; i++) {
                graph.addVertex(i);
            }
            for (int i = 0; i < M; i++) {
                graph.addEdge(edges[i][0], edges[i][1], edges[i][2]);
            }
            for (int i = 0; i < k; i++) {
                Vertex startVertex = graph.vertexMap.get(1);
                if (i == 0) {
                    startVertex.d = 0L;
                }

                PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingLong(vertex -> vertex.d));
                queue.add(startVertex);
                while (!queue.isEmpty()) {
                    Vertex fromVertex = queue.poll();
                    for (Edge edge : graph.adj.get(fromVertex.id)) {
                        Vertex toVertex = edge.toVertex;
                        if (toVertex.d > fromVertex.d + edge.weight) {
                            toVertex.d = fromVertex.d + edge.weight;
                            queue.add(toVertex);
                        }
                    }
                }
            }
            System.out.println(graph.vertexMap.get(t).d);
        }

    }
}
