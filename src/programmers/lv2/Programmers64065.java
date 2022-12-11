package programmers.lv2;

import java.util.*;

/**
 * 튜플
 */
public class Programmers64065 {

    static class Number implements Comparable<Number> {
        int num;
        int count;

        public Number(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Number num) {
            if (this.count > num.count) {
                return -1;
            } else if (this.count < num.count) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int[] solution(String s) {

        s = s.replaceAll("[{}]", "");
        String[] split = s.split(",");

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < split.length; i++) {
            Integer num = Integer.valueOf(split[i]);
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        Set<Integer> set = map.keySet();
        Number[] numberList = new Number[set.size()];
        int idx = 0;
        for (Integer key : set) {
            Integer value = map.get(key);
            numberList[idx] = new Number(key, value);
            idx++;
        }
        Arrays.sort(numberList);
        int[] answer = new int[numberList.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = numberList[i].num;
        }


        return answer;
    }

    public static void main(String[] args) {
        Programmers64065 programmers64065 = new Programmers64065();
        int[] solution = programmers64065.solution(
                "{{1,2,3},{2,1},{1,2,14,3},{2}}"
        );

        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
    }
}
