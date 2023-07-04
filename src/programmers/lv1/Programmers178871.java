package programmers.lv1;

import java.util.HashMap;
import java.util.Map;

/**
 * 달리기 경주
 */
public class Programmers178871 {

    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> playerMap = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            playerMap.put(players[i], i);
        }
        for (String call : callings) {
            int idx = playerMap.get(call);
            String temp = players[idx - 1];
            players[idx - 1] = call;
            playerMap.put(call, idx - 1);
            players[idx] = temp;
            playerMap.put(temp, idx);

        }
        return players;
    }

    public static void main(String[] args) {
        Programmers178871 programmers178871 = new Programmers178871();

    }
}
