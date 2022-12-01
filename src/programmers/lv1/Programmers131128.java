package programmers.lv1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * 숫자 짝궁
 */
public class Programmers131128 {

    public String solution(String X, String Y) {

        HashMap<String, Integer> mapX = new HashMap<>();
        HashMap<String, Integer> mapY = new HashMap<>();
        char[] charX = X.toCharArray();
        char[] charY = Y.toCharArray();
        setCounts(mapX, charX);
        setCounts(mapY, charY);
        Set<String> setX = mapX.keySet();

        StringBuilder sb = new StringBuilder();
        for (String key : setX) {
            if (!mapY.containsKey(key)) {
                continue;
            }
            Integer countX = mapX.get(key);
            Integer countY = mapY.get(key);
            Integer min = Math.min(countX, countY);
            for (int i = 0; i < min; i++) {
                sb.append(key);
            }
        }
        String s = sb.toString();
        if (s.equals("")) {
            return "-1";
        }
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        if (chars[0] == '0' && chars[chars.length - 1] == '0') {
            return "0";
        }

        sb = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }


        return sb.toString();
    }

    private void setCounts(HashMap<String, Integer> commonMap, char[] chars) {
        for (char y : chars) {
            String temp = y + "";
            if (commonMap.containsKey(temp)) {
                Integer integer = commonMap.get(temp);
                commonMap.put(temp, integer + 1);
            } else {
                commonMap.put(temp, 1);
            }
        }
    }

    public static void main(String[] args) {
        Programmers131128 programmers131128 = new Programmers131128();
        String solution = programmers131128.solution("100", "2345");
        System.out.println(solution);
    }
}
