package baekjoon;

import java.io.*;
import java.util.Arrays;

/**
 * 수열과 쿼리 21
 *
 */
public class BOJ16975 {

    public static int N;
    public static int M;

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

    public static long query(int target, int st, int en, int node, long[] tree, long[] lazy) {
        // 구간 쿼리 함수
        update_lazy(node, st, en, tree, lazy);

        if (st == en) {
            if (target == st) {
                return tree[node];
            } else {
                return Long.MAX_VALUE;
            }
        }

        int mid = (st + en) / 2;
        if (target <= mid) {
            return query(target, st, mid, node * 2, tree, lazy);
        } else {
            return query(target, mid + 1, en, (node * 2) + 1, tree, lazy);
        }
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
        N = Integer.parseInt(br.readLine());
        long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        M = Integer.parseInt(br.readLine());
        long[] tree = new long[N * 4];
        long[] lazy = new long[N * 4];
        setTree(arr, 0, arr.length - 1, 1, tree);
        for (int i = 0; i < M; i++) {
            long[] longSplit = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            long operator = longSplit[0];
            if (operator == 1) {
                int b = (int)longSplit[1] - 1;
                int c = (int)longSplit[2] - 1;
                long value = longSplit[3];
                update(b, c, value, 0, arr.length - 1, 1, tree, lazy);
            } else if (operator == 2) {
                int target = (int)longSplit[1] - 1;
                long sum = query(target, 0, arr.length - 1, 1, tree, lazy);
                bw.write(String.valueOf(sum));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
