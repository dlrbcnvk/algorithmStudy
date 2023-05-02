package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 숫자 변환하기
 * 뒤에서부터 하니까 AC
 * 2023.04.30
 * 곱해가는 대신(시간초과) 나눠가는 것이 하나의 방법
 */
public class Programmers154538 {

    private static class Status {
        int number;
        int count;

        public Status(int number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Status{" +
                    "number=" + number +
                    ", count=" + count +
                    '}';
        }
    }

    public int solution(int x, int y, int n) {

        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(y, 0));
        while (!queue.isEmpty()) {
            Status poll = queue.poll();
            int number = poll.number;
            int count = poll.count;
//            System.out.println(poll);

            if (number == x) {
                return count;
            }

            if (number - n >= x) {
                queue.add(new Status(number - n, count + 1));
            }
            if (number % 2 == 0 && number / 2 >= x) {
                queue.add(new Status(number / 2, count + 1));
            }
            if (number % 3 == 0 && number / 3 >= x) {
                queue.add(new Status(number / 3, count + 1));
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Programmers154538 programmers154538 = new Programmers154538();
        int solution = programmers154538.solution(10, 40, 5);
        System.out.println(solution);
    }
}
