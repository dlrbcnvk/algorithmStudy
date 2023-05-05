package programmers.lv3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 경주로 건설
 */
public class Programmers67259 {

    // 상 하 좌 우
    private static int[] dy = new int[]{-1, 1, 0, 0};
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static Direction[] directions = new Direction[]{Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private static class PointDirection {
        int y;
        int x;
        Direction direction;

        public PointDirection(int y, int x, Direction direction) {
            this.y = y;
            this.x = x;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "PointDirection{" +
                    "y=" + y +
                    ", x=" + x +
                    ", direction=" + direction +
                    '}';
        }
    }

    public int solution(int[][] board) {

        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] == 1 ? 1 : Integer.MAX_VALUE;
            }
        }
        board[0][0] = 0;

        Queue<PointDirection> queue = new LinkedList<>();
        queue.add(new PointDirection(0, 0, Direction.RIGHT));
        queue.add(new PointDirection(0, 0, Direction.DOWN));
        while (!queue.isEmpty()) {
            PointDirection poll = queue.poll();
            int y = poll.y;
            int x = poll.x;
            Direction direction = poll.direction;

            for (int i = 0; i < 4; i++) {
                int toY = y + dy[i];
                int toX = x + dx[i];
                Direction toDirection = directions[i];

                if (toY >= 0 && toY < n && toX >= 0 && toX < n && board[toY][toX] != 1) {
                    if (direction == toDirection) {
                        if (board[y][x] + 100 < board[toY][toX]) {
                            board[toY][toX] = board[y][x] + 100;
                            queue.add(new PointDirection(toY, toX, toDirection));
                        }
                    } else {
                        if (board[y][x] + 600 <= board[toY][toX]) {
                            board[toY][toX] = board[y][x] + 600;
                            queue.add(new PointDirection(toY, toX, toDirection));
                        }
                    }
                }
            }
        }

//        print(board);
        return board[n - 1][n - 1];
    }

    private void print(int[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Programmers67259 programmers67259 = new Programmers67259();
        int solution = programmers67259.solution(
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0, 1},
                        {0, 0, 1, 0, 0, 0, 1, 0},
                        {0, 1, 0, 0, 0, 1, 0, 0},
                        {1, 0, 0, 0, 0, 0, 0, 0}
                }
        );
        System.out.println(solution);
    }
}
