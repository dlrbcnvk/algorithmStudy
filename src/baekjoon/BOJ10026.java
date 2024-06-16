package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 적록색약
 * https://www.acmicpc.net/problem/10026
 */
public class BOJ10026 {

    private static int N;
    private static String R = "R";
    private static String G = "G";
    private static String B = "B";
    private static String[][] board;

    private static int[] dy = new int[]{-1, 1, 0, 0};
    private static int[] dx = new int[]{0, 0, -1, 1};

    public static class Node {
        int y;
        int x;
        int id;
        String rgbData;
        Node parent = null;

        public Node(int y, int x, int id, String rgbData) {
            this.y = y;
            this.x = x;
            this.id = id;
            this.rgbData = rgbData;
        }

        public boolean isConnected(Node node) {
            return this.root() == node.root();
        }

        // id 작은 쪽으로 합치기
        public void merge(Node node) {
            if (this.id < node.id) {
                node.parent = this;
                node.id = this.id;
            } else {
                this.parent = node;
                this.id = node.id;
            }
        }

        private Node root() {
            if (this.parent == null) return this;
            return this.parent.root();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.valueOf(br.readLine());
        board = new String[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().split("");
        }

        int healthy = getGroupCount(false);
        int redGreenWeak = getGroupCount(true);

        System.out.println(healthy + " " + redGreenWeak);
    }

    private static int getGroupCount(boolean isRedGreenWeak) {
        int id = 1;
        Node[][] nodes = new Node[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nodes[i][j] = new Node(i, j, id, board[i][j]);
                id++;
            }
        }
        boolean[][] marked = new boolean[N][N];
        for (int i = 0; i < N; i++) marked[i] = new boolean[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (marked[i][j]) continue;
                marked[i][j] = true;
                Queue<Node> queue = new LinkedList<>();
                queue.add(nodes[i][j]);
                while (!queue.isEmpty()) {
                    Node node = queue.poll();
                    int y = node.y;
                    int x = node.x;
                    String curRGB = node.rgbData;

                    // 상하좌우로 탐색할 수 있는지 체크
                    for (int a = 0; a < 4; a++) {
                        int toY = y + dy[a];
                        int toX = x + dx[a];
                        if (toY < 0 || toY >= N || toX < 0 || toX >= N) continue;
                        if (marked[toY][toX]) continue;

                        String toRGB = nodes[toY][toX].rgbData;
                        if (isRedGreenWeak) {
                            if (!curRGB.equals(B) && !toRGB.equals(B)) {
                                // 적록 같은그룹
                                markingAndAddNodeAndMerge(nodes, marked, queue, y, x, toY, toX);
                            } else if (curRGB.equals(toRGB)) {
                                // 적록이 아닌데 같다면 파란색으로 같은 경우임
                                markingAndAddNodeAndMerge(nodes, marked, queue, y, x, toY, toX);
                            }
                        } else {
                            if (curRGB.equals(toRGB)) {
                                // 적록색약이 아니라면 무조건 둘이 같아야 함
                                markingAndAddNodeAndMerge(nodes, marked, queue, y, x, toY, toX);
                            }
                        }
                    }
                }
            }
        }

        Set<Integer> idSet = new HashSet<>();
        for (int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                idSet.add(nodes[i][j].id);
            }
        }
        return idSet.size();
    }

    private static void markingAndAddNodeAndMerge(Node[][] nodes, boolean[][] marked, Queue<Node> queue, int y, int x, int toY, int toX) {
        marked[toY][toX] = true;
        queue.add(nodes[toY][toX]);
        nodes[y][x].merge(nodes[toY][toX]);
    }
}
