package programmers.lv2;

import java.util.Stack;

/**
 * 올바른 괄호
 */
public class Programmers12909_2 {

    private static char OPEN = '(';

    boolean solution(String s) {

        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (OPEN == c) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Programmers12909_2 programmers129092 = new Programmers12909_2();
        boolean solution = programmers129092.solution("(()(");
        System.out.println(solution);
    }
}
