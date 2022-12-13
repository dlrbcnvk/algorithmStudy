package programmers.lv1;

import java.util.HashMap;
import java.util.Map;

/**
 * 성격 유형 검사하기
 * map 사용. 루프 돌면서 기록하기
 */
public class Programmers118666 {

    Map<String, Integer> typeScoreMap;

    public String solution(String[] survey, int[] choices) {

        typeScoreMap = new HashMap<>();
        init();

        for (int i = 0; i < survey.length; i++) {
            String[] types = survey[i].split("");
            int choice = choices[i];
            String firstType = types[0];
            String secondType = types[1];

            Integer value;
            switch (choice) {
                case 1:
                    value = typeScoreMap.get(firstType);
                    typeScoreMap.put(firstType, value + 3);
                    break;
                case 2:
                    value = typeScoreMap.get(firstType);
                    typeScoreMap.put(firstType, value + 2);
                    break;
                case 3:
                    value = typeScoreMap.get(firstType);
                    typeScoreMap.put(firstType, value + 1);
                    break;
                case 4:
                    break;
                case 5:
                    value = typeScoreMap.get(secondType);
                    typeScoreMap.put(secondType, value + 1);
                    break;
                case 6:
                    value = typeScoreMap.get(secondType);
                    typeScoreMap.put(secondType, value + 2);
                    break;
                case 7:
                    value = typeScoreMap.get(secondType);
                    typeScoreMap.put(secondType, value + 3);
                    break;
                default:
                    break;
            }
        }

        String answer = "";

        Integer typeR = typeScoreMap.get("R");
        Integer typeT = typeScoreMap.get("T");
        if (typeR >= typeT) {
            answer += "R";
        } else {
            answer += "T";
        }

        Integer typeC = typeScoreMap.get("C");
        Integer typeF = typeScoreMap.get("F");
        if (typeC >= typeF) {
            answer += "C";
        } else {
            answer += "F";
        }

        Integer typeJ = typeScoreMap.get("J");
        Integer typeM = typeScoreMap.get("M");
        if (typeJ >= typeM) {
            answer += "J";
        } else {
            answer += "M";
        }

        Integer typeA = typeScoreMap.get("A");
        Integer typeN = typeScoreMap.get("N");
        if (typeA >= typeN) {
            answer += "A";
        } else {
            answer += "N";
        }

        return answer;
    }

    public void init() {
        typeScoreMap.put("R", 0);
        typeScoreMap.put("T", 0);
        typeScoreMap.put("C", 0);
        typeScoreMap.put("F", 0);
        typeScoreMap.put("J", 0);
        typeScoreMap.put("M", 0);
        typeScoreMap.put("A", 0);
        typeScoreMap.put("N", 0);
    }

    public static void main(String[] args) {
        Programmers118666 programmers118666 = new Programmers118666();
        String solution = programmers118666.solution(
                new String[]{"AN", "CF", "MJ", "RT", "NA"},
                new int[]{5, 3, 2, 7, 5}
        );
        System.out.println(solution);
    }
}
