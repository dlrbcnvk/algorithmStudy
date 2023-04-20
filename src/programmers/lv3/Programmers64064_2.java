package programmers.lv3;

import java.util.*;

/**
 * 불량 사용자
 */
public class Programmers64064_2 {

    public int solution(String[] user_id, String[] banned_id) {
        String[][] bans = Arrays.stream(banned_id)
                .map(banned -> banned.replace("*", "."))
                .map(banned -> Arrays.stream(user_id)
                        .filter(id -> id.matches(banned))
                        .toArray(String[]::new))
                .toArray(String[][]::new);

        Set<Set<String>> banSet = new HashSet<>();
        count(0, new HashSet<>(), bans, banSet);
        return banSet.size();
    }

    private void count(int index, Set<String> banned, String[][] bans, Set<Set<String>> banSet) {
        if (index == bans.length) {
            banSet.add(new HashSet<>(banned));
            return;
        }

        for (String id : bans[index]) {
            if (banned.contains(id)) {
                continue;
            }

            banned.add(id);
            count(index + 1, banned, bans, banSet);
            banned.remove(id);
        }
    }


    public static void main(String[] args) {
        Programmers64064_2 programmers64064 = new Programmers64064_2();
        int solution = programmers64064.solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "*rodo", "******", "******"}
        );
        System.out.println(solution);
    }
}
