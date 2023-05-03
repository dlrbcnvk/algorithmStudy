package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 메뉴 리뉴얼
 * 2023.05.03 AC
 * 완전탐색
 */
public class Programmers72411 {

    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> resultList = new ArrayList<>();

        for (int i = 0; i < course.length; i++) {
            Map<String, Integer> courseCountMap = new HashMap<>();
            for (int j = 0; j < orders.length; j++) {
                String[] orderSplit = orders[j].split("");

                go(orderSplit, -1, course[i], new StringBuilder(), courseCountMap);
            }

            int maxCount = 2;
            ArrayList<String> semiResultList = new ArrayList<>();

            for (String key: courseCountMap.keySet()) {
                Integer count = courseCountMap.get(key);
                if (count == maxCount) {
                    semiResultList.add(key);
                } else if (count > maxCount) {
                    semiResultList.clear();
                    semiResultList.add(key);
                    maxCount = count;
                }
            }

            resultList.addAll(semiResultList);
        }

        String[] result = resultList.stream().toArray(String[]::new);
        Arrays.sort(result);
        return result;
    }

    public void go(String[] orderSplit, int idx, int courseCount, StringBuilder sb, Map<String, Integer> courseCountMap) {
        // 종료조건
        if (sb.length() == courseCount) {
            String course = sb.toString();
            String[] split = course.split("");
            Arrays.sort(split);
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : split) {
                stringBuilder.append(s);
            }
            course = stringBuilder.toString();
            if (courseCountMap.containsKey(course)) {
                Integer value = courseCountMap.get(course);
                courseCountMap.put(course, value + 1);
            } else {
                courseCountMap.put(course, 1);
            }
            return;
        }

        if (orderSplit.length < courseCount) return;

        for (int i = idx + 1; i < orderSplit.length; i++) {
                sb.append(orderSplit[i]);
                go(orderSplit, i, courseCount, sb, courseCountMap);
                sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Programmers72411 programmers72411 = new Programmers72411();
        String[] solution = programmers72411.solution(
                new String[]{"XYZ", "XWY", "WXA"},
//                new String[]{"ABCFG"},
                new int[]{2, 3, 4}
        );

        for (String s : solution) {
            System.out.print(s + " ");
        }
    }
}
