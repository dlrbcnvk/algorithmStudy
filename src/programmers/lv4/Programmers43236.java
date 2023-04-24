package programmers.lv4;

import java.util.Arrays;

/**
 * 징검다리
 * 이분탐색?
 * 미해결
 */
public class Programmers43236 {

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);



        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {
        Programmers43236 programmers43236 = new Programmers43236();
        int solution = programmers43236.solution(
                25, new int[]{2, 14, 11, 21, 17}, 2
        );
        System.out.println(solution);
    }
}
