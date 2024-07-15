package programmers.lv2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 기능 개발
 */
public class Programmers42586_2 {
    public int[] solution(int[] progresses, int[] speeds) {

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int progress = progresses[i];
            int speed = speeds[i];
            int days;
            if ((100 - progress) % speed == 0) {
                days = (100 - progress) / speed;
            } else {
                days = (100 - progress) / speed + 1;
            }
            queue.add(days);
        }

        List<Integer> releaseList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int releaseCount = 0;
            int poll = queue.poll();
            releaseCount++;

            while (!queue.isEmpty()) {
                int peek = queue.peek();
                if (poll >= peek) {
                    queue.poll();
                    releaseCount++;
                } else {
                    break;
                }
            }

            releaseList.add(releaseCount);
        }
        return releaseList.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Programmers42586_2 programmers425862 = new Programmers42586_2();
        int[] solution = programmers425862.solution(
                new int[]{95, 90, 99, 99, 80, 99},
                new int[]{1,1,1,1,1,1}
        );

        for (int value : solution) {
            System.out.print(value + " ");
        }
    }
}
