package programmers.lv1;

/**
 * 시저 암호
 */
public class Programmers12926 {

    public String solution(String s, int n) {

        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (c == ' ') {
                sb.append(" ");
                continue;
            }
            char forwardChar = forward(c, n);
            sb.append(forwardChar);
        }

        return sb.toString();
    }

    public char forward(char c, int n) {
        if (Character.isUpperCase(c)) {
            if (c + n > 'Z') {
                return (char) (c + n - 26);
            }
        } else {
            if (c + n > 'z') {
                return (char) (c + n - 26);
            }
        }
        return (char) (c + n);
    }

    public static void main(String[] args) {
        Programmers12926 programmers12926 = new Programmers12926();
        String solution = programmers12926.solution("a B z", 4);
        System.out.println(solution);
    }
}
