package programmers.lv3;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 징검다리 건너기
 * 효율성 테스트 시간초과..?!
 * 미해결
 */
public class Programmers64062 {

    HashMap<Integer, Integer> restMap;
    HashMap<Integer, Integer> jumpMap;
    HashMap<Integer, Integer> preMap;
    int[] stones;
    int n;
    int k;

    public int solution(int[] stones, int k) {

        restMap = new HashMap<>();
        jumpMap = new HashMap<>();
        preMap = new HashMap<>();
        this.stones = stones;
        this.n = stones.length;
        this.k = k;
        for (int i = 0; i < stones.length; i++) {
            restMap.put(i, stones[i]);
            jumpMap.put(i, i + 1);
            preMap.put(i, i - 1);
        }
        jumpMap.put(-1, 0);
        restMap.put(-1, Integer.MAX_VALUE);


        int count = 0;
        while (true) {
            if (!jump()) {
                break;
            }
            count++;

        }

        return count;
    }

    public boolean jump() {
        int idx = -1;
        while (idx < n) {
            if (jumpMap.get(idx) - idx > k) {
                return false;
            }
            Integer rest = restMap.get(idx);
            if (idx != -1) {
                restMap.put(idx, rest - 1);
            }
            if (rest - 1 == 0) {
                Integer pre = preMap.get(idx);
                Integer post = jumpMap.get(idx);
                jumpMap.put(pre, post);
                preMap.put(post, pre);
            }

            Integer to = jumpMap.get(idx);
            idx = to;
        }
        return true;
    }

    public static void main(String[] args) {
        Programmers64062 programmers64062 = new Programmers64062();
        int solution = programmers64062.solution(
                new int[]{2,4,5,3,2,1,4,2,5,1}, 3
        );
        System.out.println(solution);
    }
}
