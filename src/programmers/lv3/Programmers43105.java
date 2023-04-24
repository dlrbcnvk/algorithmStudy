package programmers.lv3;

/**
 * 정수 삼각형
 */
public class Programmers43105 {
    public int solution(int[][] triangle) {

        int bottom = triangle.length;
        int[][] board = new int[bottom][bottom];
        board[0][0] = triangle[0][0];
        if (triangle.length == 1) {
            return board[0][0];
        }
        for (int i = 1; i < bottom; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    board[i][j] = board[i - 1][j] + triangle[i][j];
                } else if (j == i) {
                    board[i][j] = board[i - 1][j - 1] + triangle[i][j];
                } else if ((board[i - 1][j - 1] > board[i - 1][j])) {
                    board[i][j] = board[i - 1][j - 1] + triangle[i][j];
                } else {
                    board[i][j] = board[i - 1][j] + triangle[i][j];
                }
            }
        }

        int max = 0;
        for (int i = 0; i < bottom; i++) {
            if (max < board[bottom - 1][i]) {
                max = board[bottom - 1][i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Programmers43105 programmers43105 = new Programmers43105();
        int solution = programmers43105.solution(
                new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}
        );
        System.out.println(solution);
    }
}
