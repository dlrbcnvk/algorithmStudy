package programmers.lv1;

/**
 * 삼총사
 * 완전탐색. 재귀
 */
public class Programmers131705 {

    int[] number;
    boolean[] marked;
    int result = 0;

    private void go(int idx, int count, int sum) {
        if (count == 3) {
            if (sum == 0) {
                this.result++;
            }
            return;
        }

        for (int i = idx + 1; i < number.length; i++) {
            if (!marked[i]) {
                marked[i] = true;
                go(i, count + 1, sum + number[i]);
                marked[i] = false;
            }
        }
    }

    public int solution(int[] number) {

        this.number = number;
        marked = new boolean[number.length];

        go(-1, 0, 0);
        return this.result;
    }

    public static void main(String[] args) {
        Programmers131705 programmers131705 = new Programmers131705();
        int solution = programmers131705.solution(
                new int[]{-3,-2,-1,0,1,2,3}
        );
        System.out.println(solution);
    }
}
