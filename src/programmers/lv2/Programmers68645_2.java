package programmers.lv2;

import java.util.ArrayList;
import java.util.List;

/**
 * 삼각 달팽이
 * https://school.programmers.co.kr/learn/courses/30/lessons/68645
 * 2024.03.17
 */
public class Programmers68645_2 {

    public int[] solution(int n) {
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = new int[n];
        }

        go(0, 0, 1, board, n);

        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    resultList.add(board[i][j]);
                } else {
                    break;
                }
            }
        }
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }

    public void go(int y, int x, int value, int[][] board, int n) {
        if (y < 0 || x < 0 || y >= n || x >= n) {
            throw new IllegalArgumentException("배열 인덱스로 가능한 숫자가 아닙니다.");
        }

        int currentY = y;
        int currentX = x;
        // 아래로 진행
        while (currentY < n && board[currentY][currentX] == 0) {
            board[currentY][currentX] = value;
            value++;

            // 마지막인 경우 오른쪽으로 이동하고 루프 종료
            if (currentY == n - 1 || board[currentY + 1][currentX] != 0) {
                currentX++;
                break;
            }
            currentY++;
        }
        // 오른쪽으로 진행
        while (currentX < n && board[currentY][currentX] == 0) {
            board[currentY][currentX] = value;
            value++;

            // 마지막인 경우 왼쪽 위로 이동하고 루프 종료
            if (currentX == n - 1 || board[currentY][currentX + 1] != 0) {
                currentY--;
                currentX--;
                break;
            }
            currentX++;
        }
        // 좌상 대각선으로 진행
        while (currentY > 0 && currentX > 0 && currentY < n && currentX < n && board[currentY][currentX] == 0) {
            board[currentY][currentX] = value;
            value++;

            // 마지막인 경우 아래로 이동하고 루프 종료
            if (currentY == 1 || board[currentY - 1][currentX - 1] != 0) {
                currentY++;
                break;
            }
            currentY--;
            currentX--;
        }

        // 새로운 순환을 시작해야하는지
        if (currentY < n && currentX < n && currentY > 0 && currentX > 0 && board[currentY][currentX] == 0) {
            go(currentY, currentX, value, board, n);
        }
    }

    public static void main(String[] args) {
        Programmers68645_2 programmers686452 = new Programmers68645_2();
        int[] solution = programmers686452.solution(1);

        System.out.print("[");
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + ",");
        }
        System.out.print("]");
    }
}
