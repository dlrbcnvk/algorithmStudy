package programmers.lv2;

/**
 * 문자열 압축
 * https://school.programmers.co.kr/learn/courses/30/lessons/60057
 * 미해결
 */
public class Programmers60057_2 {

    public int solution(String s) {
        int answer = 0;
        for (int i = 1; i < s.length() / 2; i++) {
            answer = Math.min(answer, compress(s, i));
        }
        return answer;
    }

    public int compress(String s, int num) {
        int totalLength = 0;
        return 1;
    }

    public static void main(String[] args) {
        Programmers60057_2 programmers600572 = new Programmers60057_2();
        int solution = programmers600572.solution("abcabcabcabcdededededede");
        System.out.println(solution);
    }
}
