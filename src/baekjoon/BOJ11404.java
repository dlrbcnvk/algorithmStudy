package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 플로이드
 * https://www.acmicpc.net/problem/11404
 */
public class BOJ11404 {

    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Map<Edge, Integer> edgeCostMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]) - 1;
            int to = Integer.parseInt(split[1]) - 1;
            int cost = Integer.parseInt(split[2]);
            Edge edge = new Edge(from, to);
            if (edgeCostMap.containsKey(edge)) {
                int preCost = edgeCostMap.get(edge);
                if (cost < preCost) {
                    edgeCostMap.put(edge, cost);
                }
            } else {
                edgeCostMap.put(edge, cost);
            }
        }

        Integer[][] adjMatrix = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            adjMatrix[i] = new Integer[n];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    adjMatrix[i][j] = 0;
                } else {
                    adjMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (Edge edge : edgeCostMap.keySet()) {
            int from = edge.from;
            int to = edge.to;
            int cost = edgeCostMap.get(edge);
            adjMatrix[from][to] = cost;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || i == k || j == k) {
                        continue;
                    }
                    if (adjMatrix[i][k].equals(Integer.MAX_VALUE) || adjMatrix[k][j].equals(Integer.MAX_VALUE)) {
                        continue;
                    }
                    if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j].equals(Integer.MAX_VALUE)) {
                    System.out.print(0);
                } else {
                    System.out.print(adjMatrix[i][j]);
                }
                if (j < n - 1) {
                    System.out.print(" ");
                } else {
                    System.out.print("\n");
                }
            }
        }
    }
}
