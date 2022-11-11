package programmers.lv2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 최솟값 만들기
 * Arrays.stream(B).sorted().toArray() -> 효율성 테스트 2 실패 (시간초과)
 */
public class Programmers12941 {

    public int solution(int []A, int []B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[A.length - 1 - i];
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers12941 programmers12941 = new Programmers12941();
        int result = programmers12941.solution(
                new int[]{1, 4, 2},
                new int[]{5, 4, 4});
        System.out.println(result);
    }
}
