package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 벽 부수고 이동하기
 * https://www.acmicpc.net/problem/2206
 * 미해결
 */
public class BOJ2206 {

    private static int N;
    private static int M;
    private static int[][] board;
    private static boolean[][] marked;

    private static int[] dy = new int[]{-1, 1, 0, 0};
    private static int[] dx = new int[]{0, 0, -1, 1};

    static class Point {
        int y;
        int x;
        boolean brokeWall;
        int walkingCount;

        public Point(int y, int x, boolean brokeWall, int walkingCount) {
            this.y = y;
            this.x = x;
            this.brokeWall = brokeWall;
            this.walkingCount = walkingCount;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        board = new int[N][M];
        marked = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(split[j]);
                if (value == 1) {
                    board[i][j] = Integer.MAX_VALUE;
                } else {
                    board[i][j] = value;
                }
            }
            marked[i] = new boolean[M];
        }

        // 벽을 안 부수고 해보기
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, false, 1));
        marked[0][0] = true;
        int noBreakingWallResult = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int y = poll.y;
            int x = poll.x;

            if (y == N - 1 && x == M - 1) {
                noBreakingWallResult = poll.walkingCount;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int toY = y + dy[i];
                int toX = x + dx[i];
                if (toY < 0 || toY >= N || toX < 0 || toX >= M) continue;
                if (board[toY][toX] == Integer.MAX_VALUE) continue;
                if (marked[toY][toX]) continue;

                marked[toY][toX] = true;
                queue.add(new Point(toY, toX, false, poll.walkingCount + 1));
            }
        }

        int breakingWallResult = Integer.MAX_VALUE;
        queue.clear();
        queue.add(new Point(0, 0, false, 1));
        for (int i = 0; i < N; i++) {
            marked[i] = new boolean[M];
        }
        marked[0][0] = true;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int y = poll.y;
            int x = poll.x;

            if (y == N - 1 && x == M - 1) {
                if (poll.brokeWall) {
                    breakingWallResult = poll.walkingCount;
                }
                break;
            }

            for (int i = 0; i < 4; i++) {
                int toY = y + dy[i];
                int toX = x + dx[i];
                if (toY < 0 || toY >= N || toX < 0 || toX >= M) continue;
                if (poll.brokeWall && board[toY][toX] == Integer.MAX_VALUE) continue;

                if (board[toY][toX] == 0) {
                    marked[toY][toX] = true;
                    if (poll.brokeWall) {
                        queue.add(new Point(toY, toX, true, poll.walkingCount + 1));
                    } else {
                        queue.add(new Point(toY, toX, false, poll.walkingCount + 1));
                    }
                    board[toY][toX] = poll.walkingCount + 1;
                } else if (board[toY][toX] == Integer.MAX_VALUE && !poll.brokeWall) {
                    // 벽을 부순다.
                    marked[toY][toX] = true;
                    queue.add(new Point(toY, toX, true, poll.walkingCount + 1));
                }
            }
        }

        if (noBreakingWallResult == Integer.MAX_VALUE && breakingWallResult == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            int result = Math.min(noBreakingWallResult, breakingWallResult);
            System.out.println(result);
        }

    }
}
