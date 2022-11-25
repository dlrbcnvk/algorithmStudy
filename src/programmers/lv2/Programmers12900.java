package programmers.lv2;

/**
 * 2 x n 타일링
 * dp 수열 규칙 찾아서 점화식 세우기
 */
public class Programmers12900 {

    public int solution(int n) {

        int[] sequence = new int[n + 1];
        sequence[0] = 0;
        sequence[1] = 1;
        sequence[2] = 2;
        if (n <= 2) {
            return sequence[n];
        }

        for (int i = 3; i <= n; i++) {
            sequence[i] = (sequence[i - 1] + sequence[i - 2]) % 1000000007;
        }

        return sequence[n];
    }

    public static void main(String[] args) {
        Programmers12900 programmers12900 = new Programmers12900();
        int solution = programmers12900.solution(4);
        System.out.println(solution);
    }
}
