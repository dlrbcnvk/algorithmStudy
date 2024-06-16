package programmers.lv2;

import java.util.Arrays;

/**
 * 테이블 해시 함수
 * https://school.programmers.co.kr/learn/courses/30/lessons/147354
 */
public class Programmers147354 {

    public int solution(int[][] data, int col, int row_begin, int row_end) {

        col--;
        row_begin--;
        row_end--;

        int finalCol = col;

        Arrays.sort(data, (d1, d2) -> {
            if (d1[finalCol] != d2[finalCol]) {
                return d1[finalCol] - d2[finalCol];
            } else {
                return d2[0] - d1[0];
            }
        });

        int[] modSumArr = new int[row_end - row_begin + 1];
        for (int i = row_begin; i <= row_end; i++) {
            int sum = 0;
            for (int j = 0; j < data[0].length; j++) {
                sum += data[i][j] % (i + 1);
            }
            modSumArr[i - row_begin] = sum;
        }

        int bitwise = 0;
        for (int i = 1; i < modSumArr.length; i++) {
            bitwise = modSumArr[i - 1] ^ modSumArr[i];
            modSumArr[i] = bitwise;
        }

        return bitwise;
    }

    public static void main(String[] args) {
        Programmers147354 programmers147354 = new Programmers147354();
        int solution = programmers147354.solution(
                new int[][]{{2,2,6},{1,5,10},{4,2,9},{3,8,3}}, 2, 2, 3
        );
        System.out.println(solution);
    }
}
