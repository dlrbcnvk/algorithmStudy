package programmers.lv1;

import java.util.Arrays;

/**
 * 체육복
 */
public class Programmers42862 {
    public int solution(int n, int[] lost, int[] reserve) {

        boolean[] marked = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            marked[i] = true;
        }
        marked[0] = false;
        for (int i = 0; i < lost.length; i++) {
            // 잃어버렸다
            marked[lost[i]] = false;
        }
        for (int i = 0; i < reserve.length; i++) {
            int idx = reserve[i];
            // 잃어버렸지만 여벌이 있어 부족하지 않다
            if (!marked[idx]) {
                marked[idx] = true;
            }
        }
        Arrays.sort(reserve);
        for (int i = 0; i < reserve.length; i++) {
            int idx = reserve[i];
            boolean canBorrow = true;
            for (int j = 0; j < lost.length; j++) {
                if (lost[j] == idx) {
                    canBorrow = false;
                }
            }

            if (idx > 1) {
                if (!marked[idx - 1] && canBorrow) {
                    marked[idx - 1] = true;
                    canBorrow = false;
                }
            }
            if (idx < n) {
                if (!marked[idx + 1] && canBorrow) {
                    marked[idx + 1] = true;
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (marked[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Programmers42862 programmers42862 = new Programmers42862();
        int solution = programmers42862.solution(
                5, new int[]{2, 4 , 3}, new int[]{3}
        );
        System.out.println(solution);
    }
}
