package programmers.lv2;

/**
 * JadenCase 문자열 만들기
 * 공백을 trim하라는 말이 없었음. 공백 개수만큼 그대로 두고 진행해야 함
 */
public class Programmers12951 {

    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == ' ') {
                // 빈칸은 그대로 둔다
            } else if (Character.isDigit(aChar)) {
                // 숫자는 그대로 둔다
            } else if (Character.isUpperCase(aChar)) {
                if (i == 0) {
                    // 대문자인데 인덱스 맨 앞이면 그대로 둔다
                } else {
                    if (chars[i - 1] == ' ') {

                    } else {
                        chars[i] = Character.toLowerCase(aChar);
                    }
                }
            } else if (Character.isLowerCase(aChar)) {
                if (i == 0) {
                    // 소문자인데 인덱스 맨 앞이면 대문자로 바꾼다
                    chars[i] = Character.toUpperCase(aChar);
                } else {
                    if (chars[i - 1] == ' ') {
                        chars[i] = Character.toUpperCase(aChar);
                    } else {

                    }
                }
            }
            answer.append(chars[i]);
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Programmers12951 programmers12951 = new Programmers12951();
        String result = programmers12951.solution("   7MATO  f AAaAA aAA o  34r  3F5WDF5ss3 The    7aAst   weEK  4mm   ");
        System.out.println(result);
    }
}
