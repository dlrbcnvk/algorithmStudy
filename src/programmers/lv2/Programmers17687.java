package programmers.lv2;

/**
 * N진수 게임
 * 2023.04.30 AC
 */
public class Programmers17687 {

    public String solution(int n, int t, int m, int p) {

        StringBuilder sb = new StringBuilder();
        int number = 0;
        int turn = 1;
        int idx = 0;
        String radixStr = Integer.toString(number, n);

        while (sb.length() < t) {

            // 본인 차례
//            System.out.println(turn + ": " + "turn % m = " + turn % m + ", m % p = " + m % p);
            if (turn % m == p % m) {
                char c = radixStr.charAt(idx);
                System.out.println("myturn, " + c);
                sb.append(Character.toUpperCase(c));
            }

            // 숫자가 모두 끝난 경우 문자열 갱신
            if (idx == radixStr.length() - 1) {
                number++;
                radixStr = Integer.toString(number, n);
                idx = 0;
            } else {
                idx++;
            }

            turn++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Programmers17687 programmers17687 = new Programmers17687();
        String solution = programmers17687.solution(2,4,2,1);
        System.out.println(solution);
    }
}
