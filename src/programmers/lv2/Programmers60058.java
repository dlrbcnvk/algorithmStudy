package programmers.lv2;

import java.util.Stack;

/**
 * 괄호 변환
 * 문제에서 하라는 대로 재귀 구현 하기
 */
public class Programmers60058 {

    public String solution(String p) {
        if (isRightParenthesis(p)) return p;

        return algorithm(p);
    }

    private String algorithm(String w) {
        if (w.isEmpty()) return "";

        int endIdx = 1;
        while (endIdx <= w.length()) {
            String u = w.substring(0, endIdx);
            if (isBalanced(u)) {
                String v = w.substring(endIdx);
                if (isRightParenthesis(u)) {
                    u += algorithm(v);
                    return u;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("(");
                    sb.append(algorithm(v));
                    sb.append(")");
                    u = u.substring(1);
                    u = u.substring(0, u.length() - 1);
                    String[] split = u.split("");
                    for (String s : split) {
                        if (s.equals("(")) {
                            sb.append(")");
                        } else if (s.equals(")")) {
                            sb.append("(");
                        }
                    }
                    return sb.toString();
                }
            }
            endIdx++;
        }

        return w;
    }


    private boolean isRightParenthesis(String str) {
        Stack<String> stack = new Stack<>();
        String[] split = str.split("");
        for (String s : split) {
            if (s.equals("(")) {
                stack.add(s);
            } else if (s.equals(")") && !stack.isEmpty()) {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private boolean isBalanced(String str) {
        String[] split = str.split("");
        int openCount = 0;
        int closeCount = 0;
        for (String s : split) {
            if (s.equals("(")) {
                openCount++;
            } else if (s.equals(")")) {
                closeCount++;
            }
        }
        return openCount == closeCount;
    }

    public static void main(String[] args) {
        Programmers60058 programmers60058 = new Programmers60058();
        String solution = programmers60058.solution("()))((()");
        System.out.println(solution);
    }
}
