package programmers.lv1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 두 개 뽑아서 더하기
 */
public class Programmers68644 {
    public int[] solution(int[] numbers) {

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        int[] result = set.stream().mapToInt(i -> i).toArray();
        Arrays.sort(result);

        return result;
    }

    public static void main(String[] args) {
        Programmers68644 programmers68644 = new Programmers68644();
        int[] solution = programmers68644.solution(
                new int[]{2,1,3,4,1}
        );

        for (int num : solution) {
            System.out.print(num + " ");
        }
    }
}
