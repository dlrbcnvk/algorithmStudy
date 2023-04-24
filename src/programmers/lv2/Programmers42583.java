package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 다리를 지나는 트럭
 */
public class Programmers42583 {

    static class Truck {
        int entryTime;
        int weight;

        public Truck(int entryTime, int weight) {
            this.entryTime = entryTime;
            this.weight = weight;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Truck> queue = new LinkedList<>();
        int time = 0;
        int idx = 0;
        int currentWeight = 0;

        do {
            time++;
            // 뺄 거 있으면 먼저 빼기 (1초에 한 개만 뺄 수 있음)
            if (!queue.isEmpty()) {
                Truck peek = queue.peek();
                if (time - peek.entryTime == bridge_length) {
                    Truck poll = queue.poll();
                    currentWeight -= poll.weight;
                }
            }

            if (idx >= truck_weights.length) {
                continue;
            }
            // 넣을 수 있으면 넣기
            if (currentWeight + truck_weights[idx] <= weight) {
                queue.add(new Truck(time, truck_weights[idx]));
                currentWeight += truck_weights[idx];
                idx++;
            }
        } while (!queue.isEmpty());



        return time;
    }

    public static void main(String[] args) {
        Programmers42583 programmers42583 = new Programmers42583();
        int solution = programmers42583.solution(
                2, 10, new int[]{7, 4, 5, 6}
        );
        System.out.println(solution);
    }
}
