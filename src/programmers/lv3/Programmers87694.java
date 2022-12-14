package programmers.lv3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 아이템 줍기
 * bfs
 * 공간 2개 확장할 거면, 시작 지점과 끝 지점도 2배 늘릴 것.
 */
public class Programmers87694 {

    static class Point {
        int x;
        int y;
        int dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    int[][] board = new int[105][105];


    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        // board[i][j] == 0 -> 정사각형 테두리 또는 내부 공간이 아님
        // board[i][j] == 1 -> 정사각형 테두리 공간 중 갈 수 있는 곳
        // board[i][j] == 2 -> 정사각형 테두리 또는 내부 공간인데 갈 수 없는 곳
        // board[i][j] == -1 -> 이미 지나감
        for (int[] rect : rectangle) {
            // 1칸짜리 ㄷ자 이동 실제 크기 좌표에서는 네 점모두 1,1,1,1이라 정상적으로 이동하지 않음
            // 공간 2배로 확장
            int leftUpperX = rect[0] * 2;
            int leftUpperY = rect[1] * 2;
            int rightLowerX = rect[2] * 2;
            int rightLowerY = rect[3] * 2;
            for (int i = leftUpperX; i <= rightLowerX; i++) {
                for (int j = leftUpperY; j <= rightLowerY; j++) {
                    // 정사각형 테두리인 경우
                    if (i == leftUpperX || i == rightLowerX || j == leftUpperY || j == rightLowerY) {
                        // 새롭게 갈 수 있는 테두리로 설정
                        if (board[i][j] == 0) {
                            board[i][j] = 1;
                        }
                    } else {
                        // 내부공간 모두 갈 수 없도록 설정
                        board[i][j] = 2;
                    }
                }
            }
        }

        int startX = characterX * 2;
        int startY = characterY * 2;
        Point startPoint = new Point(startX, startY, 0);
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        board[startX][startY] = -1;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int dist = poll.dist;

            if (x == itemX * 2 && y == itemY * 2) {
                return dist / 2;
            }

            if (board[x - 1][y] == 1) {
                queue.add(new Point(x - 1, y, dist + 1));
                board[x - 1][y] = -1;
            }
            if (board[x + 1][y] == 1) {
                queue.add(new Point(x + 1, y, dist + 1));
                board[x + 1][y] = -1;
            }
            if (board[x][y - 1] == 1) {
                queue.add(new Point(x, y - 1, dist + 1));
                board[x][y - 1] = -1;
            }
            if (board[x][y + 1] == 1) {
                queue.add(new Point(x, y + 1, dist + 1));
                board[x][y + 1] = -1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Programmers87694 programmers87694 = new Programmers87694();
        int solution = programmers87694.solution(
            new int[][]{{1,1,4,4}, {2,2,5,5}, {3,3,7,8}}, 1, 1, 7, 3
        );
        System.out.println(solution);
    }
}
