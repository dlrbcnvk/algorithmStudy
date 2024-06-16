package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 단어 수학
 * 미해결
 */
public class BOJ1339 {

    private static String[] strArr;
    private static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Set<String> alphabetSet = new HashSet<>();
        strArr = new String[n];
        for (int i = 0; i < n; i++) {
            strArr[i] = br.readLine();
            String[] split = strArr[i].split(" ");
            for (String str : split) {
                alphabetSet.add(str);
            }
        }
        String[] alphabetArr = alphabetSet.toArray(String[]::new);
        boolean[] marked = new boolean[alphabetArr.length];
        int[] alphabetIdxValueIdxMap = new int[alphabetArr.length];


    }


}
