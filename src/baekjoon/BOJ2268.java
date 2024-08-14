package baekjoon;

import java.io.*;
import java.util.Arrays;

/**
 * 수들의 합 7
 */
public class BOJ2268 {

    public static int N;
    public static int M;
    public static int K;

    public static long setTree(long[] arr, int st, int en, int node, long[] tree) {
        if (st == en) {
            long result = arr[st];
            tree[node] = result;
            return result;
        }
        int mid = (st + en) / 2;
        long res1 = setTree(arr, st, mid, node * 2, tree);
        long res2 = setTree(arr, mid + 1, en, (node * 2) + 1, tree);

        tree[node] = res1 + res2;
        return res1 + res2;
    }

    public static long query(int from, int to, int st, int en, int node, long[] tree) {
        if (st == en) {
            if (from <= st && en <= to) {
                return tree[node];
            } else {
                return 0;
            }
        }
        if (from <= st && en <= to) {
            return tree[node];
        }
        if (en < from || to < st) {
            return 0;
        }
        int mid = (st + en) / 2;
        long res1 = query(from, to, st, mid, node * 2, tree);
        long res2 = query(from, to, mid + 1, en, (node * 2) + 1, tree);

        return res1 + res2;
    }

    private static void update(int idx, long c, int st, int en, int node, long[] tree) {
        if (st == en && st == idx) {
            long diff = c - tree[node];
            tree[node] = c;

            while (node != 1) {
                node = node / 2;
                tree[node] = tree[node] + diff;
            }
            return;
        }

        int mid = (st + en) / 2;
        if (idx <= mid) {
            update(idx, c, st, mid, node * 2, tree);
        } else {
            update(idx, c, mid + 1, en, (node * 2) + 1, tree);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        long[] arr = new long[N];
        long[] tree = new long[N * 4];
        setTree(arr, 0, arr.length - 1, 1, tree);
        for (int i = 0; i < M + K; i++) {
            long[] longSplit = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            long operator = longSplit[0];
            if (operator == 1) {
                int idx = (int)longSplit[1] - 1;
                long c = longSplit[2];
                update(idx, c, 0, arr.length - 1, 1, tree);
            } else if (operator == 0) {
                int from = (int)longSplit[1] - 1;
                int to = (int)longSplit[2] - 1;
                long sum;
                if (from < to) {
                    sum = query(from, to, 0, arr.length - 1, 1, tree);
                } else {
                    sum = query(to, from, 0, arr.length - 1, 1, tree);
                }
                bw.write(String.valueOf(sum));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
