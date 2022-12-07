package programmers.lv2;

import java.util.Stack;

/**
 * 주식가격
 */
public class Programmers42584 {

    static class Record {
        int time;
        int price;
        int downTime;

        public Record(int time, int price, int downTime) {
            this.time = time;
            this.price = price;
            this.downTime = downTime;
        }

    }

    public int[] solution(int[] prices) {

        Stack<Record> stack = new Stack<>();
        Record[] records = new Record[prices.length];
        for (int i = 0; i < records.length; i++) {
            records[i] = new Record(i, prices[i], records.length - 1);
        }

        for (int i = 0; i < records.length; i++) {
            Record record = records[i];

            while (!stack.isEmpty()) {
                Record peek = stack.peek();
                if (peek.price > record.price) {
                    peek.downTime = record.time;
                    stack.pop();
                } else {
                    break;
                }
            }


            stack.push(record);
        }


        int[] answer = new int[records.length];
        for (int i = 0; i < records.length; i++) {
            answer[i] = records[i].downTime - records[i].time;
        }


        return answer;
    }

    public static void main(String[] args) {
        Programmers42584 programmers42584 = new Programmers42584();
        int[] solution = programmers42584.solution(
                new int[]{1,2,3,2,3}
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
