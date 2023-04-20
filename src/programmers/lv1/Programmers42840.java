package programmers.lv1;

import java.util.*;

/**
 * 모의고사
 */
public class Programmers42840 {

    public int[] solution(int[] answers) {

        int[] scores = new int[]{0, 0, 0};

        int[] checkingA = new int[]{1,2,3,4,5};
        int[] checkingB = new int[]{2,1,2,3,2,4,2,5};
        int[] checkingC = new int[]{3,3,1,1,2,2,4,4,5,5};
        int pointerA = 0;
        int pointerB = 0;
        int pointerC = 0;

        for (int answer : answers) {

            if (answer == checkingA[pointerA]) {
                scores[0]++;
            }
            if (answer == checkingB[pointerB]) {
                scores[1]++;
            }
            if (answer == checkingC[pointerC]) {
                scores[2]++;
            }

            pointerA = pointerA + 1 == checkingA.length ? 0 : pointerA + 1;
            pointerB = pointerB + 1 == checkingB.length ? 0 : pointerB + 1;
            pointerC = pointerC + 1 == checkingC.length ? 0 : pointerC + 1;
        }

        int maxScore = 0;
        for (int i = 0; i < 3; i++) {
            maxScore = Math.max(scores[i], maxScore);
        }

        ArrayList<Integer> maxScoreList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (scores[i] == maxScore) {
                maxScoreList.add(i + 1);
            }
        }

        return maxScoreList.stream().mapToInt(x->x).toArray();
    }

    public static void main(String[] args) {
        Programmers42840 programmers42840 = new Programmers42840();
        int[] solution = programmers42840.solution(new int[]{1,3,2,4,2});

        for (int s : solution) {
            System.out.print(s + " ");
        }
    }
}
