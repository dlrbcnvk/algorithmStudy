package programmers.pccp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 카페 확장
 * https://school.programmers.co.kr/learn/courses/15009/lessons/121689
 */
public class Programmers121689 {

    Queue<Integer> clientQueue = new LinkedList<>();

    public int solution(int[] menu, int[] order, int k) {

        int orderIdx = 0;
        int time = 0;
        int endTime = -1;
        int maxCount = 0;
        boolean isOngoing = false;
        while(true) {

            //나갈 사람 있으면 먼저 나가고
            if (!clientQueue.isEmpty() && time == endTime) {
                clientQueue.poll();
                isOngoing = false;
            }

            //들어올 타이밍이라면 들어온다
            if (time % k == 0 && orderIdx < order.length) {
                clientQueue.add(order[orderIdx]);
                orderIdx++;
            }

            // 주문을 받는다.
            if (!clientQueue.isEmpty() && !isOngoing) {
                endTime = time + menu[clientQueue.peek()];
                isOngoing = true;
            }

            maxCount = Math.max(clientQueue.size(), maxCount);

            if (orderIdx >= order.length && clientQueue.isEmpty()) {
                break;
            }
            time++;
        }


        return maxCount;
    }
}
