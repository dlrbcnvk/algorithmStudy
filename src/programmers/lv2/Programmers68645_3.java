package programmers.lv2;

import java.util.ArrayList;
import java.util.List;

/**
 * 삼각 달팽이
 * https://school.programmers.co.kr/learn/courses/30/lessons/68645
 */
public class Programmers68645_3 {

    private int[] dy = new int[]{-1, 1, 0, 0};
    private int[] dx = new int[]{0, 0, -1, 1};

    private enum Direction {
        DOWN,
        UPLEFT,
        RIGHT
    }

    public int[] solution(int n) {

        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = new int[n];
            for (int j = 0; j < n; j++) {
                board[i][j] = 0;
            }
        }

        int y = 0;
        int x = 0;
        int value = 1;
        Direction direction = Direction.DOWN;
        while (true) {
            // 종료조건
            if (y < 0 || x < 0 || y >= n || x >= n) break;
            if (board[y][x] != 0) break;

            // 현위치 찍기
            board[y][x] = value;
            value++;

            // 다음지점으로 이동
            if (direction == Direction.DOWN) {
                if (y == n - 1 || board[y+1][x] != 0) {
                    direction = Direction.RIGHT;
                    x++;
                } else {
                    y++;
                }
            }
            else if (direction == Direction.RIGHT) {
                if (x == n - 1 || board[y][x + 1] != 0) {
                    direction = Direction.UPLEFT;
                    y--;
                    x--;
                } else {
                    x++;
                }
            }
            else if (direction == Direction.UPLEFT) {
                if (y == 0 || x == 0 || board[y - 1][x - 1] != 0) {
                    direction = Direction.DOWN;
                    y++;
                } else {
                    y--;
                    x--;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    list.add(board[i][j]);
                }
            }
        }
        int[] answer = new int[list.size()];
        int idx = 0;
        for (int val : list) {
            answer[idx] = val;
            idx++;
        }
        return answer;
    }
    public static void main(String[] args) {
        Programmers68645_3 programmers686453 = new Programmers68645_3();
        int[] solution = programmers686453.solution(1);

        for (int value : solution) {
            System.out.print(value + " ");
        }
    }
}
