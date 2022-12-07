package programmers.lv2;

/**
 * 3 x n 타일링
 * 숫자놀이.. -_-
 */
public class Programmers12902 {

    static int DIVISOR = 1000000007;

    public int solution(int n) {

        if (n % 2 == 1) {
            return 0;
        }

        long[] sequence = new long[5001];
        long[] lastTile = new long[5001];
        sequence[1] = 0;
        sequence[2] = 3;
        sequence[3] = 0;
        lastTile[2] = 2;
        for (int i = 4; i <= n; i += 2) {
            sequence[i] = ((sequence[i - 2] * 3) + lastTile[i - 2]) % DIVISOR;
            if (sequence[i] < sequence[i - 2]) {
                lastTile[i] = (sequence[i] + DIVISOR) - sequence[i - 2];
            } else {
                lastTile[i] = sequence[i] - sequence[i - 2];
            }
            lastTile[i] = lastTile[i] % DIVISOR;
        }

        return (int)(sequence[n]) % DIVISOR;
    }

    public static void main(String[] args) {
        Programmers12902 programmers12902 = new Programmers12902();
        int solution = programmers12902.solution(4000);
        System.out.println(solution);
    }
}
