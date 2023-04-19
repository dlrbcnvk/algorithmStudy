package programmers.lv1;

/**
 * 이상한 문자 만들기
 */
public class Programmers12930 {

    public String solution(String s) {

        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int wordIdx = 0;
        for (char c : chars) {
            if (c == ' ') {
                sb.append(c);
                wordIdx = 0;
                continue;
            }

            if (wordIdx % 2 == 0) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
            wordIdx++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Programmers12930 programmers12930 = new Programmers12930();
        String solution = programmers12930.solution("try hello world");
        System.out.println(solution);
    }
}
