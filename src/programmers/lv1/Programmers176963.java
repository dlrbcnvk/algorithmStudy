package programmers.lv1;

import java.util.HashMap;
import java.util.Map;

/**
 * 추억 점수
 */
public class Programmers176963 {

    public int[] solution(String[] name, int[] yearning, String[][] photo) {

        Map<String, Integer> nameScoreMap = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            nameScoreMap.put(name[i], yearning[i]);
        }

        int[] answer = new int[photo.length];
        for (int i = 0; i < photo.length; i++) {
            answer[i] = getScoreFromPhoto(photo[i], nameScoreMap);
        }

        return answer;
    }

    private int getScoreFromPhoto(String[] photo, Map<String, Integer> nameScoreMap) {
        int score = 0;
        for (String name : photo) {
            if (nameScoreMap.containsKey(name)) {
                score += nameScoreMap.get(name);
            }
        }
        return score;
    }

    public static void main(String[] args) {
        Programmers176963 programmers176963 = new Programmers176963();
        int[] solution = programmers176963.solution(
                new String[]{"may", "kein", "kain", "radi"},
                new int[]{5, 10, 1, 3},
                new String[][]{
                        {"may", "kein", "kain", "radi"},
                        {"may", "kein", "brin", "deny"},
                        {"kon", "kain", "may", "coni"}
                }
        );

        for (int score : solution) {
            System.out.print(score + " ");
        }



    }
}
