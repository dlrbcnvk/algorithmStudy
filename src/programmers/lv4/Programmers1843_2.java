package programmers.lv4;

import java.util.HashMap;
import java.util.Map;

/**
 * 사칙연산
 * 미해결
 */
public class Programmers1843_2 {

    private static String PLUS = "+";
    private static String MINUS = "-";

    public int solution(String arr[]) {

        Map<Integer, Map<Integer, Integer>> maxGroup = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> minGroup = new HashMap<>();
        for (int i = 0; i < arr.length; i += 2) {
            if (i + 2 == arr.length - 1) {
                break;
            }
            int left = Integer.parseInt(arr[i]);
            String operator = arr[i + 1];
            int right = Integer.parseInt(arr[i + 2]);
            int result = 0;
            if (PLUS.equals(operator)) {
                result = left + right;
            } else if (MINUS.equals(operator)) {
                result = left - right;
            }
            maxGroup.put(i, new HashMap<>());
            maxGroup.get(i).put(i + 2, result);
            minGroup.put(i, new HashMap<>());
            minGroup.get(i).put(i + 2, result);
        }





        int answer = -1;
        return answer;
    }

    public static void main(String[] args) {
        Programmers1843_2 programmers18432 = new Programmers1843_2();
        String[] arr = new String[]{"5", "-", "3", "+", "1", "+", "2", "-", "4"};
        int solution = programmers18432.solution(arr);
        System.out.println(solution);
    }
}
