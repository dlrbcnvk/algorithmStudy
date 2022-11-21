package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 게임 맵 최단거리
 * bfs
 */
public class Programmers1844 {

    public int solution(int[][] maps) {

        int n = maps.length;
        int m = maps[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1});
        maps[0][0] = -1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int dist = poll[2];

            if (x == n - 1 && y == m - 1) {
                return dist;
            }

            if (x + 1 < n && maps[x + 1][y] == 1) {
                queue.add(new int[]{x + 1, y, dist + 1});
                maps[x + 1][y] = -1;
            }
            if (x - 1 >= 0 && maps[x - 1][y] == 1) {
                queue.add(new int[]{x - 1, y, dist + 1});
                maps[x-1][y] = -1;
            }
            if (y + 1 < m && maps[x][y + 1] == 1) {
                queue.add(new int[]{x, y + 1, dist + 1});
                maps[x][y + 1] = -1;
            }
            if (y - 1 >= 0 && maps[x][y - 1] == 1) {
                queue.add(new int[]{x, y - 1, dist + 1});
                maps[x][y - 1] = -1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Programmers1844 programmers1844 = new Programmers1844();
        int[][] arr = new int[][]{
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,1},
                {0,0,0,0,1}
        };
        int solution = programmers1844.solution(arr);
        System.out.println(solution);
    }
}
