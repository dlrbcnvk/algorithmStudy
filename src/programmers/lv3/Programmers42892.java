package programmers.lv3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 길찾기 게임
 */
public class Programmers42892 {

    class Node {
        int data;
        int y;
        int x;

        Node left;
        Node right;

        public Node(int data, int y, int x) {
            this.data = data;
            this.y = y;
            this.x = x;
        }
    }


    public int[][] solution(int[][] nodeinfo) {

        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(i+1, nodeinfo[i][1], nodeinfo[i][0]);
        }
        Arrays.sort(nodes, (n1, n2) -> n2.y - n1.y);

        constructTree(nodes);

        ArrayList<Integer> preOrders = pre(nodes[0], new ArrayList<>());
        ArrayList<Integer> postOrders = post(nodes[0], new ArrayList<>());

        int[] preArr = preOrders.stream()
                .mapToInt(i -> i)
                .toArray();
        int[] postArr = postOrders.stream()
                .mapToInt(i -> i)
                .toArray();

        int[][] answer = new int[2][];
        answer[0] = preArr;
        answer[1] = postArr;
        return answer;
    }

    private Node constructTree(Node[] nodes) {
        Node root = nodes[0];

        for (int i = 1; i < nodes.length; i++) {
            insert(root, nodes[i]);
        }

        return root;
    }

    private void insert(Node root, Node node) {
        if (node.x < root.x) {
            // left child
            if (root.left == null) {
                root.left = node;
            } else {
                insert(root.left, node);
            }
        } else {
            // right child
            if (root.right == null) {
                root.right = node;
            } else {
                insert(root.right, node);
            }
        }
    }

    private ArrayList<Integer> pre(Node root, ArrayList<Integer> preOrder) {

        preOrder.add(root.data);
        if (root.left != null) {
            pre(root.left, preOrder);
        }
        if (root.right != null) {
            pre(root.right, preOrder);
        }

        return preOrder;
    }

    private ArrayList<Integer> post(Node root, ArrayList<Integer> postOrder) {

        if (root.left != null) {
            post(root.left, postOrder);
        }
        if (root.right != null) {
            post(root.right, postOrder);
        }
        postOrder.add(root.data);

        return postOrder;
    }

    public static void main(String[] args) {
        Programmers42892 programmers42892 = new Programmers42892();
        int[][] solution = programmers42892.solution(
                new int[][]{{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}}
        );

        for (int[] order : solution) {
            for (int i : order) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
