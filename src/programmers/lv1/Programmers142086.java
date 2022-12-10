package programmers.lv1;

import java.util.HashMap;

/**
 * 가장 가까운 같은 글자
 * map 사용
 */
public class Programmers142086 {

    public int[] solution(String s) {

        HashMap<String, Integer> lastIdxMap = new HashMap<>();
        char[] chars = s.toCharArray();
        int[] answer = new int[s.length()];
        for (int i = 0; i < answer.length; i++) {
            String str = chars[i] + "";
            if (!lastIdxMap.containsKey(str)) {
                answer[i] = -1;
                lastIdxMap.put(str, i);
            } else {
                answer[i] = i - lastIdxMap.get(str);
                lastIdxMap.put(str, i);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers142086 programmers142086 = new Programmers142086();
        int[] solution = programmers142086.solution("banana");
        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
