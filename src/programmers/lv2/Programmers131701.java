package programmers.lv2;

import java.util.HashSet;
import java.util.Set;

/**
 * 연속 부분 수열 합의 개수
 * Set 이용
 * 1개연속합, 2개연속합, 3개연속합, ...
 * 각 연속합에 대해 맨 뒤 다음 꺼 더하면서 뒤 포인터 한 칸 밀고, 맨 앞 꺼를 빼면서 앞 포인터 한칸 땡기고.
 */
public class Programmers131701 {
    public int solution(int[] elements) {

        int n = elements.length;
        Set<Integer> set = new HashSet<>();

        // 1개 짜리 부분수열
        for (Integer element : elements) {
            set.add(element);
        }

        for (int num = 2; num <= n; num++) {
            int startIdx = 0;
            int endIdx = num - 1;
            Integer sum = 0;
            for (int i = startIdx; i <= endIdx; i++) {
                sum += elements[i];
            }
            set.add(sum);

            int count = 1;
            while (count < n) {
                count++;
                endIdx = (endIdx + 1) == n ? 0 : endIdx + 1;
                sum += elements[endIdx];
                sum -= elements[startIdx];
                startIdx = (startIdx + 1) == n ? 0 : startIdx + 1;
                set.add(sum);
            }
        }


        return set.size();
    }

    public static void main(String[] args) {
        Programmers131701 programmers131701 = new Programmers131701();
        int solution = programmers131701.solution(new int[]{7,9,1,1,4});
        System.out.println(solution);
    }
}
