package programmers.lv3;

import java.util.*;

/**
 * 불량 사용자
 */
public class Programmers64064 {

    boolean[] marked;
    List<String> userList;
    private int banned_length;
    String[] user_id;
    String[] banned_id;
    Set<Set<String>> valueSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {

        marked = new boolean[user_id.length];
        Arrays.fill(marked, false);

        userList = new ArrayList<>();
        banned_length = banned_id.length;
        this.user_id = user_id;
        this.banned_id = banned_id;

        go();

        return valueSet.size();
    }

    public void go() {
        if (userList.size() == banned_length) {
            boolean checkResult = check();
            if (checkResult) {
                HashSet<String> set = new HashSet<>(userList);
                valueSet.add(set);
            }
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (!marked[i]) {
                marked[i] = !marked[i];
                userList.add(user_id[i]);
                go();
                userList.remove(user_id[i]);
                marked[i] = !marked[i];
            }
        }
    }

    public boolean check() {
        for (int i = 0; i < userList.size(); i++) {
            String pattern = banned_id[i].replaceAll("[*]", ".");
            if (!userList.get(i).matches(pattern)) {
                return false;
            }
        }
        return true;
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
