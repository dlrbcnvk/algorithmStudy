package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 경로 찾기
 */
public class BOJ11403 {

    private static int N;
    private static ArrayList<Integer>[] graph;

    static class Node {
        int from;
        int to;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(split[j]) == 1) {
                    graph[i].add(j);
                }
            }
        }

        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i] = new int[N];
            Arrays.fill(result[i], 0);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // i -> j (출발점과 같은점 간 경로가 양의 정수로 있는 경우(순환) 포함)
                boolean[][] marked = new boolean[N][N];
                for (int t = 0; t < N; t++) marked[i] = new boolean[N];
                Queue<Node> queue = new LinkedList<>();
                queue.add(new Node(i, i));
                while (!queue.isEmpty()) {
                    Node poll = queue.poll();
                    int curVertex = poll.to;
                    if (curVertex == j && marked[poll.from][curVertex]) {
                        result[i][j] = 1;
                        break;
                    }

                    for (int next : graph[curVertex]) {
                        if (!marked[curVertex][next]) {
                            marked[curVertex][next] = true;
                            queue.add(new Node(curVertex, next));
                        }
                    }
                }
            }
        }

        String space = " ";
        String nextLine = "\n";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]);
                if (j != N - 1) {
                    sb.append(space);
                }
            }
            sb.append(nextLine);
        }
        System.out.println(sb);
    }
}
