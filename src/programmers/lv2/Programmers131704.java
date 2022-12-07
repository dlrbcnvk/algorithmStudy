package programmers.lv2;

import java.util.Stack;

/**
 * 택배 상자
 */
public class Programmers131704 {

    public int solution(int[] order) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        for (int i = 1; i <= order.length; i++) {
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && stack.peek() == order[idx]) {
                    stack.pop();
                    idx++;
                    answer++;
                }
            }

            if (i == order[idx]) {
                idx++;
                answer++;
            } else {
                stack.push(i);
            }
        }

        while (!stack.isEmpty() && stack.peek() == order[idx]) {
            stack.pop();
            idx++;
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers131704 programmers131704 = new Programmers131704();
        int solution = programmers131704.solution(new int[]{4,3,1,2,5});
        System.out.println(solution);
    }
}
