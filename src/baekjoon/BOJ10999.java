package baekjoon;

import java.io.*;
import java.util.Arrays;

/**
 * 구간 합 구하기 2
 *
 */
public class BOJ10999 {

    public static int N;
    public static int M;
    public static int K;

    public static void setTree(long[] arr, int st, int en, int node, long[] tree) {
        if (st == en) {
            tree[node] = arr[st];
            return;
        }
        int mid = (st + en) / 2;
        setTree(arr, st, mid, node * 2, tree);
        setTree(arr, mid + 1, en, (node * 2) + 1, tree);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public static long query(int from, int to, int st, int en, int node, long[] tree, long[] lazy) {
        // 구간 쿼리 함수
        update_lazy(node, st, en, tree, lazy);

        if (en < from || to < st) return 0; // 포함되지 않는 범위

        // 완전히 포함되는 범위
        if (from <= st && en <= to) return tree[node];

        int mid = (st + en) / 2;
        return query(from, to, st, mid, node * 2, tree, lazy) +
                query(from, to, mid + 1, en, (node * 2) + 1, tree, lazy);
    }

    private static void update(int from, int to, long value, int st, int en, int node, long[] tree, long[] lazyTree) {

        // 구간 갱신 함수
        update_lazy(node, st, en, tree, lazyTree);

        // 포함되지 않는 범위
        if (en < from || to < st) return;

        // 완전히 포함되는 범위
        if (from <= st && en <= to) {
            tree[node] += value * (long)((en - st + 1));
            if (st != en) {
                // 구간 노드라면, 양쪽 자식에 lazy값을 추가
                lazyTree[node * 2] += value;
                lazyTree[(node * 2) + 1] += value;
            }
            return;
        }

        // 걸치는 범위
        int mid = (st + en) / 2;
        update(from, to, value, st, mid, node * 2, tree, lazyTree);
        update(from, to, value, mid + 1, en, (node * 2) + 1, tree, lazyTree);
        tree[node] = tree[node * 2] + tree[(node * 2) + 1];
    }

    private static void update_lazy(int node, int st, int en, long[] tree, long[] lazy) {
        // 현재 노드의 lazy값을 반영함
        if (lazy[node] != 0) {
            tree[node] += (long)((en - st + 1)) * lazy[node];
            if (st != en) {
                // 구간 노드라면, 양쪽 자식에 lazy값을 물려줌
                lazy[node * 2] += lazy[node];
                lazy[(node * 2) + 1] += lazy[node];
            }
            lazy[node] = 0; // 초기화
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
        long[] tree = new long[N * 4];
        long[] lazy = new long[N * 4];
        setTree(arr, 0, arr.length - 1, 1, tree);
        for (int i = 0; i < M + K; i++) {
            long[] longSplit = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            long operator = longSplit[0];
            int b = (int)longSplit[1] - 1;
            int c = (int)longSplit[2] - 1;
            if (operator == 1) {
                long value = longSplit[3];
                update(b, c, value, 0, arr.length - 1, 1, tree, lazy);
            } else if (operator == 2) {
                long sum = query(b, c, 0, arr.length - 1, 1, tree, lazy);
                bw.write(String.valueOf(sum));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
