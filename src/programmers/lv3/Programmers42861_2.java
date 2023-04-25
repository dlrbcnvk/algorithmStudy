package programmers.lv3;

import java.util.Arrays;

/**
 * 섬 연결하기
 * 최소신장트리 -> 크루스칼 알고리즘 -> 유니온 파인드
 * 유니온 파인드
 */
public class Programmers42861_2 {

    private static class Node {
        private int depth = 1;
        private Node parent = null;

        public boolean isConnected(Node node) {
            return root() == node.root();
        }

        public void merge(Node node) {
            if (isConnected(node)) return;

            Node root1 = root();
            Node root2 = node.root();

            if (root1.depth > root2.depth) {
                root2.parent = root1;
            } else if (root1.depth < root2.depth) {
                root1.parent = root2;
            } else {
                root2.parent = root1;
                root1.depth += 1;
            }
        }

        private Node root() {
            if (parent == null) return this;
            return parent.root();
        }
    }

    private static class Edge {
        public final int u;
        public final int v;
        public final int cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    public int solution(int n, int[][] costs) {

        Edge[] edges = Arrays.stream(costs)
                .map(edge -> new Edge(edge[0], edge[1], edge[2]))
                .sorted((e1, e2) -> e1.cost - e2.cost)
                .toArray(Edge[]::new);

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
        }

        int cost = 0;
        for (Edge e : edges) {
            int u = e.u;
            int v = e.v;
            if (!nodes[u].isConnected(nodes[v])) {
                nodes[u].merge(nodes[v]);
                cost += e.cost;
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        Programmers42861_2 programmers42861_2 = new Programmers42861_2();
        int solution = programmers42861_2.solution(
                4, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}
        );
        System.out.println(solution);
    }
}
