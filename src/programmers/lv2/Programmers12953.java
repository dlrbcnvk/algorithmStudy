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
            int value = initialValue;
            for (Integer divisor : divisors) {
                for (int i = 1; i < copiedArr.length; i++) {
                    if (copiedArr[i] % divisor == 0) {
                        // 약수를 내림차순으로 돌다가 copiedArr(첫 원소 제외한)원소들 중 한 개라도 나누어 떨어진다면
                        // 그때의 divisor로 'ㄴ'자 만들기
                        value = divisor;
                        break;
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
                new int[]{12,32,45,67,72}
        );
        System.out.println(solution);
    }
}
