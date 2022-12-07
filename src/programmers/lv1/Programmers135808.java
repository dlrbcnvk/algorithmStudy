package programmers.lv1;

import java.util.Arrays;

/**
 * 과일 장수
 */
public class Programmers135808 {

    public int solution(int k, int m, int[] score) {
        int answer = 0;

        Arrays.sort(score);
        int count = 0;
        for (int idx = score.length - 1; idx >= 0; idx--) {
            count++;
            if (count == m) {
                answer += score[idx] * m;
                count = 0;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers135808 programmers135808 = new Programmers135808();
        int solution = programmers135808.solution(4,3, new int[]{4,1,2,2,4,4,4,4,1,2,4,2});
        System.out.println(solution);
    }
}
