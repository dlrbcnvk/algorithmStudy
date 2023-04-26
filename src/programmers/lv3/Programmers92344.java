package programmers.lv3;

/**
 * 파괴되지 않은 건물
 * 미해결
 */
public class Programmers92344 {

    public int solution(int[][] board, int[][] skill) {

        // timeout
        for (int[] s : skill) {
            for (int i = s[1]; i <= s[3]; i++) {
                for (int j = s[2]; j <= s[4]; j++) {
                    if (s[0] == 1) {
                        board[i][j] -= s[5];
                    } else {
                        board[i][j] += s[5];
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Programmers92344 programmers92344 = new Programmers92344();
        int solution = programmers92344.solution(
                new int[][]{{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}},
                new int[][]{{1, 0, 0, 3, 3, 4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}}
        );
        System.out.println(solution);
    }
}
