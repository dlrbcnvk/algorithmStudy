package programmers.lv1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 명예의 전당 (1)
 */
public class Programmers138477 {

    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];

        ArrayList<Integer> arr = new ArrayList<>();
        int day = 0;
        while (day < score.length) {
            arr.add(score[day]);
            arr.sort(Collections.reverseOrder());

            if (day < k) {
                answer[day] = arr.get(day);
            } else {
                answer[day] = arr.get(k - 1);
            }

            day++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers138477 programmers138477 = new Programmers138477();
        int[] solution = programmers138477.solution(
                3, new int[]{10, 100, 20, 150, 1, 100, 200}
        );

        for (int num : solution) {
            System.out.print(num + " ");
        }
    }
}
