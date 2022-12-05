package programmers.lv2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [1차] 프렌즈4블록
 */
public class Programmers17679 {

    char[][] charBoard;
    int m;
    int n;
    int count = 0;

    public int solution(int m, int n, String[] board) {

        this.m = m;
        this.n = n;
        charBoard = new char[m][n];
        for (int i = 0; i < m; i++) {
            char[] chars = board[i].toCharArray();
            charBoard[i] = chars;
        }

        while (true) {
            ArrayList<Integer[]> search = search2x2();
            if (search.isEmpty()) {
                break;
            }

            for (Integer[] point : search) {
                Integer x = point[0];
                Integer y = point[1];

                checkAndCount(x, y);
                checkAndCount(x, y + 1);
                checkAndCount(x + 1, y);
                checkAndCount(x + 1, y + 1);
            }

            moveDown();
        }

        return count;
    }

    public void checkAndCount(int x, int y) {
        if (charBoard[x][y] != '0') {
            charBoard[x][y] = '0';
            count++;
        }
    }

    public void moveDown() {
        for (int j = 0; j < n; j++) {
            Queue<String> queue = new LinkedList<>();
            for (int i = m - 1; i >= 0; i--) {
                if (charBoard[i][j] != '0') {
                    queue.add(String.valueOf(charBoard[i][j]));
                }
            }
            for (int i = m - 1; i >= 0; i--) {
                if (!queue.isEmpty()) {
                    char block = queue.poll().charAt(0);
                    charBoard[i][j] = block;
                } else {
                    charBoard[i][j] = '0';
                }
            }
        }
    }

    public ArrayList<Integer[]> search2x2() {
        ArrayList<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                char c = charBoard[i][j];
                if (c == '0') {
                    continue;
                }
                if (charBoard[i][j + 1] == c && charBoard[i + 1][j] == c && charBoard[i + 1][j + 1] == c) {
                    result.add(new Integer[]{i, j});
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Programmers17679 programmers17679 = new Programmers17679();
        int solution = programmers17679.solution(
                4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}
        );

        System.out.println(solution);
    }
}
