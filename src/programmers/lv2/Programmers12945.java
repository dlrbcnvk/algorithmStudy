package programmers.lv2;

import java.util.Arrays;

/**
 * 피보나치 수
 */
public class Programmers12945 {

    public int solution(int n) {

        int[] fibonacci = new int[100001];
        Arrays.fill(fibonacci, -1);
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        fibonacci[2] = 1;

        for (int i = 3; i <= n; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        return fibonacci[n];
    }

    public static void main(String[] args) {
        Programmers12945 programmers12945 = new Programmers12945();
        int solution = programmers12945.solution(5);
        System.out.println(solution);
    }
}
