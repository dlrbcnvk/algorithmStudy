package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 구간 합 구하기 3
 *
 * 미해결
 */
public class BOJ11658 {

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

    public static long getSegmentSum(int from, int to, int st, int en, int node, long[] tree) {
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
        long res1 = getSegmentSum(from, to, st, mid, node * 2, tree);
        long res2 = getSegmentSum(from, to, mid + 1, en, (node * 2) + 1, tree);

        return res1 + res2;
    }

    private static void switchNode(int idx, long c, int st, int en, int node, long[] tree) {
        if (st == en && st == idx) {
            tree[node] = c;
            return;
        }

        int mid = (st + en) / 2;
        if (idx <= mid) {
            switchNode(idx, c, st, mid, node * 2, tree);
        } else {
            switchNode(idx, c, mid + 1, en, (node * 2) + 1, tree);
        }
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        List<Long> arrList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Arrays.stream(br.readLine().split(" "))
                    .forEach(str -> arrList.add(Long.parseLong(str)));
        }
        long[] arr = arrList.stream().mapToLong(l -> l).toArray();
        long[] tree = new long[N * N * 4];
        setTree(arr, 0, arr.length - 1, 1, tree);
        for (int i = 0; i < M; i++) {
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int operator = split[0];
            if (operator == 0) {
                int x = split[1] - 1;
                int y = split[2] - 1;
                int idx = x * N + y;
                long c = split[2];
                switchNode(idx, c, 0, arr.length - 1, 1, tree);
            } else if (operator == 1) {
                int fromX = split[1] - 1;
                int fromY = split[2] - 1;
                int toX = split[3] - 1;
                int toY = split[4] - 1;
                int from = fromX * N + fromY;
                int to = toX * N + toY;
                long sum = getSegmentSum(from, to, 0, arr.length - 1, 1, tree);
                bw.write(String.valueOf(sum));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
