package programmers.lv2;

/**
 * 행렬의 곱셈
 * 기본적인 방법 O(n^3)
 */
public class Programmers12949 {
    public int[][] solution(int[][] arr1, int[][] arr2) {

        int m = arr1.length;
        int n = arr1[0].length;
        int k = arr2[0].length;

        int[][] result = new int[m][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                int sum = 0;
                for (int z = 0; z < n; z++) {
                    sum = sum + arr1[i][z] * arr2[z][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }


}
