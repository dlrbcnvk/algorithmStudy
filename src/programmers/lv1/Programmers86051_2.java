package programmers.lv1;


import java.util.HashSet;
import java.util.Set;

/**
 * 없는 숫자 더하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/86051
 */
public class Programmers86051_2 {

    public int solution(int[] numbers) {

        int answer = 0;
        Set<Integer> numberSet = new HashSet<>();
        for (int i = 0; i <= 9; i++) {
            numberSet.add(i);
        }
        for (int number : numbers) {
            numberSet.remove(number);
        }

        return numberSet.stream()
                .mapToInt(i -> i)
                .sum();
    }

    public static void main(String[] args) {

    }
}
