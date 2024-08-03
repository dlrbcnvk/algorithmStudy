package CLRS.chapter20;

/**
 * 플로이드-워샬 알고리즘
 * 모든 쌍 최단경로 문제
 * 음의 가중 간선 O, 음의 가중순환 X 가정함.
 *
 */
public class FloydWarshall {



    public void solution(int n, int[][] edges) {
        Integer[][] adjMatrix = new Integer[n][n];
        Integer[][] preMatrix = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            adjMatrix[i] = new Integer[n];
            preMatrix[i] = new Integer[n];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    adjMatrix[i][j] = 0;
                } else {
                    adjMatrix[i][j] = Integer.MAX_VALUE;
                }
                preMatrix[i][j] = null;
            }
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            adjMatrix[from][to] = weight;
            preMatrix[from][to] = from;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (adjMatrix[i][k] == Integer.MAX_VALUE || adjMatrix[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                        preMatrix[i][j] = preMatrix[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (preMatrix[i][j] == null) {
                    System.out.print(null + " ");
                } else {
                    System.out.print(preMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        FloydWarshall floydWarshall = new FloydWarshall();
        floydWarshall.solution(
                5,
                new int[][]{
                        {0, 1, 3},
                        {0, 2, 8},
                        {0, 4, -4},
                        {1, 3, 1},
                        {1, 4, 7},
                        {2, 1, 4},
                        {3, 0, 2},
                        {3, 2, -5},
                        {4, 3, 6}
                }
        );
    }
}
