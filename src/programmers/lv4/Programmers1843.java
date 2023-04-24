package programmers.lv4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 사칙연산
 * 분할정복 메모이제이션 HashMap 사용 -> 효율성 시간초과
 * 배열 사용 -> 통과
 */
public class Programmers1843 {

    private int[][] maxMem = new int[402][402];
    private int[][] minMem = new int[402][402];

    public int solution(String arr[]) {

        for (int[] row : maxMem) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        for (int[] row : minMem) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        return getMax(0, arr.length - 1, arr);
    }

    public int getMax(int start, int end, String[] arr) {
        if (end - start == 0) {
            return Integer.parseInt(arr[start]);
        }

        if (maxMem[start][end] != Integer.MIN_VALUE) {
            return maxMem[start][end];
        }

        int result = Integer.MIN_VALUE;
        for (int i = start + 1; i < end; i += 2) {
            int value;
            int lhs = getMax(start, i-1, arr);
            int rhs;
            if (arr[i].equals("+")) {
                rhs = getMax(i + 1, end, arr);
                value = lhs + rhs;
            } else {
                rhs = getMin(i + 1, end, arr);
                value = lhs - rhs;
            }
            if (result < value) {
                result = value;
            }
        }

        maxMem[start][end] = result;
        return result;
    }

    public int getMin(int start, int end, String[] arr) {
        if (end - start == 0) {
            return Integer.parseInt(arr[start]);
        }

        if (minMem[start][end] != Integer.MAX_VALUE) {
            return minMem[start][end];
        }

        int result = Integer.MAX_VALUE;
        for (int i = start + 1; i < end; i += 2) {
            int value;
            int lhs = getMin(start, i-1, arr);
            int rhs;
            if (arr[i].equals("+")) {
                rhs = getMin(i + 1, end, arr);
                value = lhs + rhs;
            } else {
                rhs = getMax(i + 1, end, arr);
                value = lhs - rhs;
            }
            if (result > value) {
                result = value;
            }
        }
        minMem[start][end] = result;
        return result;
    }

    public static void main(String[] args) {
        Programmers1843 programmers1843 = new Programmers1843();

        int solution = programmers1843.solution(
                new String[]{"5", "-", "3", "+", "1", "+", "2", "-", "4"}
        );
        System.out.println(solution);
    }
}
