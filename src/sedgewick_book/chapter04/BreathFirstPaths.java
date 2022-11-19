package sedgewick_book.chapter04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 너비 우선 탐색
 * queue 이용
 */
public class BreathFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s; // 원점


    public BreathFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.s = s;
        bfs(graph, s);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v; // v -> w
                    marked[w] = true; // 다른 조건 없으므로 먼저 찍는 자가 임자. 일종의 선착순
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {

        Graph graph = new Graph(6);
        graph.addEdge(0, 2);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(0, 1);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(0, 2);

        BreathFirstPaths breathFirstPaths = new BreathFirstPaths(graph, 0);

        System.out.println("bfs가 지나간 경로");
        int start = 0;
        for (int i = 0; i < graph.V(); i++) {
            if (i == start)
                continue;

            Stack<Integer> stack = breathFirstPaths.pathTo(i);
            while (!stack.isEmpty()) {
                Integer popped = stack.pop();
                System.out.print(popped + " ");
            }
            System.out.println();
        }

    }
}
