package programmers.lv0;

/**
 * 문자열 뒤집기
 */
public class Programmers120822 {
    public String solution(String my_string) {
        char[] chars = my_string.toCharArray();
        String answer = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            answer += chars[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers120822 programmers120822 = new Programmers120822();
        String solution = programmers120822.solution("bread");
        System.out.println(solution);
    }
}
