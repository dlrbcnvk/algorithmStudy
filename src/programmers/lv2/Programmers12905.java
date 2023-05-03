package programmers.lv2;

/**
 * 가장 큰 정사각형 찾기
 * 2023.05.03 AC
 * 오른쪽 아래에서부터 왼쪽 위까지 올라오면서 dp, 기록하기
 */
public class Programmers12905 {

    // 상 하 좌 우
    private static int[] dy = new int[]{-1, 1, 0, 0};
    private static int[] dx = new int[]{0, 0, -1, 1};


    int[][] board;
    int n, m; // 가로 세로

    // board[i][j] == 1일 경우 탐색
    public void search(int y, int x) {

        // 아래끝, 오른쪽끝 -> 변동없음
        if (y == n - 1 || x == m - 1) return;

        // 해당 좌표가 0 이면 리턴
        if (board[y][x] == 0) return;
        // 오른쪽 또는 아래가 0 이면 리턴
        if (board[y + dy[3]][x + dx[3]] == 0 || board[y + dy[1]][x + dx[1]] == 0) return;

        int rightSquare = board[y + dy[3]][x + dx[3]];
        int downSquare = board[y + dy[1]][x + dx[1]];
        int minSquare = Math.min(rightSquare, downSquare);
        int minSize = (int) Math.sqrt(minSquare);
        // 정사각형을 만들기 위한 오른쪽아래 맨 끝점이 0 이라면 가능한 정사각형을 찾아서 사이즈 줄이기
        while (board[y + minSize][x + minSize] == 0) {
            minSize--;
        }

//        System.out.println("(" + y + "," + x + ")~(" + (y + minSize) + "," + (x + minSize) + ")");
//        System.out.println("(" + y + "," + x + ")" + "=" + (minSize + 1) * (minSize + 1));
        board[y][x] = (minSize + 1) * (minSize + 1);
    }

    public int solution(int [][]board) {

        this.board = board;
        this.n = board.length;
        this.m = board[0].length;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                search(i, j);
            }
        }

        int maxSquare = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxSquare = Math.max(board[i][j], maxSquare);
            }
        }
        return maxSquare;
    }

    public static void main(String[] args) {
        Programmers12905 programmers12905 = new Programmers12905();

        int solution = programmers12905.solution(
                new int[][]{
                        {0,1,1,1},
                        {1,1,1,1},
                        {1,1,1,1},
                        {0,0,1,0}
                }
        );
        System.out.println(solution);
    }
}
