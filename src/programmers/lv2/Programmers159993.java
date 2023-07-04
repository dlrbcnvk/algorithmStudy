package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 미로 탈출
 * https://school.programmers.co.kr/learn/courses/30/lessons/159993
 * bfs 2회 하기
 */
public class Programmers159993 {

    private static final int[] dy = new int[]{-1, 1, 0, 0};
    private static final int[] dx = new int[]{0, 0, -1, 1};

    private String[][] board;
    private boolean[][] marked;
    private int n, m;

    class Point {
        int y;
        int x;
        int sec = 0;

        public Point(int y, int x, int sec) {
            this.y = y;
            this.x = x;
            this.sec = sec;
        }
    }

    public int solution(String[] maps) {

        n = maps.length;
        m = maps[0].length();

        Point start = null;
        Point lever = null;

        board = new String[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = maps[i].split("");
            for (int j = 0; j < m; j++) {
                if (board[i][j].equals("S")) {
                    start = new Point(i, j, 0);
                } else if (board[i][j].equals("L")) {
                    lever = new Point(i, j, 0);
                }
            }
        }

        // start -> lever
        int minSecToLever = getMinSec(start, "L");

        // lever 로 갈 길이 없으면
        if (minSecToLever == Integer.MAX_VALUE) {
            return -1;
        }

        // lever -> end
        int minSecToEnd = getMinSec(lever, "E");

        // end 로 갈 길이 없으면
        if (minSecToEnd == Integer.MAX_VALUE) {
            return -1;
        }

        return minSecToLever + minSecToEnd;
    }

    private int getMinSec(Point start, String target) {
        marked = new boolean[n][m];

        // lever -> end
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        marked[start.y][start.x] = true;

        int minSecToEnd = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int curY = poll.y;
            int curX = poll.x;
            int curSec = poll.sec;

            if (board[curY][curX].equals(target)) {
                minSecToEnd = Math.min(minSecToEnd, curSec);
                break;
            }

            int toY = curY + dy[0];
            int toX = curX + dx[0];
            if (toY >= 0) { checkConditionAndAddQueue(toY, toX, queue, curSec); }

            toY = curY + dy[1];
            toX = curX + dx[1];
            if (toY < n) { checkConditionAndAddQueue(toY, toX, queue, curSec); }

            toY = curY + dy[2];
            toX = curX + dx[2];
            if (toX >= 0) { checkConditionAndAddQueue(toY, toX, queue, curSec); }

            toY = curY + dy[3];
            toX = curX + dx[3];
            if (toX < m) { checkConditionAndAddQueue(toY, toX, queue, curSec); }
        }

        return minSecToEnd;
    }

    private void checkConditionAndAddQueue(int toY, int toX, Queue<Point> queue, int curSec) {
        if (!marked[toY][toX] && !board[toY][toX].equals("X")) {
            queue.add(new Point(toY, toX, curSec + 1));
            marked[toY][toX] = true;
        }
    }

    public static void main(String[] args) {
        Programmers159993 programmers159993 = new Programmers159993();
        int solution = programmers159993.solution(
                new String[]{"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"}
        );
        System.out.println(solution);
    }
}
