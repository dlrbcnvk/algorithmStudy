package programmers.lv2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [3차] 압축
 * 두포인터..?!
 */
public class Programmers17684 {

    HashMap<String, Integer> map;
    Integer nextValue = 27;

    public int[] solution(String msg) {

        map = new HashMap<>();
        setMap();
        char[] chars = msg.toCharArray();
        ArrayList<Integer> result = new ArrayList<>();
        int idx = 0;
        while (idx < chars.length) {
            String current = chars[idx] + "";
            String next = chars[idx] + "";

            while (map.containsKey(next)) {
                current = next;
                if (idx < chars.length - 1) {
                    next += chars[++idx];
                } else if (idx == chars.length - 1) {
                    result.add(map.get(next));
                    idx++;
                    break;
                }
            }

            if (idx <= chars.length - 1) {
                map.put(next, nextValue);
                nextValue++;
                result.add(map.get(current));
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private void setMap() {
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        map.put("E", 5);
        map.put("F", 6);
        map.put("G", 7);
        map.put("H", 8);
        map.put("I", 9);
        map.put("J", 10);
        map.put("K", 11);
        map.put("L", 12);
        map.put("M", 13);
        map.put("N", 14);
        map.put("O", 15);
        map.put("P", 16);
        map.put("Q", 17);
        map.put("R", 18);
        map.put("S", 19);
        map.put("T", 20);
        map.put("U", 21);
        map.put("V", 22);
        map.put("W", 23);
        map.put("X", 24);
        map.put("Y", 25);
        map.put("Z", 26);
    }

    public static void main(String[] args) {
        Programmers17684 programmers17684 = new Programmers17684();
        int[] solution = programmers17684.solution("KAKAO");

        for (int num : solution) {
            System.out.print(num + " ");
        }
    }
}
