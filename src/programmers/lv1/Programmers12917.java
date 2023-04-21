package programmers.lv1;

import java.util.Arrays;

/**
 * 문자열 내림차순으로 배치하기
 */
public class Programmers12917 {

    public String solution(String s) {
        return s.chars()
                .boxed()
                .sorted((v1, v2) -> v2 - v1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static void main(String[] args) {
        Programmers12917 programmers12917 = new Programmers12917();
        String solution = programmers12917.solution("Zbcdefg");
        System.out.println(solution);
    }
}
