package programmers.lv1;

import java.util.HashMap;

/**
 * 완주하지 못한 선수
 */
public class Programmers42576 {

    public String solution(String[] participant, String[] completion) {

        HashMap<String, Integer> participantMap = new HashMap<>();
        for (String str : participant) {
            if (participantMap.containsKey(str)) {
                Integer integer = participantMap.get(str);
                participantMap.put(str, integer + 1);
            } else {
                participantMap.put(str, 1);
            }
        }
        for (String str : completion) {
            if (participantMap.containsKey(str)) {
                Integer integer = participantMap.get(str);
                int value = integer - 1;
                if (value == 0) {
                    participantMap.remove(str);
                } else {
                    participantMap.put(str, integer - 1);
                }
            } else {
                return str;
            }
        }

        for (String key : participantMap.keySet()) {
            return key;
        }
        return "";
    }

    public static void main(String[] args) {
        Programmers42576 programmers42576 = new Programmers42576();
        String solution = programmers42576.solution(
                new String[]{""},
                new String[]{""}
        );
        System.out.println(solution);
    }
}
