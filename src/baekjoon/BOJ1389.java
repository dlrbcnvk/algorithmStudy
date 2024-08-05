package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 케빈 베이컨의 6단계 법칙
 * https://www.acmicpc.net/problem/1389
 */
public class BOJ1389 {

    static int N;
    static int M;

    static class Graph {
        int V;
        int E;
        Map<Integer, Vertex> vertexMap = new HashMap<>();
        Map<Integer, Set<Integer>> adj = new HashMap<>();

        public void addVertex(Integer id) {
            Vertex vertex = new Vertex(id);
            vertexMap.put(id, vertex);
            adj.put(id, new HashSet<>());
        }
    }

    static class Vertex {
        Integer id;
        int d;

        public Vertex(Integer id) {
            this.id = id;
            this.d = Integer.MAX_VALUE;
        }
    }

    static int dijkstra(int s, int[][] edges) {
        Graph graph = new Graph();
        for (int i = 1; i <= N; i++) {
            graph.addVertex(i);
        }
        for (int[] edge : edges) {
            int v = edge[0];
            int w = edge[1];
            graph.adj.get(v).add(w);
            graph.adj.get(w).add(v);
        }
        boolean[] marked = new boolean[N + 1];
        Vertex startVertex = graph.vertexMap.get(s);
        startVertex.d = 0;
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(startVertex);
        marked[startVertex.id] = true;
        while (!queue.isEmpty()) {
            Vertex fromVertex = queue.poll();
            int from = fromVertex.id;
            for (int to : graph.adj.get(from)) {
                Vertex toVertex = graph.vertexMap.get(to);
                if (toVertex.d == Integer.MAX_VALUE || toVertex.d > fromVertex.d + 1) {
                    toVertex.d = fromVertex.d + 1;
                    marked[toVertex.id] = true;
                    queue.add(toVertex);
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (i == s) {
                continue;
            }
            Vertex vertex = graph.vertexMap.get(i);
            result += vertex.d;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        int[][] edges = new int[M][2];
        for (int i = 0; i < M; i++) {
            edges[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        int finalKbCount = Integer.MAX_VALUE;
        int minKbCountId = 1;
        for (int i = 1; i <= N; i++) {
            int kbCount = dijkstra(i, edges);
            if (finalKbCount > kbCount) {
                finalKbCount = kbCount;
                minKbCountId = i;
            }
        }
        System.out.println(minKbCountId);
    }
}
