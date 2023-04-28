package programmers.pccp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 보물 지도
 * https://school.programmers.co.kr/learn/courses/15009/lessons/121690
 * 시간 초과...
 */
public class Programmers121690 {

    /**
     * 상: 0
     * 하: 1
     * 좌: 2
     * 우: 3
     */
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private int minTime = Integer.MAX_VALUE;

    private static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public int solution(int n, int m, int[][] hole) {

        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], Integer.MAX_VALUE);
        }
        board[0][0] = 0;
        for (int i = 0; i < hole.length; i++) {
            board[hole[i][0] - 1][hole[i][1] - 1] = -1;
        }

        // 신발 사용하지 않은 경우
        startBFS(0, 0, board);
        minTime = Math.min(minTime, board[n - 1][m - 1]);


        // 신발을 어느 함정에서 사용할 것인지 일일이 돌면서 확인하기
        for (int[] h : hole) {
            int[][] shoeBoard;

            int hy = h[0] - 1;
            int hx = h[1] - 1;

            // 위 -> 아래
            int fromY = hy + dy[0];
            int fromX = hx + dx[0];
            int toY = hy + dy[1];
            int toX = hx + dx[1];
            shoeBoard = resetShoeBoard(board);

            if (fromY >= 0 && toY < n) {
                if (checkShoeCondition(fromY, fromX, toY, toX, shoeBoard)) {
                    progressShoeBoardBFS(fromY, fromX, toY, toX, shoeBoard);
                }
            }

            // 아래 -> 위
            fromY = hy + dy[1];
            fromX = hx + dx[1];
            toY = hy + dy[0];
            toX = hx + dx[0];
            shoeBoard = resetShoeBoard(board);

            if (fromY < n && toY >= 0) {
                if (checkShoeCondition(fromY, fromX, toY, toX, shoeBoard)) {
                    progressShoeBoardBFS(fromY, fromX, toY, toX, shoeBoard);
                }
            }

            // 왼쪽 -> 오른쪽
            fromY = hy + dy[2];
            fromX = hx + dx[2];
            toY = hy + dy[3];
            toX = hx + dx[3];
            shoeBoard = resetShoeBoard(board);

            if (fromX >= 0 && toX < m) {
                if (checkShoeCondition(fromY, fromX, toY, toX, shoeBoard)) {
                    progressShoeBoardBFS(fromY, fromX, toY, toX, shoeBoard);
                }
            }

            // 오른쪽 -> 왼쪽
            fromY = hy + dy[3];
            fromX = hx + dx[3];
            toY = hy + dy[2];
            toX = hx + dx[2];
            shoeBoard = resetShoeBoard(board);

            if (fromX < m && toX >= 0) {
                if (checkShoeCondition(fromY, fromX, toY, toX, shoeBoard)) {
                    progressShoeBoardBFS(fromY, fromX, toY, toX, shoeBoard);
                }
            }
        }

        return (minTime == Integer.MAX_VALUE) ? -1 : minTime;
    }

    private void startBFS(int startY, int startX, int[][] board) {
        int n = board.length;
        int m = board[0].length;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startY, startX));
        while (!q.isEmpty()) {
            Point poll = q.poll();
            int y = poll.y;
            int x = poll.x;

            // 아래 방향
            if (y + dy[1] < n) {
                if (board[y + dy[1]][x + dx[1]] != -1 && board[y + dy[1]][x + dx[1]] > board[y][x] + 1) {
                    board[y + dy[1]][x + dx[1]] = board[y][x] + 1;
                    q.add(new Point(y + dy[1], x + dx[1]));
                }
            }
            // 오른쪽 방향
            if (x + dx[3] < m) {
                if (board[y + dy[3]][x + dx[3]] != -1 && board[y + dy[3]][x + dx[3]] > board[y][x] + 1) {
                    board[y + dy[3]][x + dx[3]] = board[y][x] + 1;
                    q.add(new Point(y + dy[3], x + dx[3]));
                }
            }

            // 위 방향
            if (y + dy[0] >= 0) {
                if (board[y + dy[0]][x + dx[0]] != -1 && board[y + dy[0]][x + dx[0]] > board[y][x] + 1) {
                    board[y + dy[0]][x + dx[0]] = board[y][x] + 1;
                    q.add(new Point(y + dy[0], x + dx[0]));
                }
            }

            // 왼쪽 방향
            if (x + dx[2] >= 0) {
                if (board[y + dy[2]][x + dx[2]] != -1 && board[y + dy[2]][x + dx[2]] > board[y][x] + 1) {
                    board[y + dy[2]][x + dx[2]] = board[y][x] + 1;
                    q.add(new Point(y + dy[2], x + dx[2]));
                }
            }
        }
    }

    private boolean checkShoeCondition(int fromY, int fromX, int toY, int toX, int[][] shoeBoard) {
        return (shoeBoard[fromY][fromX] != -1 &&
                shoeBoard[toY][toX] != -1 && // 양쪽이 함정이 아니어야 하고
                shoeBoard[fromY][fromX] != Integer.MAX_VALUE && // 점프 시작점이 점프 이전에도 갈 수 있는 곳이어야 하고
                shoeBoard[toY][toX] > shoeBoard[fromY][fromX] + 1);
    }

    private void progressShoeBoardBFS(int fromY, int fromX, int toY, int toX, int[][] shoeBoard) {
        shoeBoard[toY][toX] = shoeBoard[fromY][fromX] + 1;
        startBFS(toY, toX, shoeBoard);
        minTime = Math.min(minTime, shoeBoard[shoeBoard.length - 1][shoeBoard[0].length - 1]);
    }

    private int[][] resetShoeBoard(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] shoeBoard = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                shoeBoard[i][j] = board[i][j];
            }
        }
        return shoeBoard;
    }


    public static void main(String[] args) {
        Programmers121690 programmers121690 = new Programmers121690();
        int solution = programmers121690.solution(
                5, 4, new int[][]{{1,4},{2,1},{2,2},{2,3},{2,4},{3,3},{4,1},{4,3},{5,3}}
        );
        System.out.println(solution);
    }
}
