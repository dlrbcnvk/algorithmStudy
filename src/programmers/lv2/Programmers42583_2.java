package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 다리를 지나는 트럭
 */
public class Programmers42583_2 {

    class Truck {
        private int weight;
        private int startTime;
        private int endTime;


        public Truck(int weight, int startTime, int bridge_length) {
            this.weight = weight;
            this.startTime = startTime;
            this.endTime = this.startTime + bridge_length;
        }

        public int getWeight() {
            return weight;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int truckIdx = 0;
        Queue<Truck> queue = new LinkedList<>();
        int time = 0;
        int queueWeight = 0;
        while (true) {

            if (truckIdx == truck_weights.length && queue.isEmpty()) {
                break;
            }

            if (!queue.isEmpty()) {
                Truck peek = queue.peek();
                if (peek.endTime == time) {
                    Truck poll = queue.poll();
                    queueWeight -= poll.getWeight();
                }
            }

            if (queue.size() < bridge_length && truckIdx < truck_weights.length) {
                Truck truck = new Truck(truck_weights[truckIdx], time, bridge_length);
                if (queueWeight + truck.getWeight() <= weight) {
                    queue.add(truck);
                    queueWeight += truck.getWeight();
                    truckIdx++;
                }
            }

            time++;
        }

        return time;
    }

    public static void main(String[] args) {
        Programmers42583_2 programmers425832 = new Programmers42583_2();
        int solution = programmers425832.solution(
                2, 10, new int[]{7,4,5,6}
        );
        System.out.println(solution);
    }
}
