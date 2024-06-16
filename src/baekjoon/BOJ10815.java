package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 숫자 카드
 * https://www.acmicpc.net/problem/10815
 */
public class BOJ10815 {

    private static int N;
    private static int[] arr;
    private static String SPACE = " ";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.valueOf(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .sorted()
                .toArray();

        int M = Integer.valueOf(br.readLine());
        StringBuilder sb = new StringBuilder();
        Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .map(num -> binarySearch(num))
                .forEach(result -> {
                    sb.append(result);
                    sb.append(SPACE);
                });
        System.out.println(sb);
    }

    private static int binarySearch(int num) {
        int start = 0; // inclusive
        int end = arr.length; // exclusive
        while(start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] < num) {
                start = mid + 1;
            } else if (arr[mid] > num) {
                end = mid;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
