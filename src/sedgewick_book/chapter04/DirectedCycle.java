package sedgewick_book.chapter04;

import java.util.Stack;

/**
 * 방향 순환 찾기
 * 모든 순환을 찾는 것이 아니라 그 중 하나만 찾는, 존재 여부의 확인
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle; // 순환을 이루는 정점들(만약 존재한다면)
    private boolean[] onStack; // 재귀 호출 스택의 정점들

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle()) {
                return; // onStack에 true를 남긴 채 return
            } else if (!marked[w]) {
                edgeTo[w] = v; // v -> w
                dfs(G, w);
            } else if (onStack[w]) {
                // 이미 한번 다녀간 곳인데 marked[w] == true,
                // onStack이 true다. 아직 호출스택에 남아있는 곳에 왔다 -> 순환 발견. w가 순환 발견점
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }


    public static void main(String[] args) {
        Digraph graph = new Digraph(13);
        graph.addEdge(4, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 2);
        graph.addEdge(6, 0);
        graph.addEdge(0, 1);
        graph.addEdge(2, 0);
        graph.addEdge(11, 12);
        graph.addEdge(12, 9);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(8, 9);
        graph.addEdge(10, 12);
        graph.addEdge(11, 4);
        graph.addEdge(4, 3);
        graph.addEdge(3, 5);
        graph.addEdge(7, 8);
        graph.addEdge(8, 7);
        graph.addEdge(5, 4);
        graph.addEdge(0, 5);
        graph.addEdge(6, 4);
        graph.addEdge(6, 9);
        graph.addEdge(7, 6);

        DirectedCycle directedCycle = new DirectedCycle(graph);
        Stack<Integer> cycle = null;
        if (directedCycle.hasCycle()) {
            cycle = (Stack<Integer>) directedCycle.cycle;
            while (!cycle.isEmpty()) {
                Integer pop = cycle.pop();
                System.out.print(pop + " ");
            }
        }


    }
}
