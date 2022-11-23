package programmers.lv2;

/**
 * 피로도
 * 완전탐색 재귀
 */
public class Programmers87946 {

    int[][] dun;
    int result = 0;
    boolean[] marked;
    int n;

    public int go(int rest, int count) {
        int maxValue = count;

        if (count == n) {
            return count;
        }

        for (int i = 0; i < n; i++) {
            if (!marked[i]) {
                if (rest >= dun[i][0]) {
                    marked[i] = true;
                    int value = go(rest - dun[i][1], count + 1);
                    maxValue = Math.max(maxValue, value);
                    marked[i] = false;
                }
            }
        }

        return maxValue;
    }

    public int solution(int k, int[][] dungeons) {

        n = dungeons.length;
        marked = new boolean[n];
        dun = dungeons;

        for (int i = 0; i < n; i++) {
            if (k >= dun[i][0]) {
                marked[i] = true;
                int count = go(k - dun[i][1], 1);
                if (result < count) {
                    result = count;
                }
                marked[i] = false;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Programmers87946 programmers87946 = new Programmers87946();
        int solution = programmers87946.solution(
                60,
                new int[][]{{80,20},{50,40},{30,10}}
        );
        System.out.println(solution);
    }
}
