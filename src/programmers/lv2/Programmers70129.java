package programmers.lv2;

import java.util.Arrays;

/**
 * 이진 변환 반복하기
 */
public class Programmers70129 {

    public int[] solution(String s) {

        int zeroCount = 0;
        int count = 0;

        while (!s.equals("1")) {
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (c == '0') {
                    zeroCount++;
                }
            }
            s = s.replaceAll("0", "");
            int value = s.length();
            s = Integer.toString(value, 2);
            count++;
        }

        return new int[]{count, zeroCount};
    }

    public static void main(String[] args) {
        Programmers70129 programmers70129 = new Programmers70129();
        int[] solution = programmers70129.solution("110010101001");
        System.out.println(solution[0] + " " + solution[1]);
    }
}
