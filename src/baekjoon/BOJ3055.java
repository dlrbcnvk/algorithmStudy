package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 탈출
 * https://www.acmicpc.net/problem/3055
 */
public class BOJ3055 {

    private static int R;
    private static int C;
    private static String[][] board;
    private static boolean[][] marked;
    private static String D = "D";
    private static String S = "S";
    private static String WATER = "*";
    private static String STONE = "X";
    private static String EMPTY = ".";

    // 상하좌우
    private static int[] dy = new int[]{-1, 1, 0, 0};
    private static int[] dx = new int[]{0, 0, -1, 1};

    static class Node {
        int y;
        int x;
        int time;

        public Node(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);

        Node start = null;
        Node end = null;
        Queue<Node> waterQueue = new LinkedList<>();
        board = new String[R][C];
        marked = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            marked[i] = new boolean[C];
            split = br.readLine().split("");
            board[i] = split;
            for (int j = 0; j < C; j++) {
                if (board[i][j].equals(S)) {
                    start = new Node(i, j, 0);
                } else if (board[i][j].equals(D)) {
                    end = new Node(i, j, Integer.MAX_VALUE);
                } else if (board[i][j].equals(WATER)) {
                    waterQueue.add(new Node(i, j, 0));
                }
            }
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        int time = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (marked[node.y][node.x]) continue;
            if (board[node.y][node.x].equals(WATER) || board[node.y][node.x].equals(STONE)) continue;
            marked[node.y][node.x] = true;

            if (node.y == end.y && node.x == end.x) {
                System.out.println(node.time);
                return;
            }

            if (node.time == time) {
                time++;
                // 물이 번진다.
                Queue<Node> newWaterQueue = new LinkedList<>();
                while (!waterQueue.isEmpty()) {
                    Node water = waterQueue.poll();
                    for (int i = 0; i < 4; i++) {
                        int toY = water.y + dy[i];
                        int toX = water.x + dx[i];
                        if (toY < 0 || toY >= R || toX < 0 || toX >= C) continue;
                        if (board[toY][toX].equals(EMPTY) || board[toY][toX].equals(S)) {
                            board[toY][toX] = WATER;
                            newWaterQueue.add(new Node(toY, toX, time));
                        }
                    }
                }
                waterQueue = newWaterQueue;
            }

            for (int i = 0; i < 4; i++) {
                int toY = node.y + dy[i];
                int toX = node.x + dx[i];
                if (toY < 0 || toY >= R || toX < 0 || toX >= C) continue;
                if (marked[toY][toX]) continue;
                if (board[toY][toX].equals(EMPTY) || board[toY][toX].equals(D)) {
                    queue.add(new Node(toY, toX, node.time + 1));
                }
            }
        }

        System.out.println("KAKTUS");
    }
}
