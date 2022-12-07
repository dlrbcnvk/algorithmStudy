package programmers.lv1;

/**
 * 기사단원의 무기
 */
public class Programmers136798 {

    public int solution(int number, int limit, int power) {
        int answer = 0;

        int[] steels = new int[number + 1];
        for (int i = 1; i <= number; i++) {
            steels[i] = getDivisorCount(i, limit, power);
        }

        for (int i = 1; i <= number; i++) {
            answer += steels[i];
        }
        return answer;
    }

    public int getDivisorCount(int num, int limit, int power) {
        int count = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                count++;
            }
            if (count > limit) {
                return power;
            }
        }
        count++;
        if (count > limit) {
            return power;
        }
        return count;
    }

    public static void main(String[] args) {
        Programmers136798 programmers136798 = new Programmers136798();
        int solution = programmers136798.solution(10, 3, 2);
        System.out.println(solution);
    }
}
