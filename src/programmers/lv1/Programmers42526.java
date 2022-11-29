package programmers.lv1;

import java.util.HashMap;

/**
 * 완주하지 못한 선수
 */
public class Programmers42526 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> participantMap = new HashMap<>();
        HashMap<String, Integer> completionMap = new HashMap<>();
        for (String str : participant) {
            if (participantMap.containsKey(str)) {
                Integer integer = participantMap.get(str);
                participantMap.put(str, integer + 1);
            } else {
                participantMap.put(str, 1);
            }
        }
        for (String str : completion) {
            if (completionMap.containsKey(str)) {
                Integer integer = completionMap.get(str);
                completionMap.put(str, integer + 1);
            } else {
                completionMap.put(str, 1);
            }
        }
        for (String str : participant) {
            if (!completionMap.containsKey(str)) {
                return str;
            } else if (participantMap.get(str) > completionMap.get(str)) {
                return str;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers42526 programmers42526 = new Programmers42526();
        String solution = programmers42526.solution(
                new String[]{"mislav", "stanko", "mislav", "ana"},
                new String[]{"stanko", "ana", "mislav"}
        );
        System.out.println(solution);
    }
}
