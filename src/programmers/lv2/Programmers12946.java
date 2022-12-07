package programmers.lv2;

import java.util.ArrayList;

/**
 * 하노이의 탑
 * 재귀. 점화식
 * n=3 : 2개짜리 1->2, 제일 큰 거 1->3, 2개짜리 2->1
 * n=4 : 3(1->2), 1->3, 3(2->3)
 * n=5 : 4(1->2), 1->3, 4(2->3)
 */
public class Programmers12946 {

    ArrayList<int[]> arrayList;

    public int[][] solution(int n) {
        this.arrayList = new ArrayList<>();
        go(n, 1, 3, 2);
        int[][] answer = new int[arrayList.size()][2];
        int idx = 0;
        for (int[] result : arrayList) {
            answer[idx] = result;
            idx++;
        }
        return answer;
    }

    public void go(int n, int from, int to, int rest) {
        if (n == 2) {
            arrayList.add(new int[]{from, rest});
            arrayList.add(new int[]{from, to});
            arrayList.add(new int[]{rest, to});
            return;
        }
        go(n-1, from, rest, to);
        arrayList.add(new int[]{from, to});
        go(n - 1, rest, to, from);
    }

    public static void main(String[] args) {
        Programmers12946 programmers12946 = new Programmers12946();
        int[][] solution = programmers12946.solution(4);

        for (int[] way : solution) {
            System.out.print("[" + way[0] + "," + way[1] + "]");
            System.out.print(",");
        }
    }
}
