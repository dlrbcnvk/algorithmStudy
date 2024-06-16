package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 알파벳
 * https://www.acmicpc.net/problem/1987
 */
public class BOJ1987 {

    private static int[] dy = new int[]{-1, 1, 0, 0};
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static Set<String> historySet = new HashSet<>();
    private static int result = 0;
    private static int r;
    private static int c;
    private static String[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        r = Integer.parseInt(split[0]);
        c = Integer.parseInt(split[1]);
        board = new String[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().split("");
        }
        historySet.add(board[0][0]);
        go(0, 0);

        System.out.println(result);
    }

    private static void go(int y, int x) {
        result = Math.max(historySet.size(), result);

        for (int i = 0; i < 4; i++) {
            int toY = y + dy[i];
            int toX = x + dx[i];
            if (toY < 0 || toY >= r || toX < 0 || toX >= c) continue;

            if (!historySet.contains(board[toY][toX])) {
                historySet.add(board[toY][toX]);
                go(toY, toX);
                historySet.remove(board[toY][toX]);
            }
        }
    }
}
