package programmers.lv1;

import java.util.Locale;

/**
 * 신규 아이디 추천
 */
public class Programmers72410 {

    public String solution(String new_id) {

        // phase 1
        new_id = new_id.toLowerCase();

        // phase 2
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");

        // phase 3
        new_id = new_id.replaceAll("[.]{2,}", ".");

        // phase 4
        new_id = new_id.replaceAll("^[.]+", "").replaceAll("[.]+$", "");

        // phase 5
        if (new_id.equals("")) {
            new_id = "a";
        }

        // phase 6
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("[.]+$", "");
        }

        // phase 7
        if (new_id.length() < 3) {
            StringBuilder sb = new StringBuilder(new_id);
            while (sb.length() < 3) {
                char c = sb.charAt(sb.length() - 1);
                sb.append(c);
            }
            new_id = sb.toString();
        }

        return new_id;
    }

    public static void main(String[] args) {
        Programmers72410 programmers72410 = new Programmers72410();
        String solution = programmers72410.solution("abcdefghijklmn.p");
        System.out.println(solution);
    }
}
