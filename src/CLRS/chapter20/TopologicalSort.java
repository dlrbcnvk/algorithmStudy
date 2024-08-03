package CLRS.chapter20;

import java.util.*;

/**
 * 위상 정렬
 * 하나의 비순환 방향 그래프를 위상 정렬한다.
 * 1. 각 정점 v에 대해 종료시간 v.f 를 계산하기 위해 DFS(G) 를 호출한다. (매번 필요하지는 않을 듯)
 * 2. 각 정점이 종료될 때마다 연결 리스트의 맨 앞에 삽입한다.
 * 3. return 정점들의 연결 리스트
 * * 종료 시간의 역순
 * * 자신과 인접한 정점들이 다 종료되어서 위상정렬 리스트에 추가되었다면, 자신을 그 앞에 추가함으로써 자신 -> 인접한정점 순서로 정렬가능
 * * 위상정렬 리스트: 앞으로 넣고 앞에서 뺀다 -> Stack
 *
 * DFS
 * 재귀함수
 * 1. v.color 으로 해당 정점이 탐색되었는지, 탐색중인지, 아직 탐색되지 않았는지 확인할 수 있다.
 *     (정점 탐색상태 배열을 유지하지 않는 다른 방법)
 * 2. 어떤 정점의 탐색 시작 -> gray
 * 3. 그 정점과 인접한 정점들 중에서 아직 탐색되지 않은 정점들(white)을 대상으로 dfs()
 * 4. 그 정점과 인접한 정점들의 재귀적인 탐색이 모두 종료되었다면 그 정점 역시 종료 상태로 전환한다.(black)
 */
public class TopologicalSort {

    private int time;

    class Graph {
        private int V;
        private int E;
        private Map<Vertex, Queue<Vertex>> adj = new HashMap<>();
        private Map<String, Vertex> vertexMap = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }

        public int getV() {
            return V;
        }

        public int getE() {
            return E;
        }

        public Map<Vertex, Queue<Vertex>> getAdj() {
            return adj;
        }

        public Map<String, Vertex> getVertexMap() {
            return vertexMap;
        }

        public void addVertex(String value) {
            Vertex vertex = new Vertex(value);
            vertexMap.put(value, vertex);
            adj.put(vertex, new LinkedList<>());
        }

        public void addEdge(String from, String to) {
            Vertex fromVertex = vertexMap.get(from);
            Vertex toVertex = vertexMap.get(to);
            if (!adj.containsKey(fromVertex)) {
                return;
            }

            adj.get(fromVertex).add(toVertex);
        }
    }

    class Vertex {
        String value;
        Color statusColor;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        int startTime;
        int endTime;

        public Vertex(String value) {
            this.value = value;
            this.statusColor = Color.WHITE;
        }
    }

    public enum Color {
        WHITE, GRAY, BLACK
    }

    public Stack<Vertex> solution(String[] vertexArr, String[][] edges) {

        Graph graph = new Graph(vertexArr.length, edges.length);
        for(String vertex : vertexArr) {
            graph.addVertex(vertex);
        }
        for (String[] edge : edges) {
            String from = edge[0];
            String to = edge[1];
            graph.addEdge(from, to);
        }

        Stack<Vertex> stack = new Stack<>();
        for (String key : graph.getVertexMap().keySet()) {
            Vertex start = graph.getVertexMap().get(key);
            if (start.statusColor == Color.WHITE) {
                dfs(graph, key, stack);
            }
        }

        return stack;
    }

    public void dfs(Graph graph, String key, Stack<Vertex> topologicalStack) {
        time++;
        Vertex fromVertex = graph.vertexMap.get(key);
        fromVertex.startTime = time;
        fromVertex.statusColor = Color.GRAY;
        for (Vertex toVertex : graph.getAdj().get(fromVertex)) {
            if (toVertex.statusColor == Color.WHITE) {
                dfs(graph, toVertex.value, topologicalStack);
            }
        }
        time++;
        fromVertex.endTime = time;
        fromVertex.statusColor = Color.BLACK;
        topologicalStack.push(fromVertex);
    }

    public static void main(String[] args) {
        TopologicalSort topologicalSort = new TopologicalSort();
        Stack<Vertex> result = topologicalSort.solution(
                new String[]{
                        "jacket", "tie", "belt", "shirt", "socks", "undershorts", "pants", "shoes", "watch"
                },
                new String[][]{
                        {"undershorts", "pants"},
                        {"undershorts", "shoes"},
                        {"socks", "shoes"},
                        {"pants", "shoes"},
                        {"pants", "belt"},
                        {"shirt", "belt"},
                        {"shirt", "tie"},
                        {"tie", "jacket"},
                        {"belt", "jacket"}
                }
        );

        while (!result.isEmpty()) {
            System.out.print(result.pop().value + " ");
        }
    }
}
