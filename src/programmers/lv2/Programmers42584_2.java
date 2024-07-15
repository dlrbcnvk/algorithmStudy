package programmers.lv2;

import java.util.Arrays;
import java.util.Stack;

public class Programmers42584_2 {

    class Info {
        private int price;
        private int idx;
        private int notDownTime;

        public Info(int price, int idx, int notDownTime) {
            this.price = price;
            this.idx = idx;
            this.notDownTime = notDownTime;
        }

        public int getPrice() {
            return price;
        }

        public int getIdx() {
            return idx;
        }

        public int getNotDownTime() {
            return notDownTime;
        }

        public void setNotDownTime(int notDownTime) {
            this.notDownTime = notDownTime;
        }
    }

    public int[] solution(int[] prices) {

        Stack<Info> stack = new Stack<>();
        Info[] infos = new Info[prices.length];
        for (int i = 0; i < prices.length; i++) {
            infos[i] = new Info(prices[i], i, 0);
        }
        for (Info info : infos) {
            if (stack.isEmpty()) {
                stack.push(info);
            } else {
                while (!stack.isEmpty()) {
                    Info peek = stack.peek();
                    if (peek.getPrice() > info.getPrice()) {
                        peek.setNotDownTime(info.getIdx() - peek.getIdx());
                        stack.pop();
                    } else {
                        break;
                    }
                }
                stack.push(info);
            }
        }

        while (!stack.isEmpty()) {
            Info peek = stack.peek();

            peek.setNotDownTime(prices.length - 1 - peek.getIdx());
            stack.pop();
        }

        return Arrays.stream(infos)
                .mapToInt(Info::getNotDownTime)
                .toArray();
    }

    public static void main(String[] args) {
        Programmers42584_2 programmers425842 = new Programmers42584_2();
        int[] solution = programmers425842.solution(
                new int[]{1,2,3,2,3}
        );

        for (int value : solution) {
            System.out.print(value + " ");
        }
    }
}
