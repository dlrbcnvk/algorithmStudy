package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 두 용액
 * https://www.acmicpc.net/problem/2470
 * 미해결
 */
public class BOJ2470 {

    private static int N;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();


    }

    private static int min(int a, int b) {
        return Math.min(a, b);
    }
}
