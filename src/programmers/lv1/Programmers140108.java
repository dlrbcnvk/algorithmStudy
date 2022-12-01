package programmers.lv1;

/**
 * 문자열 나누기
 */
public class Programmers140108 {

    public int solution(String s) {
        int answer = 0;

        int countX;
        int countOther;
        boolean separated;
        while (!s.equals("")) {
            char x = s.charAt(0);
            countX = 1;
            countOther = 0;
            separated = false;
            char[] chars = s.toCharArray();
            for (int i = 1; i < chars.length; i++) {
                char read = chars[i];
                if (read == x) {
                    countX++;
                } else {
                    countOther++;
                }

                if (countX == countOther) {
                    answer++;
                    if (i == chars.length - 1) {
                        return answer;
                    }
                    s = s.substring(i + 1);
                    separated = true;
                    break;
                }
            }

            if (!separated || chars.length == 1) {
                answer++;
                return answer;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers140108 programmers140108 = new Programmers140108();
        int solution = programmers140108.solution("aaaaaaaabbbbbbbbb");
        System.out.println(solution);
    }
}
