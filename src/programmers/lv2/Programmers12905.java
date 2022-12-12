package programmers.lv2;

/**
 * 가장 큰 정사각형 찾기
 * 테스트케이스 1번 시간초과...ㅠ
 * 미해결
 */
public class Programmers12905 {

    int[][] board;
    int n, m; // 가로 세로
    int maxSize = 0;

    // (x,y) ~ (a,b) 모두 0으로 만드는 메서드
    public void makeAllZero(int x, int y, int a, int b) {
        for (int i = x; i <= a; i++) {
            for (int j = y; j <= b; j++) {
                board[i][j] = 0;
            }
        }
    }

    // board[i][j] == 1일 경우 호출, 탐색
    public int search(int x, int y) {

        if (maxSize != 0) {
            if (x + maxSize - 1 >= n || y + maxSize - 1 >= m) {
                return 0;
            }

        }

        int size = 1;

        boolean isSquare = true;
        int movingX = x + size;
        int movingY = y + size;
        int eraseX = movingX;
        int eraseY = movingY;
        while (movingX < n && movingY < m) {
            for (int i = 0; i < size; i++) {
                if (board[x + i][movingY] != 1) {
                    isSquare = false;
                    eraseX = x + i;
                    eraseY = movingY;
                }
                if (board[movingX][y + i] != 1) {
                    isSquare = false;
                    eraseX = movingX;
                    eraseY = y + i;
                }
                if (board[movingX][movingY] != 1) {
                    isSquare = false;
                    eraseX = movingX;
                    eraseY = movingY;
                }

            }

            if (isSquare) {
                size++;
                movingX++;
                movingY++;
            } else {
                makeAllZero(x, y, eraseX, eraseY);
                break;
            }
        }

        return size;
    }

    public int solution(int [][]board) {

        this.board = board;
        this.n = board.length;
        this.m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    int size = search(i, j);
                    maxSize = Math.max(size, maxSize);
                }
            }
        }
        return maxSize * maxSize;
    }

    public static void main(String[] args) {
        Programmers12905 programmers12905 = new Programmers12905();
        int solution = programmers12905.solution(new int[][]{
                {1, 1},

        });
        System.out.println(solution);
    }
}
