package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 촌수계산
 * https://www.acmicpc.net/problem/2644
 * 그래프, BFS
 */
public class BOJ2644 {

    private static int n;
    private static int from;
    private static int to;
    private static int m;

    static class Node {
        public int id; // 사람 고유번호
        public int cnt = 0; // from 으로부터의 촌수
        public Node parent; // 부모
        public List<Node> children = new ArrayList<>(); // 자식들

        public Node(int id, Node parent) {
            this.id = id;
            this.parent = parent;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new Node(i, null);
        }
        String[] split = br.readLine().split(" ");
        from = Integer.parseInt(split[0]);
        to = Integer.parseInt(split[1]);

        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int parent = Integer.parseInt(split[0]);
            int child = Integer.parseInt(split[1]);
            nodes[parent].children.add(nodes[child]);
            nodes[child].parent = nodes[parent];
        }

        int answer = -1;
        boolean[] marked = new boolean[n + 1];
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes[from]);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (marked[curNode.id]) continue;
            marked[curNode.id] = true;

            if (curNode.id == to) {
                answer = curNode.cnt;
                break;
            }

            if (curNode.parent != null && !marked[curNode.parent.id]) {
                curNode.parent.cnt = curNode.cnt + 1;
                queue.add(curNode.parent);
            }

            for (Node child : curNode.children) {
                if (!marked[child.id]) {
                    child.cnt = curNode.cnt + 1;
                    queue.add(child);
                }
            }
        }

        System.out.println(answer);
    }
}
