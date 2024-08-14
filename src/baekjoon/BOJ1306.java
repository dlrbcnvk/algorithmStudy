package baekjoon;

import java.io.*;
import java.util.Arrays;

/**
 * 달려라 홍준
 */
public class BOJ1306 {

    private static int N;
    private static int M;
    private static long[] arr;
    private static long[] tree;

    public static void setTree(int st, int en, int idx) {

        if (st == en) {
            tree[idx] = arr[st];
            return;
        }

        int mid = (st + en) / 2;
        setTree(st, mid, idx * 2);
        setTree(mid + 1, en, idx * 2 + 1);
        tree[idx] = Math.max(tree[idx * 2], tree[idx * 2 + 1]);
    }

    public static long query(int st, int en, int idx, int from, int to) {

        if (en < from || to < st) return 0;

        if (from <= st && en <= to) {
            return tree[idx];
        }

        int mid = (st + en) / 2;
        return Math.max(query(st, mid, idx * 2, from, to),
                query(mid + 1, en, idx * 2 + 1, from, to));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        tree = new long[N * 4];
        setTree(0, arr.length - 1, 1);
        for (int i = M - 1; i < N - M + 1; i++) {
            long result = query(0, arr.length - 1, 1, i - M + 1, i + M - 1);
            bw.write(String.valueOf(result));
            bw.write(" ");
        }
        bw.flush();
    }
}
