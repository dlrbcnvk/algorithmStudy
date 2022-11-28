package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 큰 수 만들기
 * 미해결 ㅠㅠ
 */
public class Programmers42883 {
    public String solution(String number, int k) {
        String answer = "";

        char[] chars = number.toCharArray();
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            strings.add(chars[i] + "");
        }

        for (int i = 0; i < k; i++) {
            strings.remove(0);
        }
        for (String num : strings) {
            answer += num;
        }

        return answer;
    }


    public static void main(String[] args) {
        Programmers42883 programmers42883 = new Programmers42883();
        String solution = programmers42883.solution("4177252841", 4);
        System.out.println(solution);
    }
}
