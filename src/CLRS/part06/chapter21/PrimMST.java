package CLRS.part06.chapter21;

import java.util.*;

/**
 * 최소 신장 트리 - 프림 알고리즘
 * 정점을 탐색하는 과정에서 인접한 간선들을 우선순위큐에 담는다.
 * 우선순위큐에서 간선을 꺼내는데, 두 정점 모두 이미 탐색된 정점이라면 무시한다.
 */
public class PrimMST {

    class Graph {
        Map<String, Vertex> vertexMap = new HashMap<>();
        Map<String, List<Edge>> adj = new HashMap<>();

        public void addVertex(String value) {
            vertexMap.put(value, new Vertex(value));
            adj.put(value, new ArrayList<>());
        }

        public void addEdge(String v1, String v2, String weight) {
            Edge edge = new Edge(v1, v2, Integer.parseInt(weight));
            adj.get(v1).add(edge);
            adj.get(v2).add(edge);
        }
    }

    class Vertex {
        String value;
        boolean marked = false;

        public Vertex(String value) {
            this.value = value;
        }
    }

    class Edge implements Comparable<Edge> {
        String v1;
        String v2;
        int weight;

        public Edge(String v1, String v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }

    public int solution(String[] vertices, String[][] edges) {
        Graph graph = new Graph();
        for (String v : vertices) {
            graph.addVertex(v);
        }
        for (String[] edge : edges) {
            graph.addEdge(edge[0], edge[1], edge[2]);
        }

        int sum = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Vertex currentVertex = graph.vertexMap.get("a");
        currentVertex.marked = true;
        pq.addAll(graph.adj.get(currentVertex.value));
        while (!pq.isEmpty()) {
            Edge poll = pq.poll();
            String v1 = poll.v1;
            String v2 = poll.v2;
            Vertex vertex1 = graph.vertexMap.get(v1);
            Vertex vertex2 = graph.vertexMap.get(v2);

            if (vertex1.marked && vertex2.marked) {
                continue;
            }

            // 간선 (가중치) 추가
            sum += poll.weight;

            // 처음 마주친 정점을 우선순위큐에 추가한다.
            if (!vertex1.marked) {
                vertex1.marked = true;
                pq.addAll(graph.adj.get(vertex1.value));
            }
            if (!vertex2.marked) {
                vertex2.marked = true;
                pq.addAll(graph.adj.get(vertex2.value));
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        PrimMST primMST = new PrimMST();
        int solution = primMST.solution(
                new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"},
                new String[][]{
                        {"a", "b", "4"},
                        {"a", "h", "8"},
                        {"b", "h", "11"},
                        {"b", "c", "8"},
                        {"h", "i", "7"},
                        {"h", "g", "1"},
                        {"i", "g", "6"},
                        {"i", "c", "2"},
                        {"d", "c", "7"},
                        {"c", "f", "4"},
                        {"g", "f", "2"},
                        {"e", "f", "10"},
                        {"e", "d", "9"},
                        {"d", "f", "14"}
                }
        );
        System.out.println(solution);
    }
}
