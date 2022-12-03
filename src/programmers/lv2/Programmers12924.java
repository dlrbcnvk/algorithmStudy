package programmers.lv2;

/**
 * 숫자의 표현
 */
public class Programmers12924 {

    public int solution(int n) {
        int answer = 0;

        int startNum = 1;
        while (startNum <= n / 2) {
            int sum = 0;
            int value = startNum;
            while (sum < n) {
                sum += value;
                value++;
            }
            if (sum == n) {
                answer++;
            }
            startNum++;
        }

        answer++;

        return answer;
    }

    public static void main(String[] args) {
        Programmers12924 programmers12924 = new Programmers12924();
        int solution = programmers12924.solution(15);
        System.out.println(solution);
    }
}
