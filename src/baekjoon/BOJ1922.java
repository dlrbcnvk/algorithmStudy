package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 네트워크 연결
 */
public class BOJ1922 {

    static class Node {
        int id;
        Node parent;
        int count = 1;

        public Node(int id) {
            this.id = id;
        }
        public boolean isConnected(Node node) {
            return this.root() == node.root();
        }

        private Node root() {
            return parent == null ? this : parent.root();
        }

        public void merge(Node node) {
            if (isConnected(node)) {
                return;
            }

            Node root1 = this.root();
            Node root2 = node.root();

            if (root1.count >= root2.count) {
                root1.count += root2.count;
                root2.parent = root1;
            } else {
                root2.count += root1.count;
                root1.parent = root2;
            }
        }
    }

    static class Edge {
        int v;
        int w;
        int cost;

        public Edge(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }

        public int other(int value) {
            if (value == v) {
                return w;
            } else if (value == w) {
                return v;
            } else {
                throw new IllegalArgumentException("zzz");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.cost));
        for (int i = 0; i < M; i++) {
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = split[0];
            int b = split[1];
            if (a == b) {
                continue;
            }
            int cost = split[2];
            Edge edge = new Edge(a, b, cost);
            pq.add(edge);
        }
        int totalCost = 0;
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (nodes[edge.v].isConnected(nodes[edge.w])) {
                continue;
            }
            totalCost += edge.cost;
            nodes[edge.v].merge(nodes[edge.w]);
        }
        System.out.println(totalCost);
    }
}
