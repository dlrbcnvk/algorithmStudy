package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 종이의 개수
 * https://www.acmicpc.net/problem/1780
 * 분할과 정복, 재귀
 */
public class BOJ1780 {

    private static int cntMinusOne = 0; // -1로만 채워진 종이의 개수
    private static int cntZero = 0; // 0으로만 종이의 개수
    private static int cntOne = 0; // 1로만 종이의 개수
    private static int n;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        cutPaper(0, 0, n);

        System.out.println(cntMinusOne);
        System.out.println(cntZero);
        System.out.println(cntOne);
    }

    public static void cutPaper(int y, int x, int length) {

//         System.out.println("y=" + y + ", x=" + x + ", length=" + length);

        // 종이가 모두 같은 수로 되어있는지 확인하기
        int value = board[y][x];
        boolean stayAndUse = true;
        for (int i = y; i < y + length; i++) {
            if (!stayAndUse) break;

            for (int j = x; j < x + length; j++) {
                if (board[i][j] != value) {
                    stayAndUse = false;
                    break;
                }
            }
        }

        // 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
        if (stayAndUse) {
            if (value == -1) {
                cntMinusOne++;
            } else if (value == 0) {
                cntZero++;
            } else if (value == 1) {
                cntOne++;
            }
            return;
        }

        // 종이가 모두 같은 수로 되어있지 않다면
        // 종이를 같은 크기의 종이 9개로 자르고, 각각의 잘린 종이에 대해서 과정을 반복함.
        int nextLength = length / 3;
        cutPaper(y, x, nextLength);
        cutPaper(y, x + nextLength, nextLength);
        cutPaper(y, x + (2 * nextLength), nextLength);
        cutPaper(y + nextLength, x, nextLength);
        cutPaper(y + nextLength, x + nextLength, nextLength);
        cutPaper(y + nextLength, x + (2 * nextLength), nextLength);
        cutPaper(y + (2 * nextLength), x, nextLength);
        cutPaper(y + (2 * nextLength), x + nextLength, nextLength);
        cutPaper(y + (2 * nextLength), x + (2 * nextLength), nextLength);
    }
}
