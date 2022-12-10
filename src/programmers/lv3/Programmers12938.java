package programmers.lv3;

import java.util.Arrays;

/**
 * 최고의 집합
 * 미해결..ㅠㅠ
 */
public class Programmers12938 {
    public int[] solution(int n, int s) {

        if (n * (n + 1) / 2 > s) {
            return new int[]{-1};
        }

        if (s % n == 0) {
            int[] result = new int[n];
            int num = s / n;
            for (int i = 0; i < result.length; i++) {
                result[i] = num;
            }
            return result;
        }

        int a = s / n;
        int mod = s % n;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < n - mod) {
                result[i] = a + 1;
            } else {
                result[i] = a;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        Programmers12938 programmers12938 = new Programmers12938();
        int[] solution = programmers12938.solution(2, 9);

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
