package programmers.lv2;

import java.util.Arrays;
import java.util.Collections;

/**
 * 미해결
 * 어떻게 하는지 감이 잘 안 옴..ㅠ
 * n ~ 1 일일이 이분탐색 인덱스 찾으면서 판단해야하나...?
 */
public class Programmers42747 {

    int[] citations;

    public boolean condition(int n) {
        return true;
    }

    public int solution(int[] citations) {
        this.citations = citations;
        Arrays.sort(citations);




        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {
        Programmers42747 programmers42747 = new Programmers42747();
        int solution = programmers42747.solution(
                new int[]{2,3,3,3,4,5,5,5}
        );

        System.out.println(solution);

    }
}
