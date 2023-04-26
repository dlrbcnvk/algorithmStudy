package programmers.pccp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 외톨이 알파벳
 * 2023.4.26 AC
 * https://school.programmers.co.kr/learn/courses/15008/lessons/121683
 *
 */
public class Programmers121683 {

    public String solution(String input_string) {

        String replacedStr = input_string
                .replaceAll("a+", "a")
                .replaceAll("b+", "b")
                .replaceAll("c+", "c")
                .replaceAll("d+", "d")
                .replaceAll("e+", "e")
                .replaceAll("f+", "f")
                .replaceAll("g+", "g")
                .replaceAll("h+", "h")
                .replaceAll("i+", "i")
                .replaceAll("j+", "j")
                .replaceAll("k+", "k")
                .replaceAll("l+", "l")
                .replaceAll("m+", "m")
                .replaceAll("n+", "n")
                .replaceAll("o+", "o")
                .replaceAll("p+", "p")
                .replaceAll("q+", "q")
                .replaceAll("r+", "r")
                .replaceAll("s+", "s")
                .replaceAll("t+", "t")
                .replaceAll("u+", "u")
                .replaceAll("v+", "v")
                .replaceAll("w+", "w")
                .replaceAll("x+", "x")
                .replaceAll("y+", "y")
                .replaceAll("z+", "z");

        String[] split = replacedStr.split("");
        Map<String, Integer> spellCountMap = new HashMap<>();
        for (String s : split) {
            if (spellCountMap.containsKey(s)) {
                spellCountMap.put(s, spellCountMap.get(s) + 1);
            } else {
                spellCountMap.put(s, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String key : spellCountMap.keySet()) {
            if (spellCountMap.get(key) > 1) {
                sb.append(key);
            }
        }
        String[] str = sb.toString().split("");
        Arrays.sort(str);
        sb = new StringBuilder();
        for (String s : str) {
            sb.append(s);
        }
        String result = sb.toString();
        return result.equals("") ? "N" : result;
    }

    public static void main(String[] args) {
        Programmers121683 programmers121683 = new Programmers121683();
        String solution = programmers121683.solution("string");
        System.out.println(solution);
    }
}
