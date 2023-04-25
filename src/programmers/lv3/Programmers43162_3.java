package programmers.lv3;

import java.util.Stack;

/**
 * 네트워크
 * 깊이 우선 탐색 (stack)
 */
public class Programmers43162_3 {

    public int solution(int n, int[][] computers) {

        boolean[] marked = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        for (int i = 0; i < n; i++) {

            if (!marked[i]) {
                answer++;
                marked[i] = true;
                stack.push(i);
                while (!stack.isEmpty()) {
                    Integer pop = stack.pop();
                    for (int j = 0; j < n; j++) {
                        if (pop == j) continue;

                        if (computers[pop][j] == 1 && !marked[j]) {
                            marked[j] = true;
                            stack.push(j);
                        }
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers43162_3 programmers43162_3 = new Programmers43162_3();
        int solution = programmers43162_3.solution(
                3, new int[][]{{1,1,0},{1,1,0},{0,0,1}}
        );
        System.out.println(solution);
    }
}
