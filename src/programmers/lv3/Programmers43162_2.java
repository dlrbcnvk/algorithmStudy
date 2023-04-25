package programmers.lv3;

import java.util.HashSet;
import java.util.Set;

/**
 * 네트워크
 * 유니온 파인드로 가능
 */
public class Programmers43162_2 {

    private static class Node {
        private int data;
        private int depth = 1;
        private Node parent = null;

        public Node(int data) {
            this.data = data;
        }

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

    public int solution(int n, int[][] computers) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers.length; j++) {
                if (i == j) continue;

                if (computers[i][j] == 1) {
                    nodes[i].merge(nodes[j]);
                }
            }
        }

        Set<Integer> nodeDataSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            nodeDataSet.add(nodes[i].root().data);
        }
        return nodeDataSet.size();
    }

    public static void main(String[] args) {
        Programmers43162_2 programmers43162_2 = new Programmers43162_2();
        int solution = programmers43162_2.solution(
                3, new int[][]{{1,1,0},{1,1,0},{0,0,1}}
        );
        System.out.println(solution);
    }
}
