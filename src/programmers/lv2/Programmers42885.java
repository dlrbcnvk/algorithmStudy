package programmers.lv2;

import java.util.Arrays;

/**
 * 구명보트
 * 최소 힙, 최대 힙으로 우선순위 큐 두 개 놓고 했더니 효율성 테스트 시간초과.. -_-
 * 두포인터로 배열 내에서 포인터 조정하여 통과
 */
public class Programmers42885 {
    public int solution(int[] people, int limit) {

        int count = 0;
        int maxIdx = people.length - 1;
        int minIdx = 0;
        Arrays.sort(people);

        int weight;
        while (minIdx <= maxIdx) {
            weight = people[maxIdx];
            count++;

            while (minIdx < maxIdx) {
                if (weight + people[minIdx] <= limit) {
                    weight += people[minIdx];
                    minIdx++;
                    if (minIdx == maxIdx) {
                        return count;
                    }
                } else {
                    break;
                }
            }
            maxIdx--;
        }
        return count;
    }

    public static void main(String[] args) {
        Programmers42885 programmers42885 = new Programmers42885();
        int solution = programmers42885.solution(
                new int[]{70, 80,  50}, 100
        );
        System.out.println(solution);
    }
}
