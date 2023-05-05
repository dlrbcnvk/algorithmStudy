package programmers.lv2;

/**
 * 점 찍기
 * 미해결
 */
public class Programmers140107 {

    public long solution(int k, int d) {

        long answer = 0;
        for (int a = 0; a * k <= d; a++) {
            for (int b = 0; b * k <= d; b++) {
                if ((((a * k) * (a * k)) + ((b * k) * (b * k))) > d * d) {
                    break;
                }
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers140107 programmers140107 = new Programmers140107();
        long solution = programmers140107.solution(2, 4);
        System.out.println(solution);
    }
}
