package programmers.lv2;

import java.util.HashSet;

/**
 * 소수 찾기
 * 완전탐색
 */
public class Programmers42839 {

    private boolean[] marked;
    HashSet<Integer> integerSet;
    char[] chars;

    public boolean isPrime(int n) {
        if (n == 0 || n == 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void go(String str) {
        int parseInt = Integer.parseInt(str);
        if (isPrime(parseInt)) {
            integerSet.add(parseInt);
        }
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) {
                marked[i] = true;
                go(str + chars[i]);
                marked[i] = false;
            }
        }
    }

    public int solution(String numbers) {
        int answer = 0;

        chars = numbers.toCharArray();
        marked = new boolean[chars.length];
        integerSet = new HashSet<>();

        String str = "";
        for (int i = 0; i < marked.length; i++) {
            marked[i] = true;
            go(str + chars[i]);
            marked[i] = false;
        }
        return integerSet.size();
    }

    public static void main(String[] args) {
        Programmers42839 programmers42839 = new Programmers42839();
        int solution = programmers42839.solution("17");
        System.out.println(solution);
    }
}
