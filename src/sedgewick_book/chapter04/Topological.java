package sedgewick_book.chapter04;

import java.util.Stack;

/**
 * 위상 정렬 = 반전된 후행 순서 (in DAG)
 */
public class Topological {
    private Iterable<Integer> order;        // 위상 정렬 순서

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePostOrder();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(13);
        graph.addEdge(2, 3);
        graph.addEdge(0, 1);
        graph.addEdge(2, 0);
        graph.addEdge(3, 5);
        graph.addEdge(5, 4);
        graph.addEdge(0, 5);
        graph.addEdge(6, 4);
        graph.addEdge(8, 7);
        graph.addEdge(7, 6);
        graph.addEdge(6, 9);
        graph.addEdge(9, 11);
        graph.addEdge(9, 10);
        graph.addEdge(11, 12);
        graph.addEdge(9, 12);
        graph.addEdge(0, 6);

        Topological topological = new Topological(graph);
        StringBuilder sb = new StringBuilder();
        if (topological.isDAG()) {
            Stack<Integer> order = (Stack) topological.order();
            while (!order.isEmpty()) {
                Integer pop = order.pop();
                sb.append(pop).append(" ");
            }
        }
        System.out.println(sb);
    }
}
