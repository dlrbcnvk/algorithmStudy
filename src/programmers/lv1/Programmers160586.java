package programmers.lv1;

import java.util.HashMap;
import java.util.Map;

/**
 * 대충 만든 자판
 */
public class Programmers160586 {

    public int[] solution(String[] keymap, String[] targets) {

        Map<String, Integer> keyTouchCountMap = new HashMap<>();
        for (String keys : keymap) {
            String[] split = keys.split("");
            for (int i = 0; i < split.length; i++) {
                String keyChar = split[i];
                if (keyTouchCountMap.containsKey(keyChar)) {
                    Integer idx = keyTouchCountMap.get(keyChar);
                    keyTouchCountMap.put(keyChar, (i < idx) ? i : idx);
                } else {
                    keyTouchCountMap.put(keyChar, i);
                }
            }
        }

        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            answer[i] = go(targets[i], keyTouchCountMap);
        }

        return answer;
    }

    private int go(String target, Map<String, Integer> keyTouchCountMap) {
        int count = 0;
        for (String t : target.split("")) {
            if (keyTouchCountMap.containsKey(t)) {
                count += keyTouchCountMap.get(t) + 1;
            } else {
                return -1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Programmers160586 programmers160586 = new Programmers160586();
        int[] solution = programmers160586.solution(
                new String[]{"ABACD", "BCEFD"}, new String[]{"ABCD", "AABB"}
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }

    }
}
