package programmers.lv2;

/**
 * 행렬의 곱셈
 * https://school.programmers.co.kr/learn/courses/30/lessons/12949
 */
public class Programmers12949_2 {

    public int[][] solution(int[][] arr1, int[][] arr2) {

        int A = arr1.length;
        int B = arr1[0].length;
        int D = arr2[0].length;
        int[][] result = new int[A][D];

        for (int i = 0; i < A; i++) {
            for (int j = 0; j < D; j++) {
                int value = 0;
                for (int k = 0; k < B; k++) {
                    value += arr1[i][k] * arr2[k][j];
                }
                result[i][j] = value;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Programmers12949_2 programmers129492 = new Programmers12949_2();
        int[][] solution = programmers129492.solution(
                new int[][]{
                        {2,3,2},
                        {4,2,4},
                        {3,1,4}
                },
                new int[][]{
                        {5,4,3},
                        {2,4,1},
                        {3,1,1}
                }
        );

        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[0].length; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }
}
