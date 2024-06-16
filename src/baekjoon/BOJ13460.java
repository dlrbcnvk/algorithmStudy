package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 구슬 탈출 2
 * https://www.acmicpc.net/problem/13460
 * 미해결
 */
public class BOJ13460 {

    private static int N;
    private static int M;
    private static String[][] board;
    private static String WALL = "#";
    private static String DOT = ".";
    private static String R = "R";
    private static String B = "B";
    private static String HOLE = "0";

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Node {
        Point red;
        Point blue;

        public Node(Point red, Point blue) {
            this.red = red;
            this.blue = blue;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        Point startRed = null;
        Point startBlue = null;
        for (int i = 0; i < N; i++) {
            split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                board[i][j] = split[j];
                if (board[i][j].equals(R)) {
                    startRed = new Point(i, j);
                } else if (board[i][j].equals(B)) {
                    startBlue = new Point(i, j);
                }
            }
        }

        Node node = new Node(startRed, startBlue);
        Queue<Node> queue = new LinkedList<>();

    }
}
