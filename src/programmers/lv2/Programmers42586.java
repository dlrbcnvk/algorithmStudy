package programmers.lv2;

import java.util.ArrayList;
import java.util.List;

/**
 * 기능 개발
 */
public class Programmers42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();

        int idx = 0;
        while (idx < progresses.length) {
            int restPoint = 100 - progresses[idx];
            int fewDaysLater;
            if (restPoint % speeds[idx] == 0) {
                fewDaysLater = restPoint / speeds[idx];
            } else {
                fewDaysLater = restPoint / speeds[idx] + 1;
            }

            for (int i = idx; i < progresses.length; i++) {
                progresses[i] += speeds[i] * fewDaysLater;
            }
            int count = 1;
            for (int i = idx + 1; i < progresses.length; i++) {
                if (progresses[i] >= 100) {
                    count++;
                } else {
                    break;
                }
            }
            list.add(count);
            idx += count;
        }

        int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    public static void main(String[] args) {
        Programmers42586 programmers42586 = new Programmers42586();
        int[] solution = programmers42586.solution(
                new int[]{95, 90, 99, 99, 80, 99},
                new int[]{1, 1, 1, 1, 1, 1}
        );

        for (int n : solution) {
            System.out.print(n + " ");
        }
    }
}
