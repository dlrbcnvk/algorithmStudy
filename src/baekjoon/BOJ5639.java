package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 이진 검색 트리
 */
public class BOJ5639 {

    static class Node {
        int value;
        Node parent;
        Node leftChild;
        Node rightChild;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = null;
        while (true) {
            String str = br.readLine();
            if (str == null || str.equals("")) {
                break;
            }
            int value = Integer.valueOf(str);
            if (root == null) {
                root = new Node(value);
            } else {
                setNode(root, value);
            }
        }

        postOrder(root);
    }

    private static void postOrder(Node node) {
        if (node.leftChild != null) {
            postOrder(node.leftChild);
        }
        if (node.rightChild != null) {
            postOrder(node.rightChild);
        }
        System.out.println(node.value);
    }

    private static void setNode(Node node, Integer value) {
        if (value < node.value) {
            if (node.leftChild != null) {
                setNode(node.leftChild, value);
            } else {
                node.leftChild = new Node(value);
                node.leftChild.parent = node;
            }
        } else if (value > node.value) {
            if (node.rightChild != null) {
                setNode(node.rightChild, value);
            } else {
                node.rightChild = new Node(value);
                node.rightChild.parent = node;
            }
        }
    }
}
