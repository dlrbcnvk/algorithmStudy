package programmers.book;

/**
 * 유니온 파인드
 * 트리의 깊이 이용
 * 트리의 깊이가 더 얕은 트리의 루트 노드를 다른 쪽 트리에 있는 루트 노드의 자식 노드로 이어줌
 * 트리의 깊이는 합친 두 트리의 깊이가 같을 때만 증가함
 * O(logN) 으로 두 원소가 같은 서로소 집합에 있는지 검사할 수 있음
 */
public class Node1 {
    private int depth = 1;
    private Node1 parent = null;

    public boolean isConnected(Node1 node) {
        return root() == node.root();
    }

    public void merge(Node1 node) {
        if (isConnected(node)) return;

        Node1 root1 = root();
        Node1 root2 = node.root();

        if (root1.depth > root2.depth) {
            root2.parent = root1;
        } else if (root1.depth < root2.depth) {
            root1.parent = root2;
        } else {
            root2.parent = root1;
            root1.depth += 1;
        }
    }

    private Node1 root() {
        if (parent == null) return this;
        return parent.root();
    }
}
