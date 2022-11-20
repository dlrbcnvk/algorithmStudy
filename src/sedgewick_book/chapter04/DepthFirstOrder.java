package sedgewick_book.chapter04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 방향 그래프에서 정점 순서를 만들기 위한 깊이 우선 탐색
 * 선행 순서: dfs() 호출 순서
 * 후행 순서: 정점에 대한 작업이 완료된 순서
 * DAG에서 반전된 후행 순서는 위상 정렬 순서이다
 */
public class DepthFirstOrder {
    private boolean[] marked;

    private Queue<Integer> pre;             // 선행 순서 정점 목록
    private Queue<Integer> post;            // 후행 순서 정점 목록
    private Stack<Integer> reversePost;     // 반전된 후행 순서 정점 목록

    public DepthFirstOrder(Digraph G) {
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        pre.add(v);
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.add(v);
        reversePost.push(v);
    }

    public Iterable<Integer> preOrder() {
        return pre;
    }
    public Iterable<Integer> postOrder() {
        return post;
    }
    public Iterable<Integer> reversePostOrder() {
        return reversePost;
    }
}
