package CLRS.part06.chapter22;

import java.util.*;

/**
 * 벨만-포드 알고리즘
 * 단일출발점 최단경로
 * 음수가중치 허용
 * 음의 가중 순환이 있다면 알고리즘 정지
 *
 * V-1 번 루프 돌면서
 *   모든 간선에 대해 relax()
 *
 * 간선 각각에 대하여
 *   s~to 최단거리가 s~from 최단거리 + from~to 가중치 보다 크다면
 *   완화작업을 V-1번 했음에도 불구하고 완화작업을 더 할 여지가 남아있다는 것
 *   -> 음의 가중 순환
 *   -> return false;
 */
public class BellmanFord {

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

    class CustomBoolean {
        Boolean value = Boolean.FALSE;

        public CustomBoolean(Boolean value) {
            this.value = value;
        }

        public void toggle() {
            if (this.value.equals(Boolean.FALSE)) {
                this.value = Boolean.TRUE;
            } else {
                this.value = Boolean.FALSE;
            }
        }
    }


    public boolean solution(String[] vertices, String[][] edges) {
        Graph graph = new Graph(vertices.length, edges.length);
        for (String value : vertices) {
            graph.addVertex(value);
        }
        for (String[] edge : edges) {
            graph.addEdge(edge[0], edge[1], Integer.parseInt(edge[2]));
        }

        // start 's'
        Vertex vertex = graph.vertexMap.get("s");
        vertex.d = 0;
        for (int i = 1; i <= graph.V; i++) {
            graph.vertexMap.keySet().stream().forEach(key -> {
                graph.adj.get(key).stream().forEach(edge -> {
                    Vertex fromVertex = graph.vertexMap.get(key);
                    relax(fromVertex, edge);
                });
            });
        }

        CustomBoolean result = new CustomBoolean(Boolean.TRUE);
        graph.vertexMap.keySet().stream().forEach(key -> {
            graph.adj.get(key).stream().forEach(edge -> {
                Vertex fromVertex = graph.vertexMap.get(key);
                Vertex toVertex = edge.to;
                int weight = edge.weight;
                if (toVertex.d > fromVertex.d + weight) {
                    result.toggle();
                }
            });
        });

        // get path
        graph.vertexMap.keySet().forEach(key -> {
            getPath(graph, key);
            Vertex destVertex = graph.vertexMap.get(key);
            System.out.print(" (" + destVertex.d + ")");
            System.out.println();
        });
        System.out.println();

        return result.value;
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
        BellmanFord bellmanFord = new BellmanFord();
        boolean solution = bellmanFord.solution(
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

        System.out.println(solution);
    }
}
