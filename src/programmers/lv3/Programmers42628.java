package programmers.lv3;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 이중우선순위큐
 * minPQ, maxPQ 유지 관리
 */
public class Programmers42628 {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());

        for (String command : operations) {
            char c = command.charAt(0);
            command = command.substring(2);
            Integer num = Integer.parseInt(command);
            if (c == 'I') {
                maxPQ.add(num);
                minPQ.add(num);
            } else if (c == 'D' && !maxPQ.isEmpty()) {
                if (num == 1) {
                    Integer poll = maxPQ.poll();
                    minPQ.remove(poll);
                } else if (num == -1) {
                    Integer poll = minPQ.poll();
                    maxPQ.remove(poll);
                }
            }
        }

        if (maxPQ.isEmpty()) {
            return new int[]{0, 0};
        }
        int[] answer = new int[]{maxPQ.poll(), minPQ.poll()};

        return answer;
    }

    public static void main(String[] args) {
        Programmers42628 programmers42628 = new Programmers42628();
        int[] solution = programmers42628.solution(
                new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }

        System.out.println();


    }

}
