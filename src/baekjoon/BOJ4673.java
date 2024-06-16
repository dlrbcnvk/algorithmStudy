package baekjoon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 셀프 넘버
 * https://www.acmicpc.net/problem/4673
 */
public class BOJ4673 {

    public static void main(String[] args) {
        int[] arr = new int[10001];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != 0) continue;
            int num = i;
            while (num <= 10000) {
                int plus = plus(num); // plus 의 생성자 num
//                System.out.println(num + "+" + plus);
                if (plus > 10000) break;
                if (arr[plus] != 0) break;
                arr[plus] = num;
                num = plus;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                sb.append(i);
                sb.append("\n");
            }
        }
        System.out.println(sb);

    }

    private static int plus(int num) {
        return num + Arrays.stream(String.valueOf(num).split(""))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
