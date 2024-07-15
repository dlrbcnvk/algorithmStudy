package programmers.lv3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/* 보석 쇼핑
 * 미해결
 */
public class Programmers67258_2 {
    public int[] solution(String[] gems) {

        int[] answer = new int[]{-100000, 100000};

        int kind = Arrays.stream(gems)
                .collect(Collectors.toSet()).size();

        int st = 0;
        int en = 0;
        Map<String, Integer> gemCountMap = new HashMap<>();
        while (st < gems.length) {
            while (gemCountMap.keySet().size() != kind && en < gems.length) {
                String gem = gems[en];
                if (gemCountMap.containsKey(gem)) {
                    Integer value = gemCountMap.get(gem);
                    gemCountMap.put(gem, value + 1);
                } else {
                    gemCountMap.put(gem, 1);
                }

                if (gemCountMap.keySet().size() == kind) {
                    break;
                }

                en++;
            }

            if (gemCountMap.keySet().size() == kind) {
                int beforeSt = answer[0];
                int beforeEn = answer[1];
                if (en - st < beforeEn - beforeSt) {
                    answer[0] = st + 1;
                    answer[1] = en + 1;
                    System.out.println("(" + st + ", " + en + ")");
                }
            }

            while (st < en && st < gems.length) {

                if (gemCountMap.keySet().size() == kind) {
                    int beforeSt = answer[0];
                    int beforeEn = answer[1];
                    if (en - st < beforeEn - beforeSt) {
                        answer[0] = st + 1;
                        answer[1] = en + 1;
                        System.out.println("(" + st + ", " + en + ")");
                    }
                }

                String gem = gems[st];
                if (gemCountMap.get(gem).equals(1)) {
                    gemCountMap.remove(gem);
                } else {
                    Integer value = gemCountMap.get(gem);
                    gemCountMap.put(gem, value - 1);
                }

                st++;
            }

            if (st == en) {
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers67258_2 programmers672582 = new Programmers67258_2();
        int[] solution = programmers672582.solution(
                new String[]{"XYZ", "XYZ", "XYZ"}
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }

    }
}
