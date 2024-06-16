package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 돌 그룹
 * https://www.acmicpc.net/problem/12886
 */
public class BOJ12886 {

    static class Node {
        int a;
        int b;
        int c;

        public Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return a == node.a && b == node.b && c == node.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(arr);
        Node node = new Node(arr[0], arr[1], arr[2]);
        Set<Node> nodeSet = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        nodeSet.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int a = poll.a;
            int b = poll.b;
            int c = poll.c;

            if (a == b && a == c) {
                System.out.println(1);
                return;
            }

            if (a != b) {
                addNode(a + a, b - a, c, nodeSet, queue);
            }

            if (a != c) {
                addNode(a + a, b, c - a, nodeSet, queue);
            }

            if (b != c) {
                addNode(a, b + b, c - b, nodeSet, queue);
            }
        }

        System.out.println(0);
    }

    private static void addNode(int a, int b, int c, Set<Node> nodeSet, Queue<Node> queue) {
        int[] newArr = new int[]{a, b, c};
        Arrays.sort(newArr);
        Node next = new Node(newArr[0], newArr[1], newArr[2]);
        if (!nodeSet.contains(next)) {
            nodeSet.add(next);
            queue.add(next);
        }
    }
}
