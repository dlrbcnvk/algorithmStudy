package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 녹색 옷 입은 애가 젤다지?
 */
public class BOJ4485 {

    private static int[] dy = new int[]{-1, 1, 0, 0};
    private static int[] dx = new int[]{0, 0, -1, 1};

    static class Node implements Comparable<Node> {
        int y;
        int x;
        int cost;

        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int count = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            int[][] board = new int[n][n];
            for (int i = 0; i < n; i++) {
                board[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            boolean[][] marked = new boolean[n][n];
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                marked[i] = new boolean[n];
                dist[i] = new int[n];
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            dist[0][0] = board[0][0];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(0, 0, board[0][0]));
            while (!pq.isEmpty()) {
                Node curNode = pq.poll();
                int y = curNode.y;
                int x = curNode.x;

                if (marked[y][x]) continue;
                marked[y][x] = true;

                for (int i = 0; i < 4; i++) {
                    int toY = y + dy[i];
                    int toX = x + dx[i];
                    if (toY < 0 || toY >= n || toX < 0 || toX >= n) continue;

                    if (dist[toY][toX] > dist[y][x] + board[toY][toX]) {
                        dist[toY][toX] = dist[y][x] + board[toY][toX];
                        pq.add(new Node(toY, toX, dist[toY][toX]));
                    }
                }
            }

            sb.append("Problem ");
            sb.append(count);
            sb.append(": ");
            sb.append(dist[n-1][n-1]);
            sb.append("\n");
            count += 1;

        }


        System.out.println(sb);
    }
}
