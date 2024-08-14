package baekjoon;

import java.io.*;
import java.util.Arrays;

/**
 * 수열과 쿼리 37
 * https://www.acmicpc.net/problem/18436
 */
public class BOJ18436 {

    public static int N;
    public static int M;

    static class Node {
        int id;
        int even = 0;
        int odd = 0;
        int value;

        public Node(int id) {
            this.id = id;
        }
    }

    public static Node setTree(int[] arr, int st, int en, int id, Node[] tree) {
        if (st == en) {
            Node node = new Node(id);
            if (arr[st] % 2 == 0) {
                node.even = 1;
            } else {
                node.odd = 1;
            }
            node.value = arr[st];
            tree[id] = node;
            return node;
        }
        int mid = (st + en) / 2;
        Node res1 = setTree(arr, st, mid, id * 2, tree);
        Node res2 = setTree(arr, mid + 1, en, (id * 2) + 1, tree);

        Node node = new Node(id);
        tree[id] = node;
        node.even = res1.even + res2.even;
        node.odd = res1.odd + res2.odd;
        return node;
    }

    public static Node query(int from, int to, int st, int en, int id, Node[] tree) {
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
        Node res1 = query(from, to, st, mid, id * 2, tree);
        Node res2 = query(from, to, mid + 1, en, (id * 2) + 1, tree);

        Node node = new Node(id);
        if (res1 == null) {
            node.even = res2.even;
            node.odd = res2.odd;
        } else if (res2 == null) {
            node.even = res1.even;
            node.odd = res1.odd;
        } else {
            node.even = res1.even + res2.even;
            node.odd = res1.odd + res2.odd;
        }
        return node;
    }

    private static void switchNode(int idx, int value, int st, int en, int node, Node[] tree) {
        if (st == en && st == idx) {
            tree[node].value = value;
            if (value % 2 == 0) {
                tree[node].even = 1;
                tree[node].odd = 0;
            } else {
                tree[node].even = 0;
                tree[node].odd = 1;
            }
            return;
        }

        int mid = (st + en) / 2;
        if (idx <= mid) {
            switchNode(idx, value, st, mid, node * 2, tree);
        } else {
            switchNode(idx, value, mid + 1, en, (node * 2) + 1, tree);
        }
        Node leftChild = tree[node * 2];
        Node rightChild = tree[node * 2 + 1];
        tree[node].even = leftChild.even + rightChild.even;
        tree[node].odd = leftChild.odd + rightChild.odd;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        Node[] tree = new Node[N * 4];
        setTree(arr, 0, arr.length - 1, 1, tree);
        for (int i = 0; i < M; i++) {
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int operator = split[0];
            if (operator == 1) {
                int target = split[1] - 1;
                int value = split[2];
                switchNode(target, value, 0, arr.length - 1, 1, tree);
            } else if (operator == 2) {
                int from = split[1] - 1;
                int to = split[2] - 1;
                Node result = query(from, to, 0, arr.length - 1, 1, tree);
                bw.write(String.valueOf(result.even));
                bw.newLine();
            } else if (operator == 3) {
                int from = split[1] - 1;
                int to = split[2] - 1;
                Node result = query(from, to, 0, arr.length - 1, 1, tree);
                bw.write(String.valueOf(result.odd));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
