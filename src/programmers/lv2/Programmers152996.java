package programmers.lv2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 시소 짝꿍
 * https://school.programmers.co.kr/learn/courses/30/lessons/152996
 * 미해결
 */
public class Programmers152996 {

    public long solution(int[] weights) {

        // weight 별 인원 수
        Map<Integer, Integer> weightCountMap = new HashMap<>();
        for (int weight : weights) {
            if (weightCountMap.containsKey(weight)) {
                Integer count = weightCountMap.get(weight);
                weightCountMap.put(weight, count + 1);
            } else {
                weightCountMap.put(weight, 1);
            }
        }

        long count = 0;
        long count23And34 = 0;

        Set<Integer> weightSet = weightCountMap.keySet();
        // weight 같은 사람 여러명 있는 경우 n(n-1)/2
        for (Integer weight : weightSet) {
            Integer weightCount = weightCountMap.get(weight);
            if (weightCount > 1) {
                count += weightCount * (weightCount - 1) / 2;
            }
        }

        List<Integer> weightKeyList = weightSet.stream().collect(Collectors.toList());
        Collections.sort(weightKeyList);

        for (Integer weight : weightKeyList) {
            Integer weightCount = weightCountMap.get(weight);

            if (weightCountMap.containsKey(weight * 2)) {
                count += weightCount * weightCountMap.get(weight * 2);
            }

            if (weightCountMap.containsKey(weight * 3)) {
                count += weightCount * weightCountMap.get(weight * 3);
            }

            if (weightCountMap.containsKey(weight * 4)) {
                count += weightCount * weightCountMap.get(weight * 4);
            }

            if ((weight * 3) % 2 == 0 && weightCountMap.containsKey(weight * 3 / 2)) {
                count23And34 += weightCount * weightCountMap.get(weight * 3 / 2);
            }

            if ((weight * 4) % 3 == 0 && weightCountMap.containsKey(weight * 4 / 3)) {
                count23And34 += weightCount * weightCountMap.get(weight * 4 / 3);
            }

            if ((weight * 2) % 3 == 0 && weightCountMap.containsKey(weight * 2 / 3)) {
                count23And34 += weightCount * weightCountMap.get(weight * 2 / 3);
            }

            if ((weight * 3) % 4 == 0 && weightCountMap.containsKey(weight * 3 / 4)) {
                count23And34 += weightCount * weightCountMap.get(weight * 3 / 4);
            }
        }

        count23And34 = count23And34 / 2;
        return count + count23And34;
    }


    // 단순 이중 for 루프 -> 시간초과
    public long solution1(int[] weights) {

        Arrays.sort(weights);
        int length = weights.length;

        long count = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {

                if (weights[i] * 4 < weights[j]) {
                    break;
                }

                if (weights[i] == weights[j]) {
                    count++;
                    continue;
                }

                if (weights[i] * 3 == weights[j] * 2 ||
                        weights[i] * 4 == weights[j] * 2 ||
                        weights[i] * 4 == weights[j] * 3) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Programmers152996 programmers152996 = new Programmers152996();

        long solution = programmers152996.solution(
                new int[]{100,180,360,100,270}
        );
        System.out.println(solution);
    }
}
