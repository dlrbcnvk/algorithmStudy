package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 수 찾기
 * https://www.acmicpc.net/problem/1920
 * 이분탐색
 */
public class BOJ1920 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        int[] testCases = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < testCases.length; i++) {
            int target = testCases[i];
            int st = 0; // inclusive
            int en = arr.length; // exclusive
            boolean isFound = false;
            while (st < en) {
                int mid = (st + en) / 2;
                if (arr[mid] == target) {
                    isFound = true;
                    break;
                } else if (arr[mid] < target) {
                    st = mid + 1;
                } else {
                    en = mid;
                }
            }

            if (isFound) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
