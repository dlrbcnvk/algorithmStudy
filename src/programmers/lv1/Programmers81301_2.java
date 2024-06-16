package programmers.lv1;

/**
 * 숫자 문자열과 영단어
 * https://school.programmers.co.kr/learn/courses/30/lessons/81301
 */
public class Programmers81301_2 {

    public int solution(String s) {
        s = s.replaceAll("zero", "0")
                .replaceAll("one", "1")
                .replaceAll("two", "2")
                .replaceAll("three", "3")
                .replaceAll("four", "4")
                .replaceAll("five", "5")
                .replaceAll("six", "6")
                .replaceAll("seven", "7")
                .replaceAll("eight", "8")
                .replaceAll("nine", "9");

        return Integer.valueOf(s);
    }

    public static void main(String[] args) {
        Programmers81301_2 programmers813012 = new Programmers81301_2();
        int solution = programmers813012.solution("2three45sixseven");
        System.out.println(solution);
    }
}
