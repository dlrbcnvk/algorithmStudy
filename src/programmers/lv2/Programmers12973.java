package programmers.lv2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 짝지어 제거하기
 * 미해결
 */
public class Programmers12973 {

    public int solution(String s) {

        String[] arr = s.split("");
        List<String> collect = Arrays.stream(arr).collect(Collectors.toList());

        while (true) {
            int i = 0;
            boolean found = false;



            for (i = 0; i < collect.size() - 1; i++) {
                String s1 = collect.get(i);
                String s2 = collect.get(i + 1);
                if (s1.equals(s2)) {
                    found = true;
                    collect.remove(i + 1);
                    collect.remove(i);
                    break;
                }
            }
            if (!found) {
                break;
            }
        }

        if (collect.isEmpty()) {
            return 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        Programmers12973 programmers12973 = new Programmers12973();
        int solution = programmers12973.solution("cdcd");
        System.out.println(solution);
    }
}
