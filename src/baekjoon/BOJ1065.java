package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 한수
 * https://www.acmicpc.net/problem/1065
 */
public class BOJ1065 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] hansuArr = new boolean[1001];
        for (int i = 1; i < 100; i++) hansuArr[i] = true;

        for (int i = 101; i < 1000; i++) hansuArr[i] = hansu(i);

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (hansuArr[i]) count++;
        }
        System.out.println(count);
    }

    private static boolean hansu(int num) {
        int[] arr = Arrays.stream(String.valueOf(num).split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        return arr[1] - arr[0] == arr[2] - arr[1];
    }
}
