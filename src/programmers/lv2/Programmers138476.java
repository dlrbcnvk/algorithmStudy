package programmers.lv2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * 귤 고르기
 * compareTo 쓸 거면, this가 작은 경우(return -1), 큰 경우(return 1), 같은 경우(return 0) 셋 다 써주기.
 * 안 쓰면 런타임 에러 날 수 있음. 10만개 원소의 배열을 넣으니 알게 됨.
 */
public class Programmers138476 {

    class Tangerine implements Comparable<Tangerine> {
        int size;
        int count;

        public Tangerine(int size, int count) {
            this.size = size;
            this.count = count;
        }

        @Override
        public int compareTo(Tangerine tangerine) {
            if (this.count > tangerine.count) {
                return -1;
            } else if (this.count < tangerine.count) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int solution(int k, int[] tangerine) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : tangerine) {
            if (map.containsKey(i)) {
                Integer value = map.get(i);
                map.put(i, value + 1);
            } else {
                map.put(i, 1);
            }
        }

        Set<Integer> set = map.keySet();
        Tangerine[] arr = new Tangerine[set.size()];
        int idx = 0;
        for (Integer key : set) {
            Tangerine t = new Tangerine(key, map.get(key));
            arr[idx] = t;
            idx++;
        }
        Arrays.sort(arr);

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count += arr[i].count;
            if (count >= k) {
                return i + 1;
            }
        }

        return 1;
    }

    public static void main(String[] args) {
        Programmers138476 programmers138476 = new Programmers138476();
        int[] tempArr = new int[100000];
        for (int i = 0; i < 100000; i++) {
            tempArr[i] = (int)(Math.random() * 10000000);

        }
        int solution = programmers138476.solution(
                7, tempArr
        );
        System.out.println(solution);
    }
}
