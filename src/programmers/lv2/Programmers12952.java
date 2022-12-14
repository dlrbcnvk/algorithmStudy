package programmers.lv2;

import java.util.Stack;

/**
 * N-Queen
 * use stack
 */
public class Programmers12952 {

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    boolean[][] marked;
    int n;
    int count = 0;
    Stack<Point> stack;

    public boolean condition(int x, int y) {
        for (Point point : stack) {
            if (point.x == x || point.y == y) {
                return false;
            }
            if (point.x + point.y == x + y) {
                return false;
            }
            if (point.x - point.y == x - y) {
                return false;
            }
        }
        return true;
    }

    public void go(int row) {

        if (stack.size() == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (condition(row, i)) {
                stack.push(new Point(row, i));
                go(row + 1);
                stack.pop();
            }
        }
    }

    public int solution(int n) {

        this.marked = new boolean[n][n];
        this.n = n;
        stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            stack.push(new Point(0, i));
            go(1);
            stack.pop();
        }

        return count;
    }

    public static void main(String[] args) {
        Programmers12952 programmers12952 = new Programmers12952();
        int solution = programmers12952.solution(4);
        System.out.println(solution);
    }
}
