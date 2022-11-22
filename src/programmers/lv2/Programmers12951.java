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
        StringBuilder answer = new StringBuilder();
        s = s.trim();
        String[] strArr = s.split("\\s+");

        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = strArr[i].toLowerCase();
            String str = strArr[i];
            if (Character.isDigit(str.charAt(0))) {
                answer.append(str);
                if (i != strArr.length - 1) {
                    answer.append(" ");
                }
                continue;
            }
            char[] charArr = str.toCharArray();
            String first = String.valueOf(charArr[0]);
            charArr[0] = first.toUpperCase().toCharArray()[0];

            StringBuilder reducedString = new StringBuilder();
            for (char c : charArr) {
                reducedString.append(c);
            }

            answer.append(reducedString);
            if (i != strArr.length - 1) {
                answer.append(" ");
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Programmers12951 programmers12951 = new Programmers12951();
        String result = programmers12951.solution("   f aaaaa aaa o  34r  The    7aAst   weEK  4mm   ");
        System.out.println(result);
    }
}
