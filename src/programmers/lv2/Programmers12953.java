package programmers.lv2;

import java.util.*;

/**
 * N개의 최소공배수
 * 테스트케이스 2개가 안 된다. 왜 안될까
 * 미해결
 */
public class Programmers12953 {

    public List<Integer> getDivisorsInDescendingOrder(Integer num) {
        ArrayList<Integer> result = new ArrayList<>();

        if (num != 1) {
            for (int i = 2; i <= num / 2; i++) {
                if (num % i == 0) {
                    result.add(i);
                }
            }
        }
        result.add(num);
        result.sort(Collections.reverseOrder());
        return result;
    }

    public int solution(int[] arr) {
        int answer = 1;

        Arrays.sort(arr);
        int[] copiedArr = arr;
        while (copiedArr.length != 0) {
            int initialValue = copiedArr[0];
            List<Integer> divisors = getDivisorsInDescendingOrder(copiedArr[0]);
            boolean found = false;
            int value = initialValue;
            for (Integer divisor : divisors) {
                if (!found) {
                    for (int i = 1; i < copiedArr.length; i++) {
                        if (copiedArr[i] % divisor == 0) {
                            found = true;
                            value = divisor;
                        }
                    }
                }
            }
            answer *= value;
            for (int i = 0; i < copiedArr.length; i++) {
                if (copiedArr[i] % value == 0) {
                    copiedArr[i] = copiedArr[i] / value;
                }
            }
            if (initialValue == value) {
                copiedArr = Arrays.copyOfRange(copiedArr, 1, copiedArr.length);
            }
            Arrays.sort(copiedArr);
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers12953 programmers12953 = new Programmers12953();
        int solution = programmers12953.solution(
                new int[]{14,2,7}
        );
        System.out.println(solution);
    }
}
