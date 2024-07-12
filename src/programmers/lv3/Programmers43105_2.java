package programmers.lv3;

/**
 * 정수 삼각형
 */
public class Programmers43105_2 {

    public int solution(int[][] triangle) {

        int n = triangle.length;
        int[][] accumulatedSumArr = new int[n][n];
        accumulatedSumArr[0][0] = triangle[0][0];
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    accumulatedSumArr[i][j] = accumulatedSumArr[i-1][j] + triangle[i][j];
                } else if (j == triangle[i].length - 1) {
                    accumulatedSumArr[i][j] = accumulatedSumArr[i-1][j-1] + triangle[i][j];
                } else {
                    accumulatedSumArr[i][j] = Math.max(accumulatedSumArr[i-1][j-1], accumulatedSumArr[i-1][j]) + triangle[i][j];
                }
            }
        }

        int max = 0;
        for (int i = 0; i < accumulatedSumArr[n - 1].length; i++) {
            max = Math.max(accumulatedSumArr[n - 1][i], max);
        }
        return max;
    }
    public static void main(String[] args) {
        Programmers43105_2 programmers431052 = new Programmers43105_2();
        int solution = programmers431052.solution(
                new int[][]{
                        {7},
                        {3,8},
                        {8,1,0},
                        {2,7,4,4},
                        {4,5,2,6,5}
                }
        );
        System.out.println(solution);
    }
}
