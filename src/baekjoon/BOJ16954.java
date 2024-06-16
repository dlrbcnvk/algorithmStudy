package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 움직이는 미로 탈출
 * https://www.acmicpc.net/problem/16954
 * 미해결 - 스택오버플로우
 */
public class BOJ16954 {

    private static String EMPTY = ".";
    private static String WALL = "#";
    private static String[][] board;
    private static String result = "0";

    private static List<Point> wallList = new ArrayList<>();

    // 상, 하, 좌, 우, 좌상, 우상, 좌하, 우하, 가만히
    private static int[] dy = new int[]{-1, 1, 0, 0, -1, -1, 1, 1, 0};
    private static int[] dx = new int[]{0, 0, -1, 1, -1, 1, -1, 1, 0};

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new String[8][8];
        for (int i = 0; i < 8; i++) {
            String[] split = br.readLine().split("");
            board[i] = split;
            for (int j = 0; j < 8; j++) {
                board[i][j] = split[j];
                if (board[i][j].equals(WALL)) {
                    wallList.add(new Point(i, j));
                }
            }
        }

        go(7, 0);
        System.out.println(result);
    }

    private static void go(int y, int x) {
        // 종료조건
        if (y == 0 && x == 7) {
            result = "1";
            return;
        }

        // 캐릭터가 먼저 이동하고나서 벽이 이동함. 그렇기때문에
        // 캐릭터가 갈 곳에 벽이 있으면 안 됨.
        // 그리고 캐릭터가 간 다음에 벽이 바로 위에서 캐릭터의 위치로 떨어져서도 안 된다.
        for (int i = 0; i < dy.length; i++) {
            int toY = dy[i];
            int toX = dx[i];
            if (toY < 0 || toY > 7 || toX < 0 || toX > 7) continue;
            if (board[toY][toX].equals(WALL)) continue;
            if (toY > 0 && board[toY - 1][toX].equals(WALL)) continue;

            for (int j = 0; j < wallList.size(); j++) {
                Point wall = wallList.get(j);
                if (wall.y != 7) {
                    board[wall.y][wall.x] = EMPTY;
                    board[wall.y + 1][wall.x] = WALL;
                } else {
                    board[wall.y][wall.x] = EMPTY;
                }
            }
            go(toY, toX);
            for (int j = 0; j < wallList.size(); j++) {
                Point wall = wallList.get(j);
                if (wall.y != 0) {
                    board[wall.y][wall.x] = EMPTY;
                    board[wall.y - 1][wall.x] = WALL;
                } else {
                    board[wall.y][wall.x] = EMPTY;
                }
            }
        }

    }
}
