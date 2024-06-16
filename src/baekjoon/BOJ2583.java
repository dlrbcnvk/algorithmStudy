package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 영역 구하기
 * https://www.acmicpc.net/problem/2583
 * bfs, union-find
 * BFS는 큐에서 뺀 다음에 아닌, 큐에 넣을 때 방문 표시를 해야 중복 방문이 일어나지 않습니다.
 * [힌트 참고] https://www.acmicpc.net/board/view/39713
 */
public class BOJ2583 {

    private static int[] dy = new int[]{-1, 1, 0, 0};
    private static int[] dx = new int[]{0, 0, -1, 1};

    static class Node {
        int id;
        int y;
        int x;
        int data;
        int count;
        Node parent = null;

        public Node(int id, int y, int x, int data, int count) {
            this.id = id;
            this.y = y;
            this.x = x;
            this.data = data;
            this.count = count;
        }

        public boolean isConnected(Node node) {
            return this.root() == node.root();
        }

        public void merge(Node node) {
            if (isConnected(node)) return;

            Node root1 = this.root();
            Node root2 = node.root();

            if (root1.count > root2.count) {
                root2.parent = root1;
                root2.id = root1.id;
                root1.count += root2.count;
            } else {
                root1.parent = root2;
                root1.id = root2.id;
                root2.count += root1.count;
            }
        }

        private Node root() {
            if (this.parent == null) return this;
            return this.parent.root();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int m = Integer.parseInt(split[0]); // 세로
        int n = Integer.parseInt(split[1]); // 가로
        int k = Integer.parseInt(split[2]);
        Node[][] board = new Node[m][n];
        int id = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = new Node(id, i, j, 0, 1);
                id++;
            }
        }

        // 직사각형 색칠하기
        for (int i = 0; i < k; i++) {
            split = br.readLine().split(" ");
            int startX = Integer.parseInt(split[0]);
            int startY = Integer.parseInt(split[1]);
            int endX = Integer.parseInt(split[2]) - 1;
            int endY = Integer.parseInt(split[3]) - 1;
            for (int y = startY; y <= endY; y++) {
                for (int x = startX; x <= endX; x++) {

                    board[y][x].data = 1;
                }
            }
        }

        boolean[][] marked = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (marked[i][j]) continue;
                if (board[i][j].data == 1) continue;

                Queue<Node> queue = new LinkedList<>();
                marked[i][j] = true;
                queue.add(board[i][j]);
                while (!queue.isEmpty()) {
                    Node poll = queue.poll();
                    int y = poll.y;
                    int x = poll.x;

                    for (int t = 0; t < 4; t++) {
                        int toY = y + dy[t];
                        int toX = x + dx[t];
                        if (toY < 0 || toY >= m || toX < 0 || toX >= n) continue;
                        if (board[y][x].data != board[toY][toX].data) continue;
                        if (marked[toY][toX]) continue;

                        marked[toY][toX] = true;
                        board[y][x].merge(board[toY][toX]);
                        queue.add(board[toY][toX]);
                    }
                }
            }
        }

        Map<Integer, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j].data == 1) continue;
                if (resultMap.containsKey(board[i][j].id)) continue;

                resultMap.put(board[i][j].id, board[i][j].root().count);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(resultMap.size());
        sb.append("\n");

        int[] result = resultMap.values().stream()
                .sorted()
                .mapToInt(i -> i)
                .toArray();
        for (int i : result) {
            sb.append(i);
            sb.append(" ");
        }

        System.out.println(sb);
    }
}
