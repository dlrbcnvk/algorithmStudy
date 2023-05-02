package programmers.lv2;

import java.util.Arrays;

/**
 * 연속된 부분 수열의 합
 * 두 포인터
 */
public class Programmers178870 {

    private static int minDist = Integer.MAX_VALUE;
    private static int minDistIdx = Integer.MAX_VALUE;


    public int[] solution(int[] sequence, int k) {

        int idx1 = 0;
        int idx2 = 1;
        int sum = sequence[idx1];
        int[] answer = {};

        while (idx1 < sequence.length) {

            if (idx2 == sequence.length && sum < k) {
                break;
            }

            if (sum == k) {
                if (idx2 - idx1 < minDist) {
                    // 길이가 짧은 수열 발견
                    answer = new int[]{idx1, idx2 - 1};
                } else if (idx2 - idx1 == minDist && idx1 < minDistIdx) {
                    // 길이가 짧은 수열이 여러 개인 경우 앞쪽에 나오는 수열 찾기
                    answer = new int[]{idx1, idx2 - 1};
                }

                minDist = idx2 - idx1;
                minDistIdx = idx1;

//                for (int i : answer) {
//                    System.out.print(i + " ");
//                }
//                System.out.println();

                sum -= sequence[idx1];
                idx1++;
            } else {
                if (sum < k) {
                    sum += sequence[idx2];
                    idx2++;
                } else {
                    sum -= sequence[idx1];
                    idx1++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers178870 programmers178870 = new Programmers178870();
        int[] solution = programmers178870.solution(
                new int[]{2,2,2,2,2}, 6
                );

        System.out.println();
        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
