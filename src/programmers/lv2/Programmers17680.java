package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 캐시
 * LRU(Least Recently Used)
 * 큐 이용
 */
public class Programmers17680 {
    public int solution(int cacheSize, String[] cities) {
        int time = 0;

        Queue<String> queue = new LinkedList<>();
        for (String city : cities) {
            city = city.toLowerCase();
            if (queue.contains(city)) {
                queue.remove(city);
                queue.add(city);
                time += 1;
            } else {
                if (queue.size() == cacheSize) {
                    queue.poll();
                }
                if (cacheSize > 0) {
                    queue.add(city);
                }
                time += 5;
            }
        }

        return time;
    }

    public static void main(String[] args) {
        Programmers17680 programmers17680 = new Programmers17680();
        int solution = programmers17680.solution(
                2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}
        );
        System.out.println(solution);
    }
}
