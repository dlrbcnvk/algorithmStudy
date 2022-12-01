package programmers.lv1;

import java.util.Stack;

/**
 * 크레인 인형뽑기 게임
 */
public class Programmers64061 {

    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (int move : moves) {
            move = move - 1;
            int value = 0;
            for (int i = 0; i < board.length; i++) {
                value = board[i][move];
                board[i][move] = 0;
                if (value != 0) {
                    break;
                }
            }

            if (!stack.isEmpty() && value != 0) {
                Integer peek = stack.peek();
                if (value == peek) {
                    stack.pop();
                    answer += 2;
                } else {
                    stack.push(value);
                }
            } else {
                stack.push(value);
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers64061 programmers64061 = new Programmers64061();
        int solution = programmers64061.solution(
                new int[][]{
                        {0,0,0,0,0},
                        {0,0,1,0,3},
                        {0,2,5,0,1},
                        {4,2,4,4,2},
                        {3,5,1,3,1}
                },
                new int[]{1,5,3,5,1,2,1,4}
        );
        System.out.println(solution);
    }
}
