package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 단어 정렬
 * https://www.acmicpc.net/problem/1181
 */
public class BOJ1181 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            stringSet.add(br.readLine());
        }
        String[] arr = stringSet.toArray(String[]::new);
        Arrays.sort(arr, (s1, s2) -> {
            if (s1.length() != s2.length()) {
                return s1.length() - s2.length();
            }
            return s1.compareTo(s2);
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
