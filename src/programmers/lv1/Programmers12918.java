package programmers.lv1;

/**
 * 문자열 다루기 기본
 */
public class Programmers12918 {

    public boolean solution(String s) {
        if (s.length() != 4 && s.length() != 6) {
            return false;
        }

        boolean matches = s.matches("[0-9]{4}|[0-9]{6}");

        return matches;
    }

    public static void main(String[] args) {
        Programmers12918 programmers12918 = new Programmers12918();
        boolean solution = programmers12918.solution("123456");
        System.out.println(solution);
    }
}
