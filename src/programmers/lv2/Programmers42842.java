package programmers.lv2;

/**
 * 카펫. 완전탐색
 */
public class Programmers42842 {

    public int[] solution(int brown, int yellow) {
        int[] answer = {};

        int total = brown + yellow;
        for (int i = 3; i <= total / 3; i++) {
            if (total % i == 0) {
                int len = total / i;
                if (len >= 3) {
                    int[] brownYellow = getBrownYellow(i, len);
                    if (brownYellow[0] == brown && brownYellow[1] == yellow) {
                        return new int[]{brownYellow[2], brownYellow[3]};
                    }
                }
            }
        }
        return answer;
    }

    public int[] getBrownYellow(int a, int b) {
        int width, height;
        int brown, yellow;
        if (a < b) {
            width = b;
            height = a;
        } else {
            width = a;
            height = b;
        }

        brown = width * 2 + height * 2 - 4;
        yellow = a * b - brown;
        return new int[]{brown, yellow, width, height};
    }

    public static void main(String[] args) {
        Programmers42842 programmers42842 = new Programmers42842();
        int[] solution = programmers42842.solution(8, 1);
        System.out.println(solution[0] + " " + solution[1]);
    }
}
