package programmers.lv2;

import java.util.HashMap;
import java.util.Map;

/**
 * 할인 행사
 * Map 이용
 */
public class Programmers131127 {

    private String[] want;
    private int[] number;

    public boolean join(Map<String, Integer> map) {

        for (int i = 0; i < want.length; i++) {
            String item = want[i];
            if (!map.containsKey(item)) {
                return false;
            }
            Integer value = map.get(item);
            if (number[i] != value) {
                return false;
            }
        }
        return true;
    }

    public int solution(String[] want, int[] number, String[] discount) {
        int joinDays = 0;
        this.want = want;
        this.number = number;

        HashMap<String, Integer> map = new HashMap<>();
        for (String item : want) {
            map.put(item, 0);
        }

        for (int day = 0; day < discount.length; day++) {
            String item = discount[day];
            if (map.containsKey(item)) {
                Integer num = map.get(item);
                map.put(item, num + 1);
            }
            if (day >= 10) {
                String before10Days = discount[day - 10];
                if (map.containsKey(before10Days)) {
                    Integer value = map.get(before10Days);
                    map.put(before10Days, value - 1);
                }
            }

            if (join(map)) {
                joinDays++;
            }
        }

        return joinDays;
    }

    public static void main(String[] args) {
        Programmers131127 programmers131127 = new Programmers131127();
        int solution = programmers131127.solution(
                new String[]{"banana", "apple", "rice", "pork", "pot"},
                new int[]{3, 2, 2, 2, 1},
                new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"}
        );
        System.out.println(solution);
    }
}
