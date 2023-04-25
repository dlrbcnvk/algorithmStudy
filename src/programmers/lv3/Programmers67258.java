package programmers.lv3;

import java.util.*;

/**
 * 보석 쇼핑
 * 두 포인터로 해결 가능
 */
public class Programmers67258 {

    static class Range implements Comparable<Range> {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getRange() {
            return end - start;
        }

        @Override
        public int compareTo(Range range) {
            if (this.getRange() < range.getRange()) {
                return -1;
            } else if (this.getRange() > range.getRange()) {
                return 1;
            } else {
                if (this.start < range.start) {
                    return -1;
                } else if (this.start > range.start) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public int[] solution(String[] gems) {

        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>(Arrays.asList(gems));
        int n = set.size();
        int gemLength = gems.length;
        int startIdx = 0;
        int endIdx = n;
        PriorityQueue<Range> pq = new PriorityQueue<>();

        for (int i = startIdx; i < endIdx; i++) {
            String gem = gems[i];
            if (!map.containsKey(gem)) {
                map.put(gem, 1);
            } else {
                map.put(gem, map.get(gem) + 1);
            }
        }

        if (endIdx - startIdx == 1) {
            pq.add(new Range(startIdx + 1, startIdx + 1));
        }

        while (startIdx <= gemLength - n) {

            // n 종류가 될 때까지 뒤쪽 늘리기
            while (endIdx < gemLength && map.keySet().size() < n) {
                String gem = gems[endIdx];
                if (!map.containsKey(gem)) {
                    map.put(gem, 1);
                } else {
                    map.put(gem, map.get(gem) + 1);
                }

                endIdx++;
            }

            // n 종류가 유지되는 동안 앞쪽 줄이기
            while (map.keySet().size() == n) {
                String startKey = gems[startIdx];
                if (map.get(startKey) == 1) {
                    pq.add(new Range(startIdx + 1, endIdx));
                    map.remove(startKey);
                } else {
                    // startIdx에 있는 원소를 map에서 count 하나 내리고 startIdx 한칸 전진
                    map.put(startKey, map.get(startKey) - 1);
                }
                startIdx++;
            }

            if (map.keySet().size() < n && endIdx == gemLength) {
                break;
            }

        }

        Range poll = pq.poll();
        return new int[]{poll.start, poll.end};
    }

    public static void main(String[] args) {
        Programmers67258 programmers67258 = new Programmers67258();
        int[] solution = programmers67258.solution(
                new String[]{"a","b"}
        );

        for (int num : solution) {
            System.out.print(num + " ");
        }
    }
}
