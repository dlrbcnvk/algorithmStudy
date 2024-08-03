package CLRS.chapter20;

import java.util.*;

/**
 * 다익스트라 알고리즘
 * 가중 방향 그래프에서 모든 간선의 가중치가 음이 아닌 경우에 단일 출발점 최단 경로 문제를 푼다.
 *
 * 우선순위큐 기반의 너비우선탐색
 * relax() 필요
 */
public class Dijkstra {
    class Graph {
        int V;
        int E;
        Map<String, Vertex> vertexMap = new HashMap<>();
        Map<String, List<Edge>> adj = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }


        public void addVertex(String value) {
            vertexMap.put(value, new Vertex(value));
            adj.put(value, new ArrayList<>());
        }

        public void addEdge(String from, String to, int weight) {
            Vertex toVertex = vertexMap.get(to);
            adj.get(from).add(new Edge(toVertex, weight));
        }
    }

    class Vertex {
        String value;
        int d = Integer.MAX_VALUE;
        Vertex parent = null;

        public Vertex(String value) {
            this.value = value;
        }
    }

    class Edge {
        Vertex to;
        int weight;

        public Edge(Vertex to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public void solution(String[] vertices, String[][] edges) {
        Graph graph = new Graph(vertices.length, edges.length);
        for (String value : vertices) {
            graph.addVertex(value);
        }
        for (String[] edge : edges) {
            String from = edge[0];
            String to = edge[1];
            int weight = Integer.parseInt(edge[2]);
            graph.addEdge(from, to, weight);
        }

        Set<String> S = new HashSet<>();
        Vertex startVertex = graph.vertexMap.get("s");
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

        graph.vertexMap.keySet().forEach(key -> {
            getPath(graph, key);
        });
    }

    public void getPath(Graph graph, String dest) {
        Stack<String> stack = new Stack<>();
        Vertex vertex = graph.vertexMap.get(dest);
        int pathWeight = vertex.d;
        stack.push(vertex.value);
        while (vertex.parent != null) {
            vertex = vertex.parent;
            stack.push(vertex.value);
        }

        while (!stack.isEmpty()) {
            String value = stack.pop();
            System.out.print(value);
            if (!stack.isEmpty()) {
                System.out.print(" -> ");
            }
        }
        System.out.print(" (" + pathWeight + ")");
        System.out.println();
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.solution(
                new String[]{"s", "t", "x", "y", "z"},
                new String[][]{
                        {"s", "t", "10"},
                        {"t", "x", "1"},
                        {"s", "y", "5"},
                        {"t", "y", "2"},
                        {"y", "t", "3"},
                        {"y", "x", "9"},
                        {"y", "z", "2"},
                        {"z", "s", "7"},
                        {"z", "x", "6"},
                        {"x", "z", "4"},
                }
        );
    }
}
