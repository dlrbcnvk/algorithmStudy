package programmers.lv2;

/**
 * 쿼드 압축 후 개수 세기
 */
public class Programmers68936 {

    int[][] board;
    boolean[][] marked;
    int countZero = 0;
    int countOne = 0;

    public void search(int x, int y, int size) {
        if (size == 1) {
            return;
        }
        boolean allOne = true;
        boolean allZero = true;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] == 0) {
                    allOne = false;
                } else if (board[i][j] == 1) {
                    allZero = false;
                }
            }
        }
        if (allOne || allZero) {
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    if (i == x && j == y) {
                        continue;
                    }
                    marked[i][j] = false;
                }
            }
            return;
        }

        size = size / 2;
        search(x, y, size);
        search(x, y + size, size);
        search(x + size, y, size);
        search(x + size, y + size, size);
    }

    public int[] solution(int[][] arr) {
        this.board = arr;
        int n = arr.length;
        this.marked = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                marked[i][j] = true;
            }
        }

        search(0, 0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (marked[i][j]) {
                    if (board[i][j] == 0) {
                        countZero++;
                    } else if (board[i][j] == 1) {
                        countOne++;
                    }
                }
            }
        }

        return new int[]{countZero, countOne};
    }

    public static void main(String[] args) {
        Programmers68936 programmers68936 = new Programmers68936();
        int[] solution = programmers68936.solution(new int[][]{
                {1,1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1},
                {0,1,0,0,1,1,1,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,1,0,0,1},
                {0,0,0,0,1,1,1,1}
        });


        for (int num : solution) {
            System.out.print(num + " ");
        }
    }
}
