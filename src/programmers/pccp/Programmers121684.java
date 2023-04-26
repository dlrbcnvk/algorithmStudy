package programmers.pccp;

/**
 * 체육대회
 * 2023.4.26 AC
 * https://school.programmers.co.kr/learn/courses/15008/lessons/121684
 * 완전탐색
 */
public class Programmers121684 {

    int maxScore = 0;

    public int solution(int[][] ability) {

        boolean[] marked = new boolean[ability.length];
        int n = ability[0].length;

        go(0, n, 0, marked, ability);

        return maxScore;
    }

    public void go(int sport, int n, int score, boolean[] marked, int[][] ability) {
        if (sport == n) {
            maxScore = Math.max(score, maxScore);
            return;
        }

        for (int i = 0; i < ability.length; i++) {
            if (!marked[i]) {
                marked[i] = true;
                score += ability[i][sport];
                go(sport + 1, n, score, marked, ability);
                score -= ability[i][sport];
                marked[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Programmers121684 programmers121684 = new Programmers121684();
        int solution = programmers121684.solution(
                new int[][]{{40,10,10},{20,5,0},{30,30,30},{70,0,70},{100,100,100}}
        );
        System.out.println(solution);
    }
}
