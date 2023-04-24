package programmers.lv1;

import java.util.HashSet;
import java.util.Set;

/**
 * 없는 숫자 더하기
 */
public class Programmers86051 {

    public int solution(int[] numbers) {
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        for (int i : numbers) {
            set.add(i);
        }

        for (int i = 1; i <= 9; i++) {
            if (!set.contains(i)) {
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Programmers86051 programmers86051 = new Programmers86051();
        int solution = programmers86051.solution(new int[]{1,2,3,4,6,7,8,0});
        System.out.println(solution);
    }
}
