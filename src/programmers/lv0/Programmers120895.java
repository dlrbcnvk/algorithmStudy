package programmers.lv0;

/**
 * 인덱스 바꾸기
 */
public class Programmers120895 {

    public String solution(String my_string, int num1, int num2) {
        String answer = "";

        char c1 = my_string.charAt(num1);
        char c2 = my_string.charAt(num2);
        char[] chars = my_string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i == num1) {
                answer += c2;
            } else if (i == num2) {
                answer += c1;
            } else {
                answer += chars[i];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers120895 programmers120895 = new Programmers120895();
        String solution = programmers120895.solution("I love you", 3, 6);
        System.out.println(solution);
    }
}
