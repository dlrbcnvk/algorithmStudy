package programmers.lv2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 최대값 최소값
 */
public class Programmers12939 {
    public String solution(String s) {
        String answer = "";
        String[] arr = s.split(" ");
        List<Integer> intArr = Stream.of(arr).map(Integer::new)
                .collect(Collectors.toList());
        Integer max = Integer.MIN_VALUE;
        Integer min = Integer.MAX_VALUE;
        for (Integer num : intArr) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        return min + " " + max;
    }

    public static void main(String[] args) {
        Programmers12939 programmers12939 = new Programmers12939();
        String result = programmers12939.solution("-1 -2 -3 -4");
        System.out.println(result);
    }
}
