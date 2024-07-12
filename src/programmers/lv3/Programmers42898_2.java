package programmers.lv3;

/**
 * 등굣길
 */
public class Programmers42898_2 {

    /**
     *
     * @param m 가로
     * @param n 세로
     * @param puddles 물이 잠긴 지역의 좌표를 담은 2차원 배열 (one-ased index)
     */
    public int solution(int m, int n, int[][] puddles) {

        int[][] board = new int[n][m];
        for (int i = 0; i < board.length; i++) {
            board[i] = new int[m];
        }
        for (int i = 0; i < puddles.length; i++) {
            int y = puddles[i][1] - 1;
            int x = puddles[i][0] - 1;
            board[y][x] = -1;
        }

        boolean rowBlocked = false;
        boolean columnBlocked = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && board[i][j] != -1) {
                    if (!rowBlocked) {
                        board[i][j] = 1;
                    }
                    continue;
                }
                if (j == 0 && board[i][j] != -1) {
                    if (!columnBlocked) {
                        board[i][j] = 1;
                    }
                    continue;
                }

                if (board[i][j] == -1) {
                    if (i == 0) {
                        rowBlocked = true;
                    }
                    if (j == 0) {
                        columnBlocked = true;
                    }
                    continue;
                }

                if (board[i - 1][j] == -1 && board[i][j - 1] == -1) {
                    continue;
                }

                if (board[i - 1][j] == -1) {
                    board[i][j] = board[i][j - 1];
                } else if (board[i][j - 1] == -1) {
                    board[i][j] = board[i - 1][j];
                } else {
                    board[i][j] = (board[i - 1][j] + board[i][j - 1]) % 1_000_000_007;
                }
            }
        }

        return board[n - 1][m - 1];
    }

    public static void main(String[] args) {
        Programmers42898_2 programmers428982 = new Programmers42898_2();
        int solution = programmers428982.solution(4, 3,
                new int[][]{
                        {3,1},
                        {2,3}
                }
        );
        System.out.println(solution);
    }
}
