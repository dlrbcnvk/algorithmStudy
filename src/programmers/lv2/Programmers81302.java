package programmers.lv2;

/**
 * 거리두기 확인하기
 * 시간나면 책 참고해서 코드 리팩토링 하기
 */
public class Programmers81302 {

    private static final int[] dx = {0, 0, -1, 1, -1, 1, -1, 1, 0, 0, -2, 2};
    private static final int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1, -2, 2, 0, 0};

    String[][] board = new String[5][5];

    public int[] solution(String[][] places) {

        int[] answer = new int[5];
        int idx = 0;

        for (String[] place : places) {
            setBoard(place);
            Integer result = 1;

            boolean checkResult;
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (board[y][x].equals("P")) {
                        checkResult = checkDistance(x, y);
                        if (!checkResult) {
                            result = 0;
                        }
                    }
                }
            }

            answer[idx] = result;
            idx++;
        }

        return answer;
    }

    public void setBoard(String[] place) {
        for (int i = 0; i < 5; i++) {
            String s = place[i];
            String[] split = s.split("");
            for (int j = 0; j < 5; j++) {
                board[i][j] = split[j];
            }
        }
    }

    public boolean checkDistance(int x, int y) {

        if (y + dy[0] >= 0) {
            if (board[y + dy[0]][x + dx[0]].equals("P")) {
                return false;
            }
        }
        if (y + dy[1] <= 4) {
            if (board[y + dy[1]][x + dx[1]].equals("P")) {
                return false;
            }
        }
        if (x + dx[2] >= 0) {
            if (board[y + dy[2]][x + dx[2]].equals("P")) {
                return false;
            }
        }
        if (x + dx[3] <= 4) {
            if (board[y + dy[3]][x + dx[3]].equals("P")) {
                return false;
            }
        }
        if (y + dy[8] >= 0) {
            if (!board[y + dy[0]][x + dx[0]].equals("X") && board[y + dy[8]][x + dx[8]].equals("P")) {
                return false;
            }
        }
        if (y + dy[9] <= 4) {
            if (!board[y + dy[1]][x + dx[1]].equals("X") && board[y + dy[9]][x + dx[9]].equals("P")) {
                return false;
            }
        }
        if (x + dx[10] >= 0) {
            if (!board[y + dy[2]][x + dx[2]].equals("X") && board[y + dy[10]][x + dx[10]].equals("P")) {
                return false;
            }
        }
        if (x + dx[11] <= 4) {
            if (!board[y + dy[3]][x + dx[3]].equals("X") && board[y + dy[11]][x + dx[11]].equals("P")) {
                return false;
            }
        }
        if (x + dx[4] >= 0 && y + dy[4] >= 0) {
            if (!board[y + dy[2]][x + dx[2]].equals("X") || !board[y + dy[0]][x + dx[0]].equals("X")) {
                if (board[y + dy[4]][x + dx[4]].equals("P")) {
                    return false;
                }
            }
        }
        if (x + dx[5] <= 4 && y + dy[5] >= 0) {
            if (!board[y + dy[0]][x + dx[0]].equals("X") || !board[y + dy[3]][x + dx[3]].equals("X")) {
                if (board[y + dy[5]][x + dx[5]].equals("P")) {
                    return false;
                }
            }
        }
        if (x + dx[6] >= 0 && y + dy[6] <= 4) {
            if (!board[y + dy[2]][x + dx[2]].equals("X") || !board[y + dy[1]][x + dx[1]].equals("X")) {
                if (board[y + dy[6]][x + dx[6]].equals("P")) {
                    return false;
                }
            }
        }
        if (x + dx[7] <= 4 && y + dy[7] <= 4) {
            if (!board[y + dy[1]][x + dx[1]].equals("X") || !board[y + dy[3]][x + dx[3]].equals("X")) {
                if (board[y + dy[7]][x + dx[7]].equals("P")) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Programmers81302 programmers81302 = new Programmers81302();
        int[] solution = programmers81302.solution(
                new String[][]{
                        {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
                }
        );
        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
