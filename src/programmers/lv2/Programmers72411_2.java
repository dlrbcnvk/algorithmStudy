package programmers.lv2;

import java.util.*;

/**
 * 메뉴 리뉴얼
 * https://school.programmers.co.kr/learn/courses/30/lessons/72411
 * 조합
 */
public class Programmers72411_2 {

    public String[] solution(String[] orders, int[] course) {

        List<String[]> orderList = new ArrayList<>();
        for (String order : orders) {
            String[] orderChars = order.split("");
            Arrays.sort(orderChars);
            orderList.add(orderChars);
        }

        List<String> courseMenusResult = new ArrayList<>();
        for (int courseUnit : course) {
            courseMenusResult.addAll(getCourseMenus(orderList, courseUnit));
        }

        String[] result = new String[courseMenusResult.size()];
        int resultIdx = 0;
        for (String menu : courseMenusResult) {
            result[resultIdx] = menu;
            resultIdx++;
        }
        Arrays.sort(result);
        return result;
    }

    private List<String> getCourseMenus(List<String[]> orderList, int courseUnit) {
        Map<String, Integer> courseCountMap = new HashMap<>();
        for (String[] order : orderList) {
            boolean[] orderMarked = new boolean[order.length];
            if (order.length < courseUnit) {

            } else if (order.length == courseUnit) {
                String menuCombination = String.join("", order);
                if (courseCountMap.containsKey(menuCombination)) {
                    int value = courseCountMap.get(menuCombination);
                    courseCountMap.put(menuCombination, value + 1);
                } else {
                    courseCountMap.put(menuCombination, 1);
                }
            } else {
                getMenuCombination(0, "", order, orderMarked, courseUnit, courseCountMap);
            }
        }

        List<String> courseMenus = new ArrayList<>();
        Optional<Integer> maxOptional = courseCountMap.values().stream().max(Integer::compareTo);
        if (maxOptional.isEmpty()) {
            return new ArrayList<>();
        }
        int max = maxOptional.get();
        if (max <= 1) {
            return new ArrayList<>();
        }

        for (String course : courseCountMap.keySet()) {
            if (courseCountMap.get(course) == max) {
                courseMenus.add(course);
            }
        }
        return courseMenus;
    }

    private void getMenuCombination(int startIdx, String orderCombination, String[] order, boolean[] orderMarked, int courseUnit, Map<String, Integer> courseCountMap) {

        if (orderCombination.length() == courseUnit) {
            if (courseCountMap.containsKey(orderCombination)) {
                int count = courseCountMap.get(orderCombination);
                courseCountMap.put(orderCombination, count + 1);
            } else {
                courseCountMap.put(orderCombination, 1);
            }
            return;
        }

        for (int i = startIdx; i < order.length; i++) {
            if (!orderMarked[i]) {
                orderMarked[i] = true;
                getMenuCombination(i + 1,orderCombination + order[i], order, orderMarked, courseUnit, courseCountMap);
                orderMarked[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Programmers72411_2 programmers724112 = new Programmers72411_2();
        String[] solution = programmers724112.solution(
                new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
                new int[]{2, 3, 4}
        );

        for (String str : solution) {
            System.out.print(str + " ");
        }
    }
}
