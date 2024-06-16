package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * ATM
 * https://www.acmicpc.net/problem/11399
 */
public class BOJ11399 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(arr);
        int[] times = new int[arr.length];
        int sum = arr[0];
        times[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            times[i] = times[i - 1] + arr[i];
            sum += times[i];
        }
        System.out.println(sum);
    }
}
