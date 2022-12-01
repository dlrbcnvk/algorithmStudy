package programmers.lv1;

/**
 * 콜라 문제
 * 이중 while문
 */
public class Programmers132267 {

    public int solution(int a, int b, int n) {
        int answer = 0;

        while (n >= a) {
            int oddCount = 0;
            while (n >= a) {
                oddCount += n % a;
                int div = n / a;
                n = b * div;
                answer += b * div;
            }
            n += oddCount;
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers132267 programmers132267 = new Programmers132267();
        int solution = programmers132267.solution(3, 1, 20);
        System.out.println(solution);
    }
}
