package programmers.lv0;

import java.util.Arrays;

/**
 * 최댓값 만들기
 */
public class Programmers120862 {

    public int solution(int[] numbers) {
        Arrays.sort(numbers);
        int n = numbers.length;
        int lastTwo = numbers[n-1] * numbers[n-2];
        int firstTwo = numbers[0] * numbers[1];
        int bothSideEachOne = numbers[0] * numbers[n - 1];

        return Math.max(Math.max(lastTwo, firstTwo), bothSideEachOne);
    }

    public static void main(String[] args) {
        Programmers120862 programmers120862 = new Programmers120862();
        int solution = programmers120862.solution(
                new int[]{10, 20, 30, 5, 5, 20, 5}
        );
        System.out.println(solution);
    }

}
