package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 최소비용 구하기 2
 * https://www.acmicpc.net/problem/11779
 */
public class BOJ11779 {

    static Map<Integer, Map<Integer, Edge>> edgeMap = new HashMap<>();

    static class Graph {
        int V;
        int E;
        Map<Integer, Vertex> vertexMap = new HashMap<>();
        Map<Integer, List<Edge>> adj = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }

        public void addVertex(int value) {
            if (vertexMap.containsKey(value)) {
                return;
            }
            vertexMap.put(value, new Vertex(value));
        }

        public void addEdge(int from, int to, long cost) {
            if (!adj.containsKey(from)) {
                adj.put(from, new ArrayList<>());
            }
            if (edgeMap.containsKey(from)
                    && edgeMap.get(from).containsKey(to)
                    && edgeMap.get(from).get(to).cost < cost) {
                return;
            }
            Edge edge = new Edge(from, to, cost);
            adj.get(from).add(edge);
            edgeMap.get(from).put(to, edge);
        }
    }

    static class Vertex {
        int value;
        long d = Integer.MAX_VALUE;
        Vertex parent = null;

        public Vertex(int value) {
            this.value = value;
        }
    }

    static class Edge {
        int from;
        int to;
        long cost;

        public Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            edgeMap.put(i, new HashMap<>());
        }
        Graph graph = new Graph(n, m);
        for (int i = 0; i < m; i++) {
            String[] split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            long cost = Long.parseLong(split[2]);
            graph.addVertex(from);
            graph.addVertex(to);
            graph.addEdge(from, to, cost);
        }
        String[] split = br.readLine().split(" ");
        int s = Integer.parseInt(split[0]);
        int t = Integer.parseInt(split[1]);

        Vertex startVertex = graph.vertexMap.get(s);
        startVertex.d = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingLong(vertex -> vertex.d));
        graph.vertexMap.keySet().forEach(key -> pq.add(graph.vertexMap.get(key)));
        while (!pq.isEmpty()) {
            Vertex fromVertex = pq.poll();

            if (graph.adj.containsKey(fromVertex.value)) {
                for (Edge edge : graph.adj.get(fromVertex.value)) {
                    int to = edge.to;
                    Vertex toVertex = graph.vertexMap.get(to);
                    if (toVertex.d > fromVertex.d + edge.cost) {
                        toVertex.d = fromVertex.d + edge.cost;
                        toVertex.parent = fromVertex;
                        pq.remove(toVertex);
                        pq.add(toVertex);
                    }
                }
            }
        }
        getPath(graph, s, t);
    }

    private static void getPath(Graph graph, int s, int t) {
        Stack<Integer> stack = new Stack<>();
        Vertex vertex = graph.vertexMap.get(t);
        long totalCost = vertex.d;
        stack.push(t);
        while (vertex.parent != null) {
            vertex = vertex.parent;
            stack.push(vertex.value);
        }

        System.out.println(totalCost);
        System.out.println(stack.size());

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
            if (!stack.isEmpty()) {
                System.out.print(" ");
            }
        }
    }
}
