package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 수열과 쿼리 26
 *
 * 미해결
 */
public class BOJ17474 {

    private static int N;
    private static int M;
    private static int[] arr;
    private static Node[] tree;
    private static int[] lazy;

    static class Node {
        int idx;
        long sum;
        int max;
        int min;

        public Node(int idx, long sum, int max, int min) {
            super();
            this.idx = idx;
            this.sum = sum;
            this.max = max;
            this.min = min;
        }
    }


    public static void setTree(int st, int en, int idx) {
        if (st == en) {
            tree[idx] = new Node(idx, arr[st], arr[st], arr[st]);
            return;
        }

        int mid = (st + en) / 2;
        setTree(st, mid, idx * 2);
        setTree(mid + 1, en, idx * 2 + 1);
        Node left = tree[idx * 2];
        Node right = tree[idx * 2 + 1];
        tree[idx] = new Node(idx, left.sum + right.sum, Math.max(left.max, right.max), Math.min(left.min, right.min));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        tree = new Node[N * 4];
        setTree(0, arr.length - 1, 1);
        lazy = new int[N * 4];
        Arrays.fill(lazy, -1);
        for (int i=0; i<M; i++) {
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int operator = split[0];
            int from = split[1] - 1;
            int to = split[2] - 1;
            if (operator == 1) {
                int value = split[3];
                update(0, arr.length - 1, 1, from, to, value);
            } else if (operator == 2) {
                // max
                Node result = query(0, arr.length - 1, 1, from, to);
                bw.write(String.valueOf(result.max));
                bw.newLine();
            } else if (operator == 3) {
                // sum
                Node result = query(0, arr.length - 1, 1, from, to);
                bw.write(String.valueOf(result.sum));
                bw.newLine();
            }
        }
        bw.flush();
    }


    private static Node query(int st, int en, int idx, int from, int to) {
        update_lazy(st, en, idx);

        if (en < from || to < st) return null;

        if (from <= st && en <= to) return tree[idx];

        int mid = (st + en) / 2;
        Node left = query(st, mid, idx * 2, from, to);
        Node right = query(mid + 1, en, idx * 2 + 1, from, to);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        long sum = left.sum + right.sum;
        int max = Math.max(left.max, right.max);
        int min = Math.min(left.min, right.min);
        return new Node(idx, sum ,max, min);
    }


    private static void update(int st, int en, int idx, int from, int to, int value) {
        update_lazy(st, en, idx);

        if (en < from || to < st) return;

        if (from <= st && en <= to) {
            tree[idx].max = Math.min(tree[idx].max, value);
            if (st != en) {
                lazy[idx * 2] = value;
                lazy[idx * 2 + 1] = value;
            }
            if (tree[idx].min > value) {
                tree[idx].sum = ((long) (en - st + 1)) * ((long)value);
                tree[idx].min = value;
            }
            if (tree[idx].max <= value) {
                return;
            }
        }

        int mid = (st + en) / 2;
        update(st, mid, idx * 2, from, to, value);
        update(mid + 1, en, idx * 2 + 1, from, to, value);
        Node left = tree[idx * 2];
        Node right = tree[idx * 2 + 1];
        if (left == null || right == null) {
            return;
        }
        tree[idx].sum = left.sum + right.sum;
        tree[idx].max = Math.max(left.max, right.max);
        tree[idx].min = Math.min(left.min, right.min);
    }

    private static void update_lazy(int st, int en, int idx) {
        if (lazy[idx] != -1) {
            if (st != en) {
                lazy[idx * 2] = lazy[idx];
                lazy[idx * 2 + 1] = lazy[idx];
            } else {
                tree[idx].max = Math.min(tree[idx].max, lazy[idx]);
            }
            if (tree[idx].min > lazy[idx]) {
                tree[idx].sum = ((long) (en - st + 1)) * ((long)lazy[idx]);
                tree[idx].min = lazy[idx];
            }
            lazy[idx] = -1;
        }
    }
}

