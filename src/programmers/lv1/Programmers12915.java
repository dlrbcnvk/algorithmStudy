package programmers.lv1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 문자열 내 마음대로 정렬하기
 */
public class Programmers12915 {

    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, (s1, s2) -> {
            int diff = s1.charAt(n) - s2.charAt(n);
            if (diff != 0) {
                return diff;
            }
            return s1.compareTo(s2);
        });
        return strings;
    }

    public static void main(String[] args) {
        Programmers12915 programmers12915 = new Programmers12915();
        String[] solution = programmers12915.solution(
                new String[]{"abce", "abcd", "cdx"}, 2
        );

        for (String s : solution) {
            System.out.print(s + " ");
        }
    }
}
