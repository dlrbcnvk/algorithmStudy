package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 색종이 만들기
 * https://www.acmicpc.net/problem/2630
 */
public class BOJ2630 {

    private static int N;
    private static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[] result = divide(0, 0, N, new int[]{0, 0});
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    private static int[] divide(int y, int x, int size, int[] counts) {
        boolean isValueAllSame = true;
        int value = board[y][x];
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (board[i][j] != board[y][x]) {
                    isValueAllSame = false;
                    break;
                }
            }
            if (!isValueAllSame) {
                break;
            }
        }

        // 모두 하나의 색깔로 칠해진 경우
        if (isValueAllSame) {
            counts[value]++;
            return counts;
        }

        // 4 구역으로 나눠야 함
        int half = size / 2;
        counts = divide(y, x, half, counts);
        counts = divide(y + half, x, half, counts);
        counts = divide(y, x + half, half, counts);
        counts = divide(y + half, x + half, half, counts);
        return counts;
    }
}
