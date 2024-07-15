package programmers.lv2;

import java.util.Stack;

/**
 * 괄호 회전하기
 */
public class Programmers76502_2 {

    private static char OPEN_1 = '(';
    private static char OPEN_2 = '{';
    private static char OPEN_3 = '[';

    private static char CLOSE_1 = ')';
    private static char CLOSE_2 = '}';
    private static char CLOSE_3 = ']';


    public int solution(String s) {

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                char first = s.charAt(0);
                s = s.substring(1) + first;
            }
            boolean right = isRight(s);
            if (right) {
                count++;
            }
        }

        return count;
    }

    private boolean isRight(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (OPEN_1 == c || OPEN_2 == c || OPEN_3 == c) {
                stack.push(c);
                continue;
            }

            if (CLOSE_1 == c || CLOSE_2 == c || CLOSE_3 == c) {
                if (stack.isEmpty()) {
                    return false;
                }
                char peek = stack.peek();
                if ((CLOSE_1 == c && OPEN_1 == peek) || (CLOSE_2 == c && OPEN_2 == peek) || (CLOSE_3 == c && OPEN_3 == peek)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Programmers76502_2 programmers765022 = new Programmers76502_2();
        int solution = programmers765022.solution(
                "[](){}"
        );
        System.out.println(solution);
    }
}
