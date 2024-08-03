package CLRS.chapter20;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 최소 신장 트리 - 크루스칼 알고리즘
 * Union-Find
 * 간선들을 가중치 오름차순으로 정렬한 뒤에 순회하며 정점들을 merge 및 간선의 가중치를 누적한다.
 * 최소 가중치합으로 신장트리 구성하기.
 */
public class KruskalMST {

    class Graph {
        int V;
        int E;

        Map<String, Vertex> vertexMap = new HashMap<>();

        public Graph(int v, int e) {
            V = v;
            E = e;
        }

        public void addVertex(String value) {
            Vertex vertex = new Vertex(value);
            vertexMap.put(value, vertex);
        }

    }

    class Vertex {
        String value;
        Vertex parent = null;
        int count = 1;

        public Vertex(String value) {
            this.value = value;
        }

        public boolean isConnected(Vertex vertex) {
            return this.root() == vertex.root();
        }

        private Vertex root() {
            return parent == null ? this : parent.root();
        }

        public void merge(Vertex vertex) {
            Vertex root1 = this.root();
            Vertex root2 = vertex.root();

            if (root1 == root2) {
                return;
            }

            if (root1.count >= root2.count) {
                root2.parent = root1;
                root1.count += root2.count;
            } else {
                root1.parent = root2;
                root2.count += root1.count;
            }
        }
    }


    public int solution(String[] vertices, String[][] edges) {
        Graph graph = new Graph(vertices.length, edges.length);
        for (String v : vertices) {
            graph.addVertex(v);
        }


        int sum = 0;
        List<String[]> sortedEdges = Arrays.stream(edges)
                .sorted(Comparator.comparingInt(e -> Integer.parseInt(e[2])))
                .collect(Collectors.toList());
        for (String[] edge : sortedEdges) {
            String e1 = edge[0];
            String e2 = edge[1];
            Vertex vertex1 = graph.vertexMap.get(e1);
            Vertex vertex2 = graph.vertexMap.get(e2);
            if (!vertex1.isConnected(vertex2)) {
                vertex1.merge(vertex2);
                sum += Integer.parseInt(edge[2]);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        KruskalMST kruskalMST = new KruskalMST();
        int solution = kruskalMST.solution(
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
