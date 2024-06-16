package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 듣보잡
 * https://www.acmicpc.net/problem/1764
 */
public class BOJ1764 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        Set<String> dSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            dSet.add(br.readLine());
        }
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String person = br.readLine();
            if (dSet.contains(person)) {
                resultList.add(person);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(resultList.size());
        sb.append("\n");
        resultList = resultList.stream().sorted().collect(Collectors.toList());
        for (String str : resultList) {
            sb.append(str);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
