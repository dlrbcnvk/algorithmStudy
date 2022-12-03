package programmers.lv2;

import java.util.Stack;

/**
 * 짝지어 제거하기
 * 스택 사용으로 O(n)에 해결
 */
public class Programmers12973 {

    public int solution(String s) {

        String[] split = s.split("");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            if (stack.isEmpty()) {
                stack.add(split[i]);
            } else {
                String peek = stack.peek();
                if (split[i].equals(peek)) {
                    stack.pop();
                } else {
                    stack.add(split[i]);
                }
            }
        }
        if (stack.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Programmers12973 programmers12973 = new Programmers12973();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            s.append("cdcd");
        }
        int solution = programmers12973.solution(s.toString());
        System.out.println(solution);
    }
}
