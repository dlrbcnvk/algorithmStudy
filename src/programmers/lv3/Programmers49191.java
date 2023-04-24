package programmers.lv3;

/**
 * 순위
 * 그래프 인접행렬
 */
public class Programmers49191 {

    public int solution(int n, int[][] results) {

        boolean[][] graph = new boolean[n][n];
        for (int[] result : results) {
            graph[result[0] - 1][result[1] - 1] = true;
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int wins = countForward(i, graph, new boolean[n]);
            int loses = countBackward(i, graph, new boolean[n]);
            if (wins + loses + 1 == n) {
                answer++;
            }
        }

        return answer;
    }

    private int countForward(int u, boolean[][] graph, boolean[] marked) {

        int count = 0;

        for (int v = 0; v < graph[u].length; v++) {
            if (!graph[u][v] || marked[v]) continue;
            marked[v] = true;
            count += countForward(v, graph, marked) + 1;
        }

        return count;
    }

    private int countBackward(int u, boolean[][] graph, boolean[] marked) {

        int count = 0;

        for (int v = 0; v < graph[u].length; v++) {
            if (!graph[v][u] || marked[v]) continue;
            marked[v] = true;
            count += countBackward(v, graph, marked) + 1;
        }

        return count;
    }

    public static void main(String[] args) {
        Programmers49191 programmers49191 = new Programmers49191();
        int solution = programmers49191.solution(
                5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}
        );
        System.out.println(solution);
    }
}
