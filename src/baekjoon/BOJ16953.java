package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * A -> B
 * https://www.acmicpc.net/problem/16953
 * bfs
 */
public class BOJ16953 {

    static class Node {
        long num;
        int cnt;

        public Node(long num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return num == node.num && cnt == node.cnt;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, cnt);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int a = arr[0];
        int b = arr[1];

        int answer = -1;
        Set<Long> numSet = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        Node startNode = new Node(a, 1);
        queue.add(startNode);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            System.out.println(curNode);

            if (curNode.num == b) {
                answer = curNode.cnt;
                break;
            }

            if (numSet.contains(curNode.num)) continue;
            numSet.add(curNode.num);

            long nextNum = curNode.num * 10 + 1;
            if (!numSet.contains(nextNum) && nextNum <= b) {
                queue.add(new Node(nextNum, curNode.cnt + 1));
            }

            nextNum = curNode.num * 2;

            if (!numSet.contains(nextNum) && nextNum <= b) {
                queue.add(new Node(nextNum, curNode.cnt + 1));
            }
        }
        System.out.println(answer);
    }
}
