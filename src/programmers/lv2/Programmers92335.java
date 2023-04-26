package programmers.lv2;

import java.util.StringTokenizer;

/**
 * k진수에서 소수 개수 구하기
 * 2023.04.26 AC
 */
public class Programmers92335 {

    public int solution(int n, int k) {
        String str = Integer.toString(n, k);
        StringTokenizer st = new StringTokenizer(str, "0", false);
        int answer = 0;
        while (st.hasMoreTokens()) {
            long parseLong = Long.parseLong(st.nextToken());
            System.out.println(parseLong);
            if (isPrime(parseLong)) {
                answer++;
            }
        }

        return answer;
    }

    // for 루프 변수 i 를 long 으로 바꿔야 통과되네...?
    private boolean isPrime(long parseLong) {
        if (parseLong == 1) {
            return false;
        }
        for (int i = 2; (long) i * i <= parseLong; i++) {
            if (parseLong % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Programmers92335 programmers92335 = new Programmers92335();
        int solution = programmers92335.solution(3*3*3*3*3*3*3*3*3*3*3*3 - 2, 3);
        System.out.println(solution);
    }
}
