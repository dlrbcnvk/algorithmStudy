package programmers.lv0;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A로 B 만들기
 */
public class Programmers120886 {

    public int solution(String before, String after) {

        Map<Character, Integer> beforeMap = new HashMap<>();
        Map<Character, Integer> afterMap = new TreeMap<>();

        for (char c : before.toCharArray()) {
            if (!beforeMap.containsKey(c)) {
                beforeMap.put(c, 1);
            } else {
                Integer count = beforeMap.get(c);
                beforeMap.put(c, count + 1);
            }
        }
        for (char c : after.toCharArray()) {
            if (!afterMap.containsKey(c)) {
                afterMap.put(c, 1);
            } else {
                Integer count = afterMap.get(c);
                afterMap.put(c, count + 1);
            }
        }

        return beforeMap.equals(afterMap) ? 1 : 0;
    }

    public static void main(String[] args) {
        Programmers120886 programmers120886 = new Programmers120886();
        int solution = programmers120886.solution("olleh", "hello");
        System.out.println(solution);
    }
}
