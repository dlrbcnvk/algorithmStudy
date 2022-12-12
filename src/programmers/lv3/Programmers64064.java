package programmers.lv3;

/**
 * 불량 사용자
 */
public class Programmers64064 {

    public int solution(String[] user_id, String[] banned_id) {




        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {
        Programmers64064 programmers64064 = new Programmers64064();
        int solution = programmers64064.solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "*rodo", "******", "******"}
        );
        System.out.println(solution);
    }
}
