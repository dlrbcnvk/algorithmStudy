package programmers.lv3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 섬 연결하기
 */
public class Programmers42861_3 {

    class Node {
        int number;
        Node parent;
        Node child;
        int childCount = 1;

        public Node(int number) {
            this.number = number;
        }

        public boolean isConnected(Node node) {
            return this.root() == node.root();
        }

        public Node root() {
            return parent == null ? this : parent.root();
        }

        public void merge(Node node) {
            if (this.isConnected(node)) {
                return;
            }
            Node root1 = this.root();
            Node root2 = node.root();
            if (root1.childCount >= root2.childCount) {
                root1.child = root2;
                root2.parent = root1;
                root1.childCount += root2.childCount;
            } else {
                root2.child = root1;
                root1.parent = root2;
                root2.childCount += root1.childCount;
            }
        }

        public int getNumber() {
            return number;
        }

        public Node getParent() {
            return parent;
        }

        public Node getChild() {
            return child;
        }

        public int getChildCount() {
            return childCount;
        }
    }

    public int solution(int n, int[][] costs) {

        Node[] nodes = new Node[n];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }

        Arrays.sort(costs, Comparator.comparingInt(c -> c[2]));
        int totalCost = 0;
        for (int[] ints : costs) {
            int c0 = ints[0];
            int c1 = ints[1];
            int cost = ints[2];
            Node node1 = nodes[c0];
            Node node2 = nodes[c1];
            if (node1.isConnected(node2)) {
                continue;
            }

            node1.merge(node2);
            totalCost += cost;
            if (node1.getChildCount() == n || node2.getChildCount() == n) {
                break;
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        Programmers42861_3 programmers428613 = new Programmers42861_3();
        int solution = programmers428613.solution(5,
                new int[][]{
                        {0,1,5},
                        {1,2,3},
                        {2,3,3},
                        {3,1,2},
                        {3,0,4},

                });
        System.out.println(solution);
    }
}
