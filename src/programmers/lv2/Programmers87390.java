package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * n^2 배열 자르기
 * 런타임 에러..? 반례 4,7,15 (맨 끝) -> 해결
 */
public class Programmers87390 {
    public int[] solution(int n, long left, long right) {

        right += 1;
        int leftTurn = (int)(left / n);
        int rightTurn = (int)(right / n);
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            if (i <= leftTurn) {
                arr[i] = (leftTurn + 1);
            } else {
                arr[i] = arr[i-1] == n ? n : arr[i-1] + 1;
            }
        }

        if (leftTurn == rightTurn) {
            int leftIdx = (int)(left % n);
            int rightIdx = (int)(right % n);
            return Arrays.copyOfRange(arr, leftIdx, rightIdx);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i : arr) {
            result.add(i);
        }
        int leftIdx = (int)(left % n);

        for (int i = leftTurn + 1; i <= rightTurn; i++) {
            int[] clone = arr.clone();
            for (int j = 0; j < i; j++) {
                clone[j] = i + 1;
            }
            for (int a : clone) {
                result.add(a);
            }
            arr = clone;

            if (i == rightTurn) {
                int howManyCut = result.size() - (int)(right - (leftTurn) * n) - 1;
                for (int j = 0; j < howManyCut; j++) {
                    int size = result.size();
                    result.remove(size - 1);
                }
            }
        }

//        int[] answer = result.subList(leftIdx, (int) (right - (leftTurn) * n)).stream().mapToInt(i -> i).toArray();
        int[] answer = result.subList(leftIdx, result.size() - 1).stream().mapToInt(i -> i).toArray();

        return answer;
    }

    public static void main(String[] args) {
        Programmers87390 programmers87390 = new Programmers87390();
        int[] solution = programmers87390.solution(
                4, 7, 15
        );
        System.out.println();
        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
