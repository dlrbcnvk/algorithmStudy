package programmers.lv1;

import java.util.Stack;

/**
 * 같은 숫자는 싫어
 */
public class Programmers12906 {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        stack.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            Integer peek = stack.peek();
            if (arr[i] != peek) {
                stack.add(arr[i]);
            }
        }
        int[] answer = new int[stack.size()];
        int idx = stack.size() - 1;
        while (!stack.isEmpty()) {
            answer[idx] = stack.pop();
            idx--;
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers12906 programmers12906 = new Programmers12906();
        int[] solution = programmers12906.solution(new int[]{1,1,3,3,0,1,1});
        for (int num : solution) {
            System.out.print(num + " ");
        }
    }
}
