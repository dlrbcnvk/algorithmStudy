package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 에바쿰
 * https://www.acmicpc.net/problem/15967
 */
public class BOJ15967 {

    private static int N;
    private static int Q1;
    private static int Q2;

    private static long[] arr;
    private static long[] tree;
    private static long[] lazy;

    public static void createTree(int st, int en, int node) {

        if (st == en) {
            tree[node] = arr[st];
            return;
        }

        int mid = (st + en) / 2;
        createTree(st, mid, node * 2);
        createTree(mid + 1, en, node * 2 + 1);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        Q1 = split[1];
        Q2 = split[2];
        arr = new long[N];
        tree = new long[N * 4];
        lazy = new long[N * 4];
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        createTree(0, arr.length - 1, 1);

        for (int i = 0; i < Q1 + Q2; i++) {
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int operator = split[0];
            if (operator == 1) {
                int n = split[1] - 1;
                int m = split[2] - 1;
                long result = query(0, arr.length - 1, 1, n, m);
                System.out.println(result);
            } else if (operator == 2) {
                int s = split[1] - 1;
                int e = split[2] - 1;
                int l = split[3];
                update(0, arr.length - 1, 1, s, e, l);
            }
        }
    }

    private static void update(int st, int en, int node, int from, int to, int value) {
        update_lazy(st, en, node);

        if (en < from || to < st) return;

        if (from <= st && en <= to) {
            tree[node] += ((long)(en - st + 1)) * value;
            if (st != en) {
                lazy[node * 2] += value;
                lazy[node * 2 + 1] += value;
            }
            return;
        }

        int mid = (st + en) / 2;
        update(st, mid, node * 2, from, to, value);
        update(mid + 1, en, node * 2 + 1, from, to, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static void update_lazy(int st, int en, int node) {
        if (lazy[node] != 0) {
            tree[node] += ((long)(en - st + 1)) * lazy[node];
            if (st != en) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    private static long query(int st, int en, int node, int from, int to) {
        update_lazy(st, en, node);

        if (en < from || to < st) return 0;

        if (from <= st && en <= to) return tree[node];

        int mid = (st + en) / 2;
        return query(st, mid, node * 2, from, to) +
                query(mid + 1, en, node * 2 + 1, from, to);
    }
}
