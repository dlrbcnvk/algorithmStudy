package programmers.lv0;

/**
 * 외계행성의 나이
 */
public class Programmers120834 {

    public String solution(int age) {
        StringBuilder answer = new StringBuilder();

        char[] chars = String.valueOf(age).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '0') {
                answer.append("a");
            } else if (c == '1') {
                answer.append("b");
            } else if (c == '2') {
                answer.append("c");
            } else if (c == '3') {
                answer.append("d");
            } else if (c == '4') {
                answer.append("e");
            } else if (c == '5') {
                answer.append("f");
            } else if (c == '6') {
                answer.append("g");
            } else if (c == '7') {
                answer.append("h");
            } else if (c == '8') {
                answer.append("i");
            } else if (c == '9') {
                answer.append("j");
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Programmers120834 programmers120834 = new Programmers120834();
        String solution = programmers120834.solution(51);
        System.out.println(solution);
    }
}
