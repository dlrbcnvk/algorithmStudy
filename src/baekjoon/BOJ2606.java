package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 바이러스
 * https://www.acmicpc.net/problem/2606
 */
public class BOJ2606 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int m = Integer.valueOf(br.readLine());

        boolean[] marked = new boolean[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] split = br.readLine().split(" ");
            int v = Integer.valueOf(split[0]);
            int w = Integer.valueOf(split[1]);
            graph[v].add(w);
            graph[w].add(v);
        }

        marked[1] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for (int next : graph[poll]) {
                if (!marked[next]) {
                    marked[next] = true;
                    queue.add(next);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < marked.length; i++) {
            if (marked[i]) {
                count++;
            }
        }
        System.out.println(count - 1);
    }
}
