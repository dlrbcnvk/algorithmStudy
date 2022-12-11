package programmers.lv2;

import java.util.Stack;

/**
 * 괄호 회전하기
 * 스택 사용.
 * 앞글자 떼서 뒤에 붙이는 식으로 반복
 */
public class Programmers76502 {

    public boolean isRightParenthesis(String s) {
        String[] split = s.split("");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            if (str.equals("]") || str.equals(")") || str.equals("}")) {
                if (stack.isEmpty()) {
                    return false;
                }
            }

            if (str.equals("]")) {
                if (stack.peek().equals("[")) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (str.equals(")")) {
                if (stack.peek().equals("(")) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (str.equals("}")) {
                if (stack.peek().equals("{")) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(str);
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            String lastString = s.charAt(0) + "";
            String substring = s.substring(1);
            s = substring + lastString;
            if (isRightParenthesis(s)) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers76502 programmers76502 = new Programmers76502();
        int solution = programmers76502.solution("[](){}");
        System.out.println(solution);
    }
}
