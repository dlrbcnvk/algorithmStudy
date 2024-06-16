package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 나무 자르기
 * https://www.acmicpc.net/problem/2805
 * 나무의 높이의 합이 20억보다 클 수 있으므로 나무길이 연산 관련해서 long 타입을 고려해야 함.
 */
public class BOJ2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.valueOf(split[0]);
        long m = Long.valueOf(split[1]);

        int[] trees = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
        Arrays.sort(trees);

        long[] sumMetersAfterCut = new long[n];
        long sum = 0;
        sumMetersAfterCut[n-1] = 0;
        for (int i = trees.length - 2; i >= 0; i--) {
            sum += (long) (trees[i + 1] - trees[i]) * (trees.length - i - 1);
            sumMetersAfterCut[i] = sum;
        }


        int start = 0; // inclusive
        int end = n - 1; // exclusive
        while (start < end) {

            int mid = (start + end) / 2;
            long height = trees[mid];
            long value = sumMetersAfterCut[mid];
            if (m > value) {
                // 부족함
                end = mid;
            } else if (m < value) {
                // 높이를 올릴 여지가 있음.
                start = mid;

                if (start + 1 == end) {
                    break;
                }
            } else {
                // 딱 맞는 높이
                System.out.println(height);
                return;
            }
        }

        if (start == 0) {
            // 제일 낮은 나무보다도 밑으로 잘라야 함
            long totalLength = sumMetersAfterCut[0];
            long height = trees[start];
            int quantity = trees.length;
            height -= (m - totalLength) / quantity;
            if ((m - totalLength) % quantity != 0) {
                height--;
            }
            System.out.println(height);
            return;
        }

        int idx = start;
        long totalLength = sumMetersAfterCut[idx];
        long height = trees[idx];
        int quantity = trees.length - 1 - idx;
        height += (totalLength - m) / quantity;

        System.out.println(height);
    }
}
