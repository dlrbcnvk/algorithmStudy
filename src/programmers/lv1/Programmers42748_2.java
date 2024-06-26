package programmers.lv1;

import java.util.ArrayList;
import java.util.List;

/**
 * K번째 수
 * https://school.programmers.co.kr/learn/courses/30/lessons/42748
 */
public class Programmers42748_2 {

    public int[] solution(int[] array, int[][] commands) {

        int[] answer = new int[commands.length];
        for (int x = 0; x < commands.length; x++) {
            int[] command = commands[x];
            int i = command[0];
            int j = command[1];
            int k = command[2];
            List<Integer> subList = new ArrayList<>();
            for (int y = i - 1; y <= j - 1; y++) {
                subList.add(array[y]);
            }
            subList.sort(Integer::compareTo);
            answer[x] = subList.get(k - 1);
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers42748_2 programmers427482 = new Programmers42748_2();
        int[] solution = programmers427482.solution(
                new int[]{1,5,2,6,3,7,4},
                new int[][]{
                        {2,5,3},
                        {4,4,1},
                        {1,7,3}
                }
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
