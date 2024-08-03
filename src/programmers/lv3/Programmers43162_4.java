package programmers.lv3;

/**
 * 네트워크
 * Union-Find 풀이
 */
public class Programmers43162_4 {

    class Node {
        int id;
        Node parent;
        int childCount;

        public Node(int id) {
            this.id = id;
        }
        public boolean isConnected(Node target) {
            return this.root() == target.root();
        }

        private Node root() {
            return this.getParent() == null ? this : this.parent.root();
        }

        public void merge(Node target) {
            if (isConnected(target)) {
                return;
            }
            Node root1 = this.root();
            Node root2 = target.root();
            if (root1.getChildCount() >= root2.getChildCount()) {
                root1.childCount += root2.getChildCount();
                root2.setParent(root1);
            } else {
                root2.childCount += root1.getChildCount();
                root1.setParent(root2);
            }
        }

        public int getId() {
            return id;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public int getChildCount() {
            return childCount;
        }

        public void setChildCount(int childCount) {
            this.childCount = childCount;
        }
    }

    public int solution(int n, int[][] computers) {

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (computers[i][j] == 0) {
                    continue;
                }
                nodes[i].merge(nodes[j]);
            }
        }

        int networkCount = 0;
        for (Node node : nodes) {
            if (node.getParent() == null) {
                networkCount++;
            }
        }
        return networkCount;
    }

    public static void main(String[] args) {
        Programmers43162_4 programmers431624 = new Programmers43162_4();
        int solution = programmers431624.solution(
                3, new int[][]{
                        {1,1,0},
                        {1,1,0},
                        {0,0,1}
                }
        );
        System.out.println(solution);
    }
}
