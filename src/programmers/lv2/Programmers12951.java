package programmers.lv2;

import javax.xml.stream.events.Characters;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 * JadenCase 문자열 만들기
 * 미해결
 */
public class Programmers12951 {

    public String solution(String s) {
        String answer = "";
        s = s.trim();
        String[] strArr = s.split("\\s+");

        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = strArr[i].toLowerCase();
            String str = strArr[i];
            if (Character.isDigit(str.charAt(0))) {
                answer += str + " ";
                continue;
            }
            char[] charArr = str.toCharArray();
            String first = String.valueOf(charArr[0]);
            charArr[0] = first.toUpperCase().toCharArray()[0];

            String reducedString = "";
            for (char c : charArr) {
                reducedString += c;
            }
            answer += reducedString + " ";
        }

        answer = answer.substring(0, answer.length() - 1);
        return answer;
    }

    public static void main(String[] args) {
        Programmers12951 programmers12951 = new Programmers12951();
        String result = programmers12951.solution("   fo  34r  the    last   week    ");
        System.out.println(result);
    }
}
