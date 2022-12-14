package programmers.lv1;

import java.util.Stack;

/**
 * 햄버거 만들기
 * 스택..?!
 * 미해결. 집안일 하다가 장보러 나감. ㅃㅇ
 */
public class Programmers133502 {

    Stack<Integer> stack;
    Stack<Integer> tempStack;
    int count = 0;

    public void moveToStack() {
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    public boolean canMakeHamburger() {
        if (stack.size() < 4) {
            return false;
        }

        tempStack = new Stack<>();
        Integer pop = stack.pop();
        tempStack.push(pop);
        if (pop != 1) {
            moveToStack();
            return false;
        }

        pop = stack.pop();
        tempStack.push(pop);
        if (pop != 3) {
            moveToStack();
            return false;
        }

        pop = stack.pop();
        tempStack.push(pop);
        if (pop != 2) {
            moveToStack();
            return false;
        }

        pop = stack.pop();
        tempStack.push(pop);
        if (pop != 1) {
            moveToStack();
            return false;
        }

        return true;
    }

    public int solution(int[] ingredient) {

         stack = new Stack<>();
        for (int i = 0; i < ingredient.length; i++) {
            stack.push(ingredient[i]);
            if (ingredient[i] == 1) {
                while (true) {
                    boolean canMake = canMakeHamburger();
                    if (canMake) {
                        count++;
                    } else {
                        break;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Programmers133502 programmers133502 = new Programmers133502();
        int solution = programmers133502.solution(
                new int[]{2, 1, 1, 2, 3, 1, 2, 3, 1}
        );
        System.out.println(solution);
    }
}
