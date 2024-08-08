package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 집합의 표현
 */
public class BOJ1717 {

    static class Node {
        int value;
        Node parent;
        int size = 1;

        public Node(int value) {
            this.value = value;
        }

        public Node root() {
            return this.parent == null ? this : this.parent.root();
        }


        public void merge(Node node) {
            if (isConnected(node)) {
                return;
            }

            Node root1 = this.root();
            Node root2 = node.root();

            if (root1.size >= root2.size) {
                root1.size += root2.size;
                root2.parent = root1;
            } else {
                root2.size += root1.size;
                root1.parent = root2;
            }
        }

        public boolean isConnected(Node node) {
            return this.root() == node.root();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = split[0];
        int m = split[1];
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int operator = split[0];
            int a = split[1];
            int b = split[2];

            if (!nodeMap.containsKey(a)) {
                nodeMap.put(a, new Node(a));
            }
            if (!nodeMap.containsKey(b)) {
                nodeMap.put(b, new Node(b));
            }
            Node aNode = nodeMap.get(a);
            Node bNode = nodeMap.get(b);

            if (operator == 0) {
                aNode.merge(bNode);
            } else if (operator == 1) {
                boolean result = aNode.isConnected(bNode);
                if (result) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }
}
