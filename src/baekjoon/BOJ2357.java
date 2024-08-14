package baekjoon;

import java.io.*;
import java.util.Arrays;

/**
 * 최솟값과 최댓값
 * https://www.acmicpc.net/problem/2357
 */
public class BOJ2357 {

    public static int N;
    public static int M;

    static class Node {
        int id;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        public Node(int id) {
            this.id = id;
        }
    }

    public static Node setTree(int[] arr, int st, int en, int id, Node[] tree) {
        if (st == en) {
            Node node = new Node(id);
            node.min = arr[st];
            node.max = arr[en];
            tree[id] = node;
            return node;
        }
        int mid = (st + en) / 2;
        Node res1 = setTree(arr, st, mid, id * 2, tree);
        Node res2 = setTree(arr, mid + 1, en, (id * 2) + 1, tree);

        Node node = new Node(id);
        tree[id] = node;
        node.min = Math.min(res1.min, res2.min);
        node.max = Math.max(res1.max, res2.max);
        return node;
    }

    public static Node getSegmentSum(int from, int to, int st, int en, int id, Node[] tree) {
        if (st == en) {
            if (from <= st && en <= to) {
                return tree[id];
            } else {
                return null;
            }
        }
        if (from <= st && en <= to) {
            return tree[id];
        }
        if (en < from || to < st) {
            return null;
        }
        int mid = (st + en) / 2;
        Node res1 = getSegmentSum(from, to, st, mid, id * 2, tree);
        Node res2 = getSegmentSum(from, to, mid + 1, en, (id * 2) + 1, tree);

        Node node = new Node(id);
        if (res1 == null) {
            return res2;
        }
        if (res2 == null) {
            return res1;
        }
        node.min = Math.min(res1.min, res2.min);
        node.max = Math.max(res1.max, res2.max);
        return node;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Node[] tree = new Node[N * 4];
        setTree(arr, 0, arr.length - 1, 1, tree);
        for (int i = 0; i < M; i++) {
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = split[0] - 1;
            int to = split[1] - 1;
            Node segmentSum = getSegmentSum(from, to, 0, arr.length - 1, 1, tree);
            bw.write(segmentSum.min + " " + segmentSum.max);
            bw.newLine();
        }
        bw.flush();
    }
}
