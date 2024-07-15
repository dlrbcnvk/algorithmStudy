package programmers.lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 길 찾기 게임
 * 미해결
 */
public class Programmers42892_2 {

    class Node {
        private int y;
        private int x;
        private int number;
        private Node parentNode;
        private Node leftNode;
        private Node rightNode;
        private int leftBound = Integer.MIN_VALUE;
        private int rightBound = Integer.MAX_VALUE;

        public Node(int y, int x, int number) {
            this.y = y;
            this.x = x;
            this.number = number;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public Node getParentNode() {
            return parentNode;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public int getNumber() {
            return number;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public int getLeftBound() {
            return leftBound;
        }

        public int getRightBound() {
            return rightBound;
        }

        public void setLeftBound(int leftBound) {
            this.leftBound = leftBound;
        }

        public void setRightBound(int rightBound) {
            this.rightBound = rightBound;
        }
    }

    public int[][] solution(int[][] nodeinfo) {

        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            int[] info = nodeinfo[i];
            nodes[i] = new Node(info[1], info[0], i + 1);
        }
        Arrays.sort(nodes, (n1, n2) -> {
            if (n1.getY() != n2.getY()) {
                return n2.getY() - n1.getY();
            }
            return n1.getX() - n2.getX();
        });

        for (int i = 0; i < nodes.length; i++) {
            Node node = nodes[i];
            Node leftChild = findLeftChild(nodes, i);
            if (leftChild != null) {
                node.setLeftNode(leftChild);
                leftChild.setParentNode(node);
                leftChild.setRightBound(node.getX());
            }
            Node rightChild = findRightChild(nodes, i);
            if (rightChild != null) {
                node.setRightNode(rightChild);
                rightChild.setParentNode(node);
                rightChild.setLeftBound(node.getX());
            }
        }

        List<Integer> preorderRecord = new ArrayList<>();
        preorder(nodes[0], preorderRecord);
        List<Integer> postorderRecord = new ArrayList<>();
        postorder(nodes[0], postorderRecord);

        int[] preorderArray = preorderRecord.stream().mapToInt(i -> i).toArray();
        int[] postorderArray = postorderRecord.stream().mapToInt(i -> i).toArray();

        int[][] answer = new int[2][nodes.length];
        answer[0] = preorderArray;
        answer[1] = postorderArray;
        return answer;
    }

    private Node findLeftChild(Node[] nodes, int idx) {
        Node node = nodes[idx];
        Node parent = node.getParentNode();
        boolean amILeftChild = false;
        boolean amIRightChild = false;
        if (parent != null && parent.getLeftNode() == node) {
            amILeftChild = true;
        }
        if (parent != null && parent.getRightNode() == node) {
            amIRightChild = true;
        }
        idx++;
        while (idx < nodes.length) {
            Node child = nodes[idx];
            if (parent != null) {
                if (amILeftChild) {
                    if (child.getY() < node.getY() && child.getX() < node.getX()) {
                        return child;
                    }
                } else if (amIRightChild) {
                    if (child.getY() < node.getY() && child.getX() < node.getX() && child.getX() > parent.getX()) {
                        return child;
                    }
                }
            } else {
                if (child.getY() < node.getY() && child.getX() < node.getX()) {
                    return child;
                }
            }
            idx++;
        }
        return null;
    }

    private Node findRightChild(Node[] nodes, int idx) {
        Node node = nodes[idx];
        Node parent = node.getParentNode();
        boolean amILeftChild = false;
        boolean amIRightChild = false;
        if (parent != null && parent.getLeftNode() == node) {
            amILeftChild = true;
        }
        if (parent != null && parent.getRightNode() == node) {
            amIRightChild = true;
        }
        idx++;
        while (idx < nodes.length) {
            Node child = nodes[idx];
            if (parent == null) {
                if (child.getY() < node.getY() && child.getX() > node.getX()) {
                    return child;
                }
            } else {
                if (amILeftChild) {
                    if (child.getY() < node.getY() && child.getX() > node.getX() && child.getX() < parent.getX()) {
                        return child;
                    }
                } else if (amIRightChild) {
                    if (child.getY() < node.getY() && child.getX() > node.getX()) {
                        return child;
                    }
                }
            }
            idx++;
        }
        return null;
    }

    private void preorder(Node node, List<Integer> record) {
        record.add(node.getNumber());
        if (node.getLeftNode() != null) {
            preorder(node.getLeftNode(), record);
        }
        if (node.getRightNode() != null) {
            preorder(node.getRightNode(), record);
        }
    }

    private void postorder(Node node, List<Integer> record) {
        if (node.getLeftNode() != null) {
            preorder(node.getLeftNode(), record);
        }
        if (node.getRightNode() != null) {
            preorder(node.getRightNode(), record);
        }
        record.add(node.getNumber());
    }

    public static void main(String[] args) {
        Programmers42892_2 programmers428922 = new Programmers42892_2();
        int[][] solution = programmers428922.solution(new int[][]{
                {5,3},
                {11,5},
                {13,3},
                {3,5},
                {6,1},
                {1,3},
                {8,6},
                {7,2},
                {2,2}
        });

        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[0].length; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }
}
