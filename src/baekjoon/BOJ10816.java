package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 숫자 카드 2
 * https://www.acmicpc.net/problem/10816
 * 이분탐색
 */
public class BOJ10816 {

    private static String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(arr);
        int m = Integer.parseInt(br.readLine());
        int[] tests = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int result = search(tests[i], arr);
            if (i < m - 1) {
                sb.append(result);
                sb.append(SPACE);
            } else {
                sb.append(result);
            }
        }
        System.out.println(sb);
    }

    private static int search(int target, int[] arr) {
        int st = 0; // inclusive
        int en = arr.length - 1; // inclusive
        int first, last;

        // 가장 앞쪽에 있는 target 찾기
        while (st < en) {
            int mid = (st + en) / 2;
            if (arr[mid] < target) {
                st = mid + 1;
            } else if (arr[mid] >= target) {
                en = mid;
            }
        }
        if (arr[en] != target) return 0;
        first = en;
//        System.out.println("target=" + target + ", 가장 앞쪽 target의 위치=" + en);

        // 가장 뒤쪽에 있는 target 찾기
        st = 0; // inclusive
        en = arr.length; // exclusive
        while (st < en) {
            int mid = (st + en) / 2;

            if (st == mid) break;

            if (arr[mid] < target) {
                st = mid + 1;
            } else if (arr[mid] > target) {
                en = mid;
            } else {
                st = mid;
            }
        }
        if (arr[st] != target) return 0;
        last = st;
//        System.out.println("target=" + target + ", 가장 뒤쪽 target의 위치=" + st);

        return last - first + 1;
    }
}
