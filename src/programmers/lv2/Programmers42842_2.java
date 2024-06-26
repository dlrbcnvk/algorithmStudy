package programmers.lv2;

/**
 * 카펫
 * https://school.programmers.co.kr/learn/courses/30/lessons/42842
 */
public class Programmers42842_2 {

    public int[] solution(int brown, int yellow) {
        // a * b = yellow 의 (a,b) 조합 각각에 대해서
        for (int i = 1; i <= yellow; i++) {
            if (yellow % i == 0) {
                int yellowHeight = i;
                int yellowWidth = yellow / i;
                // brown 을 계산해본다.
                int calculatedBrown = getBrownFromYellowWidthHeight(yellowWidth, yellowHeight);
                if (brown == calculatedBrown) {
                    return new int[]{yellowWidth + 2, yellowHeight + 2};
                }
            }
        }
        int[] answer = {};
        return answer;
    }

    private int getBrownFromYellowWidthHeight(int yellowWidth, int yellowHeight) {
        return (yellowWidth + yellowHeight) * 2 + 4;
    }

    public static void main(String[] args) {
        Programmers42842_2 programmers428422 = new Programmers42842_2();
        int[] solution = programmers428422.solution(10, 2);

        System.out.print(solution[0] + " " + solution[1]);
    }
}
