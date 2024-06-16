package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 카드 구매하기
 * https://www.acmicpc.net/problem/11052
 * bfs
 */
public class BOJ11052 {

    private static int n;
    private static int[] cards;

    static class Node {
        int cnt; // 카드의 개수
        int price; // 가격

        public Node(int cnt, int price) {
            this.cnt = cnt;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return cnt == node.cnt && price == node.price;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cnt, price);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "cnt=" + cnt +
                    ", price=" + price +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cards = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] prices = new int[n + 1];
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < cards.length; i++) {
            Node node = new Node(i + 1, cards[i]);
            prices[i + 1] = cards[i];
            queue.add(node);
        }


        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

//            System.out.println(curNode);

            if (curNode.cnt == n) {
                prices[n] = Math.max(prices[n], curNode.price);
            }

            if (curNode.price < prices[curNode.cnt]) continue;

            for (int i = 0; i < cards.length; i++) {
                int nextCnt = curNode.cnt + i + 1;
                int nextPrice = curNode.price + cards[i];

                if (nextCnt > n) continue;
                if (nextPrice <= prices[nextCnt]) continue;

                prices[nextCnt] = nextPrice;
                queue.add(new Node(nextCnt, nextPrice));
            }
        }

        System.out.println(prices[n]);
    }
}
