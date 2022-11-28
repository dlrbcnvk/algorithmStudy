package programmers.lv2;

import java.util.ArrayList;

/**
 * 삼각 달팽이
 */
public class Programmers68645 {

    public enum Direction {
        DOWN, RIGHT, UP
    }

    public int[] solution(int n) {

        int[][] board = new int[n][n];

        int x = 0;
        int y = 0;
        int length = n;
        int value = 1;
        Direction direction = Direction.DOWN;
        while (length > 0) {

            switch (direction) {
                case DOWN:
                    int count = 0;
                    while (count < length) {
                        board[x][y] = value;
                        count++;
                        value++;
                        if (count < length) {
                            x++;
                        } else if (count == length) {
                            y++;
                            length--;
                            direction = Direction.RIGHT;
                        }
                    }
                    break;
                case RIGHT:
                    count = 0;
                    while (count < length) {
                        board[x][y] = value;
                        count++;
                        value++;
                        if (count < length) {
                            y++;
                        } else if (count == length) {
                            x--;
                            y--;
                            length--;
                            direction = Direction.UP;
                        }
                    }
                    break;
                case UP:
                    count = 0;
                    while (count < length) {
                        board[x][y] = value;
                        count++;
                        value++;
                        if (count < length) {
                            x--;
                            y--;
                        } else if (count == length) {
                            x++;
                            length--;
                            direction = Direction.DOWN;
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();
        int rowLength = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < rowLength; j++) {
                arr.add(board[i][j]);
            }
            rowLength++;
        }

        return arr.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Programmers68645 programmers68645 = new Programmers68645();
        int[] solution = programmers68645.solution(5);

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
