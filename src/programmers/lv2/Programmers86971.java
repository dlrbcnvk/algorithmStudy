package programmers.lv2;

import java.util.*;

/**
 * 전력망을 둘로 나누기
 * union-find 로 해결
 */
public class Programmers86971 {

    private static class Node {
        int data;
        Node parent = null;
        List<Node> children = new ArrayList<>();


        public Node(int data) {
            this.data = data;
        }

        public boolean isConnected(Node node) {
            return root() == node.root();
        }

        public void merge(Node node) {
            if (isConnected(node)) return;

            // root 들을 가지고 merge 해야 함
            Node root = node.root();

            this.children.add(root);
            root.parent = this;
        }

        private Node root() {
            if (parent == null) return this;
            return parent.root();
        }
    }

    public int solution(int n, int[][] wires) {

        int minDistance = Integer.MAX_VALUE;
        // 1-based index
        for (int i = 0; i < wires.length; i++) {
            Node[] nodes = new Node[n + 1];
            for (int idx = 1; idx < nodes.length; idx++) {
                nodes[idx] = new Node(idx);
            }
            Map<Integer, Integer> countMap = new HashMap<>();

            // exclude wire : wires[i]
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;

                nodes[wires[j][0]].merge(nodes[wires[j][1]]);
            }

            // counting
            for (int k = 1; k <= n; k++) {
                int rootData = nodes[k].root().data;
                if (countMap.containsKey(rootData)) {
                    Integer data = countMap.get(rootData);
                    countMap.put(rootData, data + 1);
                } else {
                    countMap.put(rootData, 1);
                }
            }

            Set<Integer> keySet = countMap.keySet();
            Integer length1 = null;
            Integer length2 = null;



            for (int key : keySet) {
                if (length1 == null) {
                    length1 = countMap.get(key);
                } else {
                    length2 = countMap.get(key);
                }
//                System.out.print(key + " ");
            }
//            System.out.println();

//            System.out.println("length1=" + length1 + ", length2=" + length2);
            minDistance = Math.min(Math.abs(length1 - length2), minDistance);
        }


        return minDistance;
    }

    public static void main(String[] args) {
        Programmers86971 programmers86971 = new Programmers86971();
        int solution = programmers86971.solution(
                9, new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}
        );
        System.out.println(solution);
    }
}
