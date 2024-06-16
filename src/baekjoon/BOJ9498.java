package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시험 성적
 * https://www.acmicpc.net/problem/9498
 */
public class BOJ9498 {

    private static String A = "A";
    private static String B = "B";
    private static String C = "C";
    private static String D = "D";
    private static String F = "F";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n >= 90 && n <= 100) {
            System.out.println(A);
        } else if (n >= 80 && n <= 89) {
            System.out.println(B);
        } else if (n >= 70 && n <= 79) {
            System.out.println(C);
        } else if (n >= 60 && n <= 69) {
            System.out.println(D);
        } else {
            System.out.println(F);
        }
    }
}
