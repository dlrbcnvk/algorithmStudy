package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 여행 가자
 */
public class BOJ1976 {

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
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                int a = i;
                int b = j + 1;
                boolean connected = split[j] == 1;
                if (!nodeMap.containsKey(a)) {
                    nodeMap.put(a, new Node(a));
                }
                if (!nodeMap.containsKey(b)) {
                    nodeMap.put(b, new Node(b));
                }
                Node aNode = nodeMap.get(a);
                Node bNode = nodeMap.get(b);

                if (connected) {
                    aNode.merge(bNode);
                }
            }
        }

        int[] plans = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Node root = null;
        for (int plan : plans) {
            if (!nodeMap.containsKey(plan)) {
                System.out.println("NO");
                return;
            }
            Node planRoot = nodeMap.get(plan).root();
            if (root == null) {
                root = planRoot;
            } else {
                if (root != planRoot) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }
}
