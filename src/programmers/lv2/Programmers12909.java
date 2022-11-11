package programmers.lv2;

import java.util.Stack;

/**
 * 올바른 괄호
 * split("") 쓰니까 효율성에서 시간초과 나네..😓
 */
public class Programmers12909 {

    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<Character>();

        for (Character str : s.toCharArray()) {
            if (str == '(') {
                stack.push(str);
            } else if (str == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers12909 programmers12909 = new Programmers12909();
        boolean result = programmers12909.solution("(()(");
        System.out.println(result);
    }
}
