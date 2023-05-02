package programmers.lv2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 무인도 여행
 * 2차원배열 bfs
 */
public class Programmers154540 {

    // 상 하 좌 우
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    private static class Point {
        int y;
        int x;
        int data;

        public Point(int y, int x, int data) {
            this.y = y;
            this.x = x;
            this.data = data;
        }
    }

    public int[] solution(String[] maps) {

        int r, c;
        r = maps.length;
        c = maps[0].length();
        int[][] board = new int[r][c];
        boolean[][] marked = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String[] mapSplit = maps[i].split("");
            for (int j = 0; j < c; j++) {
                String s = mapSplit[j];
                if (s.equals("X")) {
                    board[i][j] = -1;
                } else {
                    board[i][j] = Integer.parseInt(s);
                }
            }
        }

        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!marked[i][j] && board[i][j] != -1) {
                    // start bfs
                    int sum = 0;
                    Queue<Point> q = new LinkedList<>();
                    q.add(new Point(i, j, board[i][j]));
                    sum += board[i][j];
                    marked[i][j] = true;

                    while (!q.isEmpty()) {
                        Point poll = q.poll();
                        int y = poll.y;
                        int x = poll.x;

                        for (int d = 0; d < 4; d++) {
                            int toY = y + dy[d];
                            int toX = x + dx[d];

                            if (toY >= 0 &&
                                    toY < r &&
                                    toX >= 0 &&
                                    toX < c &&
                                    board[toY][toX] != -1 &&
                                    !marked[toY][toX]) {
                                marked[toY][toX] = true;
                                sum += board[toY][toX];
                                q.add(new Point(toY, toX, board[toY][toX]));
                            }
                        }
                    }

                    if (sum != 0) {
                        resultList.add(sum);
                    }
                }
            }
        }

        if (resultList.isEmpty()) return new int[]{-1};


        return resultList.stream().sorted().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Programmers154540 programmers154540 = new Programmers154540();
        int[] solution = programmers154540.solution(
                new String[]{"X591X","X1X5X","X231X", "1XXX1"}
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
