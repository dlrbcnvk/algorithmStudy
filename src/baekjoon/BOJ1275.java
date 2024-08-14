package baekjoon;

import java.io.*;
import java.util.Arrays;

/**
 * 커피숍2
 */
public class BOJ1275 {

    public static int N;
    public static int Q;

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
        Q = split[1];

        long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] tree = new long[N * 4];
        setTree(arr, 0, arr.length - 1, 1, tree);
        for (int i = 0; i < Q; i++) {
            long[] longSplit = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

            int from = (int) longSplit[0] - 1;
            int to = (int) longSplit[1] - 1;
            long sum = 0;
            if (from < to) {
                sum = getSegmentSum(from, to, 0, arr.length - 1, 1, tree);
            } else {
                sum = getSegmentSum(to, from, 0, arr.length - 1, 1, tree);
            }

            bw.write(String.valueOf(sum));
            bw.newLine();

            int idx = (int) longSplit[2] - 1;
            long c = longSplit[3];
            switchNode(idx, c, 0, arr.length - 1, 1, tree);
        }
        bw.flush();
    }
}
