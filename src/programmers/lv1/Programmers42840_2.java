package programmers.lv1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 모의고사
 * https://school.programmers.co.kr/learn/courses/30/lessons/42840
 */
public class Programmers42840_2 {

    public int[] solution(int[] answers) {
        int[] score = new int[]{0, 0, 0};
        int[] pattern1 = new int[]{1, 2, 3, 4, 5};
        int[] pattern2 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern3 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int idx1 = 0;
        int idx2 = 0;
        int idx3 = 0;
        for (int i = 0; i < answers.length; i++) {
            int answer = answers[i];
            if (answer == pattern1[idx1]) {
                score[0]++;
            }
            if (answer == pattern2[idx2]) {
                score[1]++;
            }
            if (answer == pattern3[idx3]) {
                score[2]++;
            }
            idx1 = idx1 == pattern1.length - 1 ? 0 : idx1 + 1;
            idx2 = idx2 == pattern2.length - 1 ? 0 : idx2 + 1;
            idx3 = idx3 == pattern3.length - 1 ? 0 : idx3 + 1;
        }

        int maxScore = score[0];
        maxScore = Math.max(score[1], maxScore);
        maxScore = Math.max(score[2], maxScore);
        List<Integer> winnerList = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            if (score[i] == maxScore) {
                winnerList.add(i + 1);
            }
        }
        winnerList = winnerList.stream().sorted().collect(Collectors.toList());

        int[] result = new int[winnerList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = winnerList.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Programmers42840_2 programmers428402 = new Programmers42840_2();
        int[] solution = programmers428402.solution(new int[]{1,3,2,4,2});

        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
    }
}
