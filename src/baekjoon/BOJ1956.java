package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 운동
 * https://www.acmicpc.net/problem/1956
 * 미해결
 */
public class BOJ1956 {

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
            Edge edge = new Edge(from, to, weight);
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
        int from;
        int to;
        Long weight;

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static public long dijkstra(int s, int V, int E, int[][] edges) {
        Graph graph = new Graph(V, E);
        for (int i = 1; i <= V; i++) {
            graph.addVertex(i);
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph.addEdge(from, to, weight);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((v1, v2) -> {
            Vertex vertex1 = graph.vertexMap.get(v1);
            Vertex vertex2 = graph.vertexMap.get(v2);
            if (vertex1.d < vertex2.d) {
                return -1;
            } else {
                return 1;
            }
        });
        boolean[] marked = new boolean[V + 1];
        Vertex startVertex = graph.vertexMap.get(s);
        startVertex.d = 0L;
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            Integer from = queue.poll();
            Vertex fromVertex = graph.vertexMap.get(from);
            for (Edge edge : graph.adj.get(from)) {
                Vertex toVertex = graph.vertexMap.get(edge.to);
                if (toVertex.id == s &&
                        (toVertex.d == 0 || toVertex.d > fromVertex.d + edge.weight)) {
                        toVertex.d = fromVertex.d + edge.weight;
                } else if (!marked[edge.to] && toVertex.d > fromVertex.d + edge.weight) {
                    toVertex.d = fromVertex.d + edge.weight;
                    marked[edge.to] = true;
                    queue.add(edge.to);
                }
            }
        }
        return startVertex.d;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int V = Integer.parseInt(split[0]);
        int E = Integer.parseInt(split[1]);
        int[][] edges = new int[E][3];
        for (int i = 0; i < E; i++) {
            edges[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        Long minCycle = Long.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            Long cycle = dijkstra(i, V, E, edges);
            if (!cycle.equals(0L) && cycle < minCycle) {
                minCycle = cycle;
            }
        }
        if (minCycle.equals(Long.MAX_VALUE)) {
            System.out.println(-1);
        } else {
            System.out.println(minCycle);
        }
    }
}
