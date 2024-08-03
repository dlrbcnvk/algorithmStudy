package CLRS.chapter20;

import java.util.*;

/**
 * DAG-Shortest-Paths
 * 비순환 방향 그래프에서의 단일 출발점 최단 경로
 *
 * 먼저, 위상정렬한다.
 * 위상정렬된 순서대로 relax()
 * 앞의 것을 relax()하고 다음 것으로 넘어가면, 그 이전 것은 더이상 relax() 로 갱신할 필요 없다.
 */
public class DAGShortestPaths {

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
        Color color = Color.WHITE;

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

    public enum Color {
        WHITE, GRAY, BLACK
    }

    public void solution(String[] vertices, String[][] edges) {
        Graph graph = new Graph(vertices.length, edges.length);
        for (String value : vertices) {
            graph.addVertex(value);
        }
        for (String[] edge : edges) {
            graph.addEdge(edge[0], edge[1], Integer.parseInt(edge[2]));
        }

        Stack<Vertex> topologicalStack = new Stack<>();
        dfs(graph, "s", topologicalStack);

        // start 's'
        Vertex vertex = graph.vertexMap.get("s");
        vertex.d = 0;
        while (!topologicalStack.isEmpty()) {
            String key = topologicalStack.pop().value;
            graph.adj.get(key).stream().forEach(edge -> {
                Vertex fromVertex = graph.vertexMap.get(key);
                relax(fromVertex, edge);
            });
        }

        // get path
        graph.vertexMap.keySet().forEach(key -> {
            getPath(graph, key);
            Vertex destVertex = graph.vertexMap.get(key);
            System.out.print(" (" + destVertex.d + ")");
            System.out.println();
        });
        System.out.println();
    }

    public void dfs(Graph graph, String key, Stack<Vertex> topologicalStack) {
        Vertex fromVertex = graph.vertexMap.get(key);
        fromVertex.color = Color.GRAY;
        for (Edge edge : graph.adj.get(key)) {
            Vertex toVertex = edge.to;
            if (toVertex.color == Color.WHITE) {
                dfs(graph, toVertex.value, topologicalStack);
            }
        }
        fromVertex.color = Color.BLACK;
        topologicalStack.push(fromVertex);
    }

    public void relax(Vertex fromVertex, Edge edge) {
        Vertex toVertex = edge.to;
        int weight = edge.weight;
        if (toVertex.d > fromVertex.d + weight) {
            toVertex.d = fromVertex.d + weight;
            toVertex.parent = fromVertex;
        }
    }

    public void getPath(Graph graph, String dest) {
        Stack<String> stack = new Stack<>();
        Vertex vertex = graph.vertexMap.get(dest);
        stack.push(vertex.value);
        while (vertex.parent != null) {
            vertex = vertex.parent;
            stack.push(vertex.value);
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
            if (!stack.isEmpty()) {
                System.out.print(" -> ");
            }
        }
    }

    public static void main(String[] args) {
        DAGShortestPaths bellmanFord = new DAGShortestPaths();
        bellmanFord.solution(
                new String[]{"s", "t", "x", "y", "z"},
                new String[][]{
                        {"s", "t", "6"},
                        {"t", "x", "5"},
                        {"x", "t", "-2"},
                        {"s", "y", "7"},
                        {"t", "y", "8"},
                        {"y", "z", "9"},
                        {"z", "s", "2"},
                        {"y", "x", "-3"},
                        {"z", "x", "7"},
                        {"t", "z", "-4"}
                }
        );

    }
}
