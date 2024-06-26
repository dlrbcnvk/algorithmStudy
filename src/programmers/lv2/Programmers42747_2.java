package programmers.lv2;

import java.util.Arrays;

/**
 * H-Index
 * https://school.programmers.co.kr/learn/courses/30/lessons/42747
 * 미해결
 */
public class Programmers42747_2 {

    private Integer H = null;

    public int solution(int[] citations) {

        // sort
        Arrays.sort(citations);

        int h = 1;
        int citationIdx = 0;
        while (citationIdx < citations.length) {
            String str = "abcde";

        }



        return 0;
    }

    public static void main(String[] args) {
        Programmers42747_2 programmers427472 = new Programmers42747_2();
//        int solution = programmers427472.solution();
//        System.out.println(solution);
    }
}
