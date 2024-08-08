package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 민준이와 마산 그리고 건우
 * https://www.acmicpc.net/problem/18223
 */
public class BOJ18223 {

    private static int T;
    private static int N;
    private static int M;
    private static int K;


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

    public static int[] dijkstra(int s, Graph graph) {
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

        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            result[i] = graph.vertexMap.get(i).d;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

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
            K = Integer.parseInt(br.readLine());

            int[] friends = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            int[][] arr = new int[K][N + 1];
            for (int i = 0; i < K; i++) {
                arr[i] = dijkstra(friends[i], graph);
            }
            int result = 0;
            int resultSum = Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                int sum = 0;
                for (int k = 0; k < K; k++) {
                    sum += arr[k][i];
                }
                if (sum < resultSum) {
                    resultSum = sum;
                    result = i;
                }
            }

            System.out.println(result);
        }
    }
}

