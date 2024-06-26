package programmers.lv1;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 두 개 뽑아서 더하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/68644
 */
public class Programmers68644_2 {

    public int[] solution(int[] numbers) {
        Set<Integer> numberSet = new HashSet<>();
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                numberSet.add(numbers[i] + numbers[j]);
            }
        }
        return numberSet.stream()
                .sorted()
                .mapToInt(i -> i)
                .toArray();
    }

    public static void main(String[] args) {
        Programmers68644_2 programmers686442 = new Programmers68644_2();
        int[] solution = programmers686442.solution(
                new int[]{2,1,3,4,1}
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
