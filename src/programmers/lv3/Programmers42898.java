package programmers.lv3;

/**
 * 등굣길
 */
public class Programmers42898 {

    public int solution(int m, int n, int[][] puddles) {

        int[][] board = new int[n][m];
        // 기본 셋팅 - 우선 모두 1로 하고
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = 1;
            }
        }
        // 물에 잠긴 곳은 0으로
        for (int[] puddle : puddles) {
            board[puddle[1] - 1][puddle[0] - 1] = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // 물에 잠긴 곳은 pass
                if (board[i][j] == 0) {
                    continue;
                }

                // 위쪽 라인
                if (i == 0) {
                    if (j == 0) {
                        board[i][j] = 1;
                    } else {
                        board[i][j] = board[i][j - 1];
                    }
                }

                // 그 이외라면 물웅덩이 0짜리 신경쓰지 않아도 되고 위쪽 + 왼쪽
                if (i != 0) {
                    if (j == 0) {
                        board[i][j] = board[i - 1][j];
                    } else {
                        board[i][j] = (board[i-1][j] + board[i][j-1]) % 1000000007;
                    }
                }
            }
        }

        return board[n - 1][m - 1];
    }

    public static void main(String[] args) {
        Programmers42898 programmers42898 = new Programmers42898();
        int solution = programmers42898.solution(
                4, 3, new int[][]{{2,2}}
        );
        System.out.println(solution);

    }
}
