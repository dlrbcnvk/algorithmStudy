package programmers.lv3;

import java.util.Arrays;

/**
 * 최고의 집합
 * 자연수 n개로 이루어진 '중복집합' 중...
 * {1,1,1,1} 가능
 * 숫자를 최대한 가운데 수로 모아야 곱셈이 크게 나옴
 * s가 n으로 나누어 떨어질 경우 그 몫으로 모두 채움
 * 나머지가 생기는 경우, 그 나머지를 요소에 되는대로 1씩 뿌려준다.
 */
public class Programmers12938 {
    public int[] solution(int n, int s) {

        if (n > s) {
            return new int[]{-1};
        }

        int[] result = new int[n];

        int num = s / n;
        Arrays.fill(result, num);

        if (s % n != 0) {
            for (int i = 0; i < s % n; i++) {
                result[i] += 1;
            }
        }

        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        Programmers12938 programmers12938 = new Programmers12938();
        int[] solution = programmers12938.solution(2,8);

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
