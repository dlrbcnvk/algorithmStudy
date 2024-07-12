package programmers.lv2;

/**
 * 피보나치 수
 */
public class Programmers12945_2 {

    public int solution(int n) {
        int[] fibonacciArr = new int[n + 1];
        fibonacciArr[0] = 0;
        fibonacciArr[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibonacciArr[i] = (fibonacciArr[i - 1] + fibonacciArr[i - 2]) % 1234567;
        }

        return fibonacciArr[n];
    }

    public static void main(String[] args) {
        Programmers12945_2 programmers129452 = new Programmers12945_2();
        int solution = programmers129452.solution(5);
        System.out.println(solution);
    }
}
