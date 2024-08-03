package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 서강그라운드
 * https://www.acmicpc.net/problem/14938
 * 다익스트라 알고리즘
 * 가중 방향 그래프에서 모든 간선의 가중치가 음이 아닌 경우에 단일 출발점 최단 경로 문제를 푼다.
 *
 * 우선순위큐 기반의 너비우선탐색
 * relax() 필요
 *
 * 미해결
 */
public class BOJ14938 {
    static class Graph {
        int V;
        int E;
        Map<Integer, Vertex> vertexMap = new HashMap<>();
        Map<Integer, List<Edge>> adj = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }


        public void addVertex(int value, int count) {
            vertexMap.put(value, new Vertex(value, count));
            adj.put(value, new ArrayList<>());
        }

        public void addEdge(int from, int to, int weight) {
            Vertex fromVertex = vertexMap.get(from);
            Vertex toVertex = vertexMap.get(to);
            adj.get(from).add(new Edge(toVertex, weight));
            adj.get(to).add(new Edge(fromVertex, weight));
        }
    }

    static class Vertex {
        int value;
        int d = Integer.MAX_VALUE;
        Vertex parent = null;
        int count;

        public Vertex(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    static class Edge {
        Vertex to;
        int weight;

        public Edge(Vertex to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static int solution(int[] counts, String[][] edges, int start, int m) {
        Graph graph = new Graph(counts.length, edges.length);
        for (int i = 0; i < counts.length; i++) {
            graph.addVertex(i + 1, counts[i]);
        }
        for (String[] edge : edges) {
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);
            int weight = Integer.parseInt(edge[2]);
            graph.addEdge(from, to, weight);
        }

        Vertex startVertex = graph.vertexMap.get(start);
        startVertex.d = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(vertex -> vertex.d));
        graph.vertexMap.keySet().forEach(key -> {
            pq.add(graph.vertexMap.get(key));
        });
        while (!pq.isEmpty()) {
            Vertex fromVertex = pq.poll();

            for (Edge edge : graph.adj.get(fromVertex.value)) {
                Vertex toVertex = edge.to;
                if (toVertex.d > fromVertex.d + edge.weight) {
                    toVertex.d = fromVertex.d + edge.weight;
                    toVertex.parent = fromVertex;
                    pq.remove(toVertex);
                    pq.add(toVertex);
                }
            }
        }

        int totalCount = 0;
        for (int key : graph.vertexMap.keySet()){
            Vertex destVertex = graph.vertexMap.get(key);
            if (destVertex.d <= m) {
                totalCount += destVertex.count;
            }
        }

        return totalCount;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int r = Integer.parseInt(split[2]);

        int[] counts = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        String[][] edges = new String[r][3];
        for (int i = 0; i < r; i++) {
            edges[i] = br.readLine().split(" ");
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int result = solution(counts, edges, i, m);
            answer = Math.max(result, answer);
        }

        System.out.println(answer);
    }
}
