package CLRS.chapter20;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 강연결 요소 (강한 컴포넌트)
 * 코사라주-샤리르 알고리즘 (Kosaraju - Sharir)
 */
public class SCC {

    private int time = 0;

    class Graph {
        int V;
        int E;
        Map<Integer, Vertex> vertexMap = new HashMap<>();
        Map<Integer, List<Vertex>> adj = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }

        public void addVertex(int v) {
            vertexMap.put(v, new Vertex(v));
            adj.put(v, new ArrayList<>());
        }

        public void addEdge(int from, int to) {
            Vertex toVertex = vertexMap.get(to);
            adj.get(from).add(toVertex);
        }

        public Graph reverse() {
            Graph reversedGraph = new Graph(V, E);
            for (Integer value : vertexMap.keySet()) {
                reversedGraph.addVertex(value);
            }
            for (Integer from : adj.keySet()) {
                List<Vertex> fromAdjList = adj.get(from);
                for (Vertex toVertex : fromAdjList) {
                    Integer reversedFrom = toVertex.value;
                    Integer reversedTo = from;
                    reversedGraph.adj.get(reversedFrom).add(reversedGraph.vertexMap.get(reversedTo));
                }
            }
            return reversedGraph;
        }
    }

    class Vertex {
        Integer value;
        Color color;
        int startTime;
        int endTime;
        int component;

        public Vertex(Integer value) {
            this.value = value;
            this.color = Color.WHITE;
        }
    }

    enum Color {
        WHITE, GRAY, BLACK
    }

    public Map<Integer, List<Integer>> solution(Integer V, int[][] edges) {
        Graph graph = new Graph(V, edges.length);
        for (int i = 0; i < V; i++) {
            graph.addVertex(i);
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.addEdge(from, to);
        }

        // 각 정점 u에 대해 종료시간 u.f를 계산하기 위해 dfs()를 호출한다.
        for (int i = 0; i < V; i++) {
            Vertex fromVertex = graph.vertexMap.get(i);
            if (fromVertex.color == Color.WHITE) {
                dfs(i, graph, 0);
            }
        }

        // G(T) 를 계산한다.
        Graph reversedGraph = graph.reverse();

        // dfs(G(T)) 를 호출한다.
        // 단, DFS의 메인 루프에서 수행한 결과 u.f의 감소 순서로 정점을 고려한다.
        Collection<Vertex> values = graph.vertexMap.values();
        List<Integer> descendingEndtimeVertexList = values.stream()
                .sorted((v1, v2) -> v2.endTime - v1.endTime)
                .map(vertex -> {
                    System.out.println("(" + vertex.value + ", " + vertex.endTime + ")");
                    return vertex.value;
                })
                .collect(Collectors.toList());
        int component = 0;
        for (Integer v : descendingEndtimeVertexList) {
            System.out.print(v + " ");
            Vertex vertex = reversedGraph.vertexMap.get(v);
            if (vertex.color == Color.WHITE) {
                dfs(vertex.value, reversedGraph, component);
                component++;
            }
        }

        Collection<Vertex> vertices = reversedGraph.vertexMap.values();
        Map<Integer, List<Vertex>> componentVertexGroup = vertices.stream()
                .collect(Collectors.groupingBy(vertex -> vertex.component));
        Map<Integer, List<Integer>> componentGroup = new HashMap<>();
        for(Integer id : componentVertexGroup.keySet()) {
            componentGroup.put(id, componentVertexGroup.get(id).stream()
                    .map(vertex -> vertex.value).collect(Collectors.toList()));
        }

        return componentGroup;
    }

    public void dfs(int v, Graph graph, int component) {
        time++;
        Vertex fromVertex = graph.vertexMap.get(v);
        fromVertex.component = component;
        fromVertex.startTime = time;
        fromVertex.color = Color.GRAY;
        for (Vertex toVertex : graph.adj.get(fromVertex.value)) {
            if (toVertex.color == Color.WHITE) {
               dfs(toVertex.value, graph, component);
            }
        }
        time++;
        fromVertex.color = Color.BLACK;
        fromVertex.endTime = time;
    }

    public static void main(String[] args) {
        SCC scc = new SCC();
        Map<Integer, List<Integer>> solution = scc.solution(
                13,
                new int[][]{
                        {0, 1},
                        {2, 0},
                        {0, 5},
                        {6, 0},
                        {7, 6},
                        {6, 7},
                        {8, 6},
                        {5, 4},
                        {3, 5},
                        {4, 3},
                        {3, 2},
                        {2, 3},
                        {4, 2},
                        {6, 4},
                        {11, 4},
                        {9, 11},
                        {6, 9},
                        {8, 9},
                        {9, 10},
                        {12, 9},
                        {11, 12},
                        {10, 12}
                }
        );

        System.out.println();
        for (Integer key : solution.keySet()) {
            System.out.print(key + " : [");
            for (Integer value : solution.get(key)) {
                System.out.print(value + " ");
            }
            System.out.print("]\n");
        }
    }
}
