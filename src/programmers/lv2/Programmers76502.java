package programmers.lv2;

import java.util.Arrays;

/**
 * 괄호 회전하기
 * 미해결. 일단 패스
 */
public class Programmers76502 {

    public int solution(String s) {
        int answer = 0;

        char[] chars = s.toCharArray();
        String[] arr = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            arr[i] = String.valueOf(chars[i]);
        }


        return answer;
    }

    public static void main(String[] args) {
        Programmers76502 programmers76502 = new Programmers76502();
        int solution = programmers76502.solution("[](){}");
        System.out.println(solution);
    }
}
