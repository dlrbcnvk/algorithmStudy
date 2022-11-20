package programmers.lv2;

/**
 * 멀리 뛰기
 * 2칸짜리들을 1칸짜리들 사이 어디에 배치할지 고민. nCr 미리 다 계산하기
 */
public class Programmers12914 {
    public long solution(int n) {
        long count = 0;

        int[][] board = new int[2001][2001];

        for (int i = 1; i <= 2000; i++) {
            board[i][0] = 1;
            board[i][i] = 1;
        }
        for (int i = 2; i <= 2000; i++) {
            for (int j = 1; j < i; j++) {
                board[i][j] = (board[i-1][j-1] + board[i-1][j]) % 1234567;
            }
        }

        int maxTwoCount = n / 2;
        for (int twoCount = 0; twoCount <= maxTwoCount; twoCount++) {
            int oneCount = n - twoCount * 2;
            count = (count + board[oneCount + twoCount][twoCount]) % 1234567;
        }

        return count;
    }

    public static void main(String[] args) {
        Programmers12914 programmers12914 = new Programmers12914();
        long solution = programmers12914.solution(3);
        System.out.println(solution);
    }
}
