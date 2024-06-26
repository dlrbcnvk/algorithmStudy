package programmers.lv2;


import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 가장 큰 수
 * https://school.programmers.co.kr/learn/courses/30/lessons/42746
 * 미해결
 */
public class Programmers42746_2 {

    public String solution(int[] numbers) {
        return Arrays.asList(numbers).stream()
                .map(String::valueOf)
                .sorted((s1, s2) -> {
                    if (s1.charAt(0) != s2.charAt(0)) {
                        return s2.charAt(0) - s1.charAt(1);
                    } else {
                        return -1;
                    }
                })
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {

    }
}
