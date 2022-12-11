package programmers.lv2;

/**
 * 땅따먹기
 * 완전탐색 재귀 - 시간초과
 * dp로 해결
 * 열 내려가면서 현재까지 해당 인덱스에서 얻을 수 있는 최대값을 기록하면서 내려감
 */
public class Programmers12913 {

    int[][] land;
    int n;
    int[][] board;

    int solution(int[][] land) {

        this.n = land.length;

        this.land = land;
        this.board = new int[n][4];
        for (int i = 0; i < 4; i++) {
            board[0][i] = land[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                int value = -1;
                for (int k = 0; k < 4; k++) {
                    if (j != k) {
                        if (board[i - 1][k] > value) {
                            value = board[i - 1][k];
                        }
                    }
                }
                board[i][j] = land[i][j] + value;
            }
        }

        int answer = -1;
        for (int i = 0; i < 4; i++) {
            if (board[n - 1][i] > answer) {
                answer = board[n-1][i];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers12913 programmers12913 = new Programmers12913();
        int solution = programmers12913.solution(
                new int[][]{{1,2,3,5},{5,6,7,8},{4,3,2,1}}
        );
        System.out.println(solution);
    }
}
