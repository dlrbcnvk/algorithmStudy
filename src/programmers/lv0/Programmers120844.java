package programmers.lv0;


import java.util.ArrayList;

/**
 * 배열 회전시키기
 */
public class Programmers120844 {

    public int[] solution(int[] numbers, String direction) {
        ArrayList<Integer> integers = new ArrayList<>();

        if (direction.equals("right")) {
            integers.add(numbers[numbers.length - 1]);
            for (int i = 0; i < numbers.length - 1; i++) {
                integers.add(numbers[i]);
            }
        } else if (direction.equals("left")) {
            for (int i = 1; i < numbers.length; i++) {
                integers.add(numbers[i]);
            }
            integers.add(numbers[0]);
        }

        return integers.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Programmers120844 programmers120903 = new Programmers120844();
        int[] solution = programmers120903.solution(
                new int[]{4, 455, 6, 4, -1, 45, 6}, "left"
        );
        System.out.println(solution);
    }
}
