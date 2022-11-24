package programmers.lv2;

/**
 * 땅따먹기
 * 완전탐색 재귀 - 시간초과
 * 미해결
 */
public class Programmers12913 {

    int[][] land;
    int n;
    int maxPoint = 0;

    /**
     *
     * @param idx : current idx
     * @param row : current row
     * @param point : point until now
     */
    public void go(int idx, int row, int point) {
        if (row == n - 1) {
            if (point > maxPoint) {
                maxPoint = point;
                return;
            }
            return;
        }

        int firstIdx = 0;
        int firstVal = 0;
        int secondIdx = 0;
        int secondVal = 0;
        for (int i = 0; i < 4; i++) {
            if (idx == i) {
                continue;
            }
            if (land[row + 1][i] > firstVal) {
                firstVal = land[row + 1][i];
                firstIdx = i;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (idx == i || idx == firstIdx) {
                continue;
            }
            if (land[row + 1][i] > secondVal) {
                secondVal = land[row + 1][i];
                secondIdx = i;
            }
        }
        go(firstIdx, row + 1, point + firstVal);
        go(secondIdx, row + 1, point + secondVal);
    }

    int solution(int[][] land) {

        this.n = land.length;

        this.land = land;
        for (int i = 0; i < 4; i++) {
            go(i, 0, land[0][i]);
        }

        return maxPoint;
    }

    public static void main(String[] args) {
        Programmers12913 programmers12913 = new Programmers12913();
        int solution = programmers12913.solution(
                new int[][]{{1,2,3,5},{5,6,7,8},{4,3,2,1}}
        );
        System.out.println(solution);
    }
}
