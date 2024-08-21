package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 색종이 만들기
 */
public class BOJ2630_2 {

    private static int N;
    private static int[][] arr;
    private static int[] result = new int[2];

    public static void divide(int y, int x, int size) {
        int value = arr[y][x];
        if (size == 1) {
            // 종료조건
            if (value == 0) {
                result[0]++;
            } else if (value == 1){
                result[1]++;
            }
            return;
        }

        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (arr[i][j] != value) {
                    // divide
                    int half = size / 2;
                    divide(y, x, half);
                    divide(y, x + half, half);
                    divide(y + half, x, half);
                    divide(y + half, x + half, half);
                    return;
                }
            }
        }

        if (value == 0) {
            result[0]++;
        } else if (value == 1){
            result[1]++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        divide(0, 0, N);

        System.out.println(result[0]);
        System.out.println(result[1]);

    }
}
