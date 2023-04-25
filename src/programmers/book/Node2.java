package programmers.book;

/**
 * 유니온 파인드
 * 루트 노드를 구할 때 부모 노드를 루트 노드로 업데이트
 * 루트 노드를 제외한 모든 노드의 부모 노드가 바로 루트 노드가 됨
 * O(1) 만에 같은 집합에 속하는지 검사할 수 있음
 */
public class Node2 {
    private Node2 parent = null;

    public boolean isConnected(Node2 node) {
        return root() == node.root();
    }

    public void merge(Node2 node) {
        if (isConnected(node)) return;
        node.parent = this;
    }

    private Node2 root() {
        if (parent == null) return this;
        return parent = parent.root();
    }
}
