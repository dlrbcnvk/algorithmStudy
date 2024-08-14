package baekjoon;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 구간 곱 구하기
 *
 * 미해결
 */
public class BOJ11505 {

    public static int N;
    public static int M;
    public static int K;

    public static long setTree(long[] arr, int st, int en, int node, double[] tree) {
        if (st == en) {
            long result = arr[st];
            tree[node] = result;
            return result;
        }
        int mid = (st + en) / 2;
        long res1 = setTree(arr, st, mid, node * 2, tree);
        long res2 = setTree(arr, mid + 1, en, (node * 2) + 1, tree);

        long newValue = (res1 * res2) % 1_000_000_007;
        tree[node] = newValue;
        return newValue;
    }

    public static double getSegmentSum(int from, int to, int st, int en, int node, double[] tree) {
        if (st == en) {
            if (from <= st && en <= to) {
                return tree[node];
            } else {
                return -1;
            }
        }
        if (from <= st && en <= to) {
            return tree[node];
        }
        if (en < from || to < st) {
            return -1;
        }
        int mid = (st + en) / 2;
        double res1 = getSegmentSum(from, to, st, mid, node * 2, tree);
        double res2 = getSegmentSum(from, to, mid + 1, en, (node * 2) + 1, tree);
        if (res1 == -1) {
            return res2;
        }
        if (res2 == -1) {
            return res1;
        }
        return (res1 * res2) % 1_000_000_007;
    }

    private static void switchNode(int idx, long c, int st, int en, int node, double[] tree) {
        if (st == en && st == idx) {
            double curValue = c;
            double preValue = tree[node];
            tree[node] = c;

            while (node != 1) {
                node = node / 2;
                if (preValue == 0) {
                    int mid = (st + en) / 2;
                    double res1 = getSegmentSum(st, mid, st, mid, node * 2, tree);
                    double res2 = getSegmentSum(mid + 1, en, mid + 1, en, (node * 2) + 1, tree);
                    if (res1 != 0) {
                        tree[node] = (res1 * curValue) % 1_000_000_007;
                    } else if (res2 != 0) {
                        tree[node] = (res2 * curValue) % 1_000_000_007;
                    }
                } else {
                    tree[node] = tree[node] / preValue;
                    tree[node] = (tree[node] * curValue) % 1_000_000_007;
                }
                preValue = curValue;
                curValue = tree[node];
            }
            return;
        }

        int mid = (st + en) / 2;
        if (idx <= mid) {
            switchNode(idx, c, st, mid, node * 2, tree);
        } else {
            switchNode(idx, c, mid + 1, en, (node * 2) + 1, tree);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        K = split[2];
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        double[] tree = new double[N * 4];
        setTree(arr, 0, arr.length - 1, 1, tree);
        for (int i = 0; i < M + K; i++) {
            long[] longSplit = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            long operator = longSplit[0];
            if (operator == 1) {
                int idx = (int)longSplit[1] - 1;
                long c = longSplit[2];
                switchNode(idx, c, 0, arr.length - 1, 1, tree);
            } else if (operator == 2) {
                int from = (int)longSplit[1] - 1;
                int to = (int)longSplit[2] - 1;
                double sum = getSegmentSum(from, to, 0, arr.length - 1, 1, tree);

                bw.write(String.valueOf((long)sum));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
