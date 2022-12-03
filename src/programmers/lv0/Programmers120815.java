package programmers.lv0;

/**
 * 피자 나눠 먹기 (2)
 */
public class Programmers120815 {

    public int solution(int n) {

        int value = 6;
        while (true) {
            if (value % n == 0) {
                return value / 6;
            }
            value += 6;
        }
    }

    public static void main(String[] args) {
        Programmers120815 programmers120815 = new Programmers120815();
        int solution = programmers120815.solution(10);
        System.out.println(solution);
    }
}
