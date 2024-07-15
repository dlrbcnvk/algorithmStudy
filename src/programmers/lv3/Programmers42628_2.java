package programmers.lv3;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 이중우선순위큐
 */
public class Programmers42628_2 {

    public int[] solution(String[] operations) {

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        for (String operation : operations) {
            Integer value = Integer.parseInt(operation.substring(2));
            if (operation.startsWith("I")) {
                maxPQ.add(value);
                minPQ.add(value);
            } else if ("D 1".equals(operation)) {
                Integer poll = maxPQ.poll();
                if (poll != null) {
                    minPQ.remove(poll);
                }

            } else if ("D -1".equals(operation)) {
                Integer poll = minPQ.poll();
                if (poll != null) {
                    maxPQ.remove(poll);
                }
            }
        }

        if (maxPQ.isEmpty() && minPQ.isEmpty()) {
            return new int[]{0, 0};
        }

        return new int[]{maxPQ.poll(), minPQ.poll()};
    }

    public static void main(String[] args) {
        Programmers42628_2 programmers426282 = new Programmers42628_2();
        int[] solution = programmers426282.solution(new String[]{
                "I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"
        });

        for (int s : solution) {
            System.out.print(s + " ");
        }
    }
}
