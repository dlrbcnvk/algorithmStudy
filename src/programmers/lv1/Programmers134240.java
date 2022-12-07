package programmers.lv1;

import java.util.*;

/**
 * 푸드 파이트 대회
 */
public class Programmers134240 {

    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> keyList = new ArrayList<>();
        HashMap<Integer, Integer> keyValueMap = new HashMap<>();
        for (int i = 1; i < food.length; i++) {
            int count = food[i] / 2;
            if (count == 0) {
                continue;
            }
            keyList.add(i);
            keyValueMap.put(i, count);
        }

        for (int i = 0; i < keyList.size(); i++) {
            Integer key = keyList.get(i);
            for (int j = 0; j < keyValueMap.get(key); j++) {
                sb.append(key);
            }
        }
        sb.append("0");
        for (int i = keyList.size() - 1; i >= 0; i--) {
            Integer key = keyList.get(i);
            for (int j = 0; j < keyValueMap.get(key); j++) {
                sb.append(key);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Programmers134240 programmers134240 = new Programmers134240();
        String solution = programmers134240.solution(new int[]{1,7,1,2});
        System.out.println(solution);
    }
}
