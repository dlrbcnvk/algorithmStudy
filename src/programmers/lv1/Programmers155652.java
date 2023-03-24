package programmers.lv1;

/**
 * 둘만의 암호
 */
public class Programmers155652 {

    public String solution(String s, String skip, int index) {

        StringBuilder answer = new StringBuilder();

        String[] sArray = s.split("");
        for (String string : sArray) {

            char c = string.toCharArray()[0];
            int count = 0;

            while (count < index) {

                if ((int) c == 122) {
                    c = 97;
                } else {
                    c++;
                }

                if (!skip.contains(String.valueOf(c))) {
                    count++;
                }
            }

            answer.append(c);
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Programmers155652 programmers155652 = new Programmers155652();
        String solution = programmers155652.solution("xyz", "sdfsad", 5);
        System.out.println(solution);
    }
}
