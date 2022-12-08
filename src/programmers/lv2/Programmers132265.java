package programmers.lv2;

import java.util.HashMap;

/**
 * 롤케이크 자르기
 * map 사용
 * arraylist 써야되나 set을 써야하나 잠시 고민했다가 map 사용하는 것이 낫다고 판단
 */
public class Programmers132265 {

    public int solution(int[] topping) {
        int answer = 0;

        HashMap<Integer, Integer> firstMap = new HashMap<>();
        HashMap<Integer, Integer> secondMap = new HashMap<>();

        for (int i = 0; i < topping.length; i++) {
            if (!secondMap.containsKey(topping[i])) {
                secondMap.put(topping[i], 1);
            } else {
                Integer integer = secondMap.get(topping[i]);
                secondMap.put(topping[i], integer + 1);
            }
        }

        for (int i = 0; i < topping.length; i++) {
            Integer item = topping[i];
            if (!firstMap.containsKey(item)) {
                firstMap.put(item, 1);
            } else {
                firstMap.put(item, firstMap.get(item) + 1);
            }

            if (secondMap.get(item) == 1) {
                secondMap.remove(item);
            } else {
                secondMap.put(item, secondMap.get(item) - 1);
            }

            if (firstMap.keySet().size() == secondMap.keySet().size()) {
                answer++;
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers132265 programmers132265 = new Programmers132265();
        int solution = programmers132265.solution(new int[]{1,2,3,1,4});
        System.out.println(solution);
    }
}
