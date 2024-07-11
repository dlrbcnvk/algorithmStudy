package programmers.lv1;


import java.util.HashMap;
import java.util.Map;

/**
 * 완주하지 못한 선수
 * https://school.programmers.co.kr/learn/courses/30/lessons/42576
 */
public class Programmers42576_2 {

    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> participantMap = new HashMap<>();
        for (String participantName : participant) {
            if (!participantMap.containsKey(participantName)) {
                participantMap.put(participantName, 1);
            } else {
                Integer value = participantMap.get(participantName);
                participantMap.put(participantName, value + 1);
            }
        }

        for (String completedName : completion) {
            if (participantMap.containsKey(completedName)) {
                Integer value = participantMap.get(completedName);
                if (value == 1) {
                    participantMap.remove(completedName);
                } else {
                    participantMap.put(completedName, value - 1);
                }
            }
        }

        return participantMap.keySet().toArray(String[]::new)[0];
    }

    public static void main(String[] args) {

    }
}
