package programmers.lv2;

import java.util.ArrayList;
import java.util.List;

public class Programmers12946_2 {
    public int[][] solution(int n) {
        List<Integer[]> procedures = new ArrayList<>();
        hanoi(procedures, n, 1, 3, 2);
        int[][] answer = new int[procedures.size()][2];
        int idx = 0;
        for (Integer[] procedure : procedures) {
            answer[idx] = new int[]{procedure[0], procedure[1]};
            idx++;
        }
        return answer;
    }

    public void hanoi(List<Integer[]> procedures, int n, int start, int end, int other) {
        if (n == 1) {
            procedures.add(new Integer[]{start, end});
            return;
        }
        hanoi(procedures, n-1, start, other, end);
        procedures.add(new Integer[]{start, end});
        hanoi(procedures, n-1, other, end, start);
    }

    public static void main(String[] args) {
        Programmers12946_2 programmers129462 = new Programmers12946_2();
        int[][] solution = programmers129462.solution(3);

        for (int i = 0; i < solution.length; i++) {
            System.out.println("[" + solution[i][0] + "," + solution[i][1] + "]");
        }
    }
}
