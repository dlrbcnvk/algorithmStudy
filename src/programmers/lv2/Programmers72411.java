package programmers.lv2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 메뉴 리뉴얼
 * 미해결
 */
public class Programmers72411 {

    Map<Integer, List<String>> clientOrdersMap;
    Map<String, List<Integer>> menuClientMap;
    String[] menuList;
    boolean[] marked;
    ArrayList<String> resultList;
    Set<String> menuSet;


    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        clientOrdersMap = new HashMap<>();
        menuClientMap = new HashMap<>();


        int client = 1;
        for (String order : orders) {
            List<String> collect = Arrays.stream(order.split("")).collect(Collectors.toList());
            clientOrdersMap.put(client, collect);

            for (String menu : collect) {
                if (menuClientMap.containsKey(menu)) {
                    menuClientMap.get(menu).add(client);
                } else {
                    ArrayList<Integer> startArr = new ArrayList<>();
                    startArr.add(client);
                    menuClientMap.put(menu, startArr);
                }
            }
            client++;
        }

        menuSet = menuClientMap.keySet();
        menuList = menuSet.toArray(String[]::new);
        marked = new boolean[menuList.length];

        resultList = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
//            ArrayList<String> courses = makeCourse(course[i]);
//            resultList.addAll(courses);
        }

        Collections.sort(resultList);
        return resultList.toArray(String[]::new);
    }

//    public ArrayList<String> makeCourse(int count) {
//
//    }

    public void go(int idx, int count, String course) {
        if (course.length() == count) {
            resultList.add(course);
            return;
        }
        if (idx == menuList.length) {
            return;
        }

        for (int i = idx + 1; i < menuList.length; i++) {
            if (menuClientMap.get(menuList[i]).size() >= 2) {
                return;
            }

        }




    }

    public static void main(String[] args) {
        Programmers72411 programmers72411 = new Programmers72411();
        String[] solution = programmers72411.solution(
                new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
                new int[]{2, 3, 4}
        );
        System.out.println(solution);
    }
}
