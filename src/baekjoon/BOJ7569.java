package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 토마토
 * https://www.acmicpc.net/problem/7569
 * 테스트케이스는 다 맞는데 왜 1%에서 바로 틀리지...?
 * 미해결
 */
public class BOJ7569 {

    private static int N;
    private static int M;
    private static int H;

    private static int[] dy = new int[]{-1, 1, 0, 0};
    private static int[] dx = new int[]{0, 0, -1, 1};

    static class Point {
        int y;
        int x;
        int z;
        int data;
        int day;

        public Point(int y, int x, int z, int data, int day) {
            this.y = y;
            this.x = x;
            this.z = z;
            this.data = data;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        M = Integer.parseInt(split[0]); // 가로
        N = Integer.parseInt(split[1]); // 세로
        H = Integer.parseInt(split[2]); // 높이

        Queue<Point> queue = new LinkedList<>();
        Point[][][] board = new Point[N][M][H]; // (세로, 가로, 높이)
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                split = br.readLine().split(" ");
                for (int k = 0; k < M; k++) {
                    int data = Integer.parseInt(split[k]);
                    board[j][k][i] = new Point(j, k, i, data, 0);
                    if (data == 1) {
                        queue.add(board[j][k][i]);
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int y = point.y;
            int x = point.x;
            int z = point.z;

            // 앞뒤좌우 (같은 층에서 탐색)
            for (int i = 0; i < 4; i++) {
                int toY = y + dy[i];
                int toX = x + dx[i];
                if (toY < 0 || toY >= N || toX < 0 || toX >= M) continue;
                if (board[toY][toX][z].data != 0) continue;

                board[toY][toX][z].day = board[y][x][z].day + 1;
                board[toY][toX][z].data = 1;
                queue.add(board[toY][toX][z]);
            }

            // 위아래 탐색
            int toZ = z - 1;
            if (toZ >= 0) {
                if (board[y][x][toZ].data != 0) continue;

                board[y][x][toZ].day = board[y][x][z].day + 1;
                board[y][x][toZ].data = 1;
                queue.add(board[y][x][toZ]);
            }
            toZ = z + 1;
            if (toZ < H) {
                if (board[y][x][toZ].data != 0) continue;

                board[y][x][toZ].day = board[y][x][z].day + 1;
                board[y][x][toZ].data = 1;
                queue.add(board[y][x][toZ]);
            }
        }

        int resultDay = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < H; k++) {
                    // 안 익은 토마토 발견
                    if (board[i][j][k].data == 0) {
                        System.out.println(-1);
                        return;
                    }
                    if (board[i][j][k].data == -1) continue;

                    resultDay = Math.max(board[i][j][k].day, resultDay);
                }
            }
        }

        System.out.println(resultDay);
    }
}
