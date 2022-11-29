package programmers.lv0;

import java.util.Arrays;

public class Programmers120847 {
    public int solution(int[] numbers) {
        Arrays.sort(numbers);
        int length = numbers.length;
        return numbers[length - 1] * numbers[length - 2];
    }

    public static void main(String[] args) {
        Programmers120847 programmers120847 = new Programmers120847();
        int solution = programmers120847.solution(new int[]{0,31,24,10,1,9});
        System.out.println(solution);
    }
}
