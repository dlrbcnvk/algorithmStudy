package programmers.lv1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * K번째수
 */
public class Programmers42748 {

    public int[] solution(int[] array, int[][] commands) {

        int[] answer = new int[commands.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = operate(array, commands[i]);
        }

        return answer;
    }

    public int operate(int[] array, int[] command) {
        int i = command[0];
        int j = command[1];
        int k = command[2];

        ArrayList<Integer> list = new ArrayList<>();
        for (int p = i - 1; p <= j - 1; p++) {
            list.add(array[p]);
        }

        Collections.sort(list);
        return list.get(k - 1);
    }


    public static void main(String[] args) {
        Programmers42748 programmers42748 = new Programmers42748();
        int[] solution = programmers42748.solution(
                new int[]{1, 5, 2, 6, 3, 7, 4},
                new int[][]{{2,5,3}, {4,4,1}, {1,7,3}}
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
