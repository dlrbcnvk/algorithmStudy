package programmers.lv1;

/**
 * 신규 아이디 추천
 * https://school.programmers.co.kr/learn/courses/30/lessons/72410
 */
public class Programmers72410_2 {

    public String solution(String new_id) {
        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-z0-9\\.\\-\\_]", "");
        new_id = new_id.replaceAll("[.]{2,1000}", ".");
        new_id = new_id.replaceAll("^\\.", "");
        new_id = new_id.replaceAll("\\.$", "");
        new_id = new_id.isEmpty() ? "a" : new_id;
        new_id = new_id.length() >= 16 ? new_id.substring(0, 15) : new_id;
        new_id = new_id.replaceAll("\\.$", "");
        if (new_id.length() <= 2) {
            String lastStr = new_id.substring(new_id.length() - 1);
            StringBuilder new_idBuilder = new StringBuilder(new_id);
            while (new_idBuilder.length() < 3) {
                new_idBuilder.append(lastStr);
            }
            new_id = new_idBuilder.toString();
        }

        return new_id;
    }

    public static void main(String[] args) {
        Programmers72410_2 programmers724102 = new Programmers72410_2();
        String solution = programmers724102.solution("abcdefghijklmn.p");
        System.out.println(solution);
    }
}
