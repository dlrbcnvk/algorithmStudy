package programmers.lv2;

/**
 * N-Queen
 * 스택을 이용해서 풀까..?
 * 일단 졸려서 자야지..
 * 미해결
 */
public class Programmers12952 {

    boolean[][] marked;
    int n;
    int count = 0;

    public boolean condition(int x, int y) {
        for (int i = 0; i < n; i++) {
            if (y != i && marked[x][i]) {
                return false;
            }
            if (x != i && marked[i][y]) {
                return false;
            }
        }

        return true;
    }

    public void go(int row) {

        if (row == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (condition(row, i)) {
                marked[row][i] = true;
                go(row + 1);
                marked[row][i] = false;
            }
        }
    }

    public int solution(int n) {

        this.marked = new boolean[n][n];
        this.n = n;

        for (int i = 0; i < n; i++) {
            marked[0][i] = true;
            go(1);
            marked[0][i] = false;
        }

        return count;
    }

    public static void main(String[] args) {
        Programmers12952 programmers12952 = new Programmers12952();
        int solution = programmers12952.solution(4);
        System.out.println(solution);
    }
}
