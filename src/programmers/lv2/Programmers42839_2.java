package programmers.lv2;

import java.util.HashSet;
import java.util.Set;

/**
 * 소수 찾기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42839
 */
public class Programmers42839_2 {

    public int solution(String numbers) {

        char[] chars = numbers.toCharArray();
        boolean[] marked = new boolean[chars.length];
        Set<Integer> primeSet = new HashSet<>();

        go(0, "", chars, marked, primeSet);

        return primeSet.size();
    }

    public void go(int count, String str, char[] chars, boolean[] marked, Set<Integer> primeSet) {

        if (!str.isEmpty()) {
            int num = Integer.parseInt(str);

            boolean isPrime = isPrime(num);
            if (isPrime) {
                primeSet.add(num);
            }
        }

        if (count == chars.length) {
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (!marked[i]) {
                marked[i] = true;
                go(count + 1, str + chars[i], chars, marked, primeSet);
                marked[i] = false;
            }
        }
    }

    // 소수 구하기
    public boolean isPrime(int num) {
        if (num == 1 || num == 0) {
            return false;
        }
        if (num == 2 || num == 3) {
            return true;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Programmers42839_2 programmers428392 = new Programmers42839_2();
        int solution = programmers428392.solution("1231");
        System.out.println(solution);
    }
}
