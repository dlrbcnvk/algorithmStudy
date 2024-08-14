package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

/**
 * 성대나라의 물탱크
 */
public class BOK18227 {

    private static int N;
    private static int Q;

    static class Node {
        int idx;
        int level;
        long water;

        public Node(int idx, int level, long water) {
            this.idx = idx;
            this.level = level;
            this.water = water;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        Q = split[1];

    }
}
