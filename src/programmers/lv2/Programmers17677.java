package programmers.lv2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * [1차] 뉴스 클러스터링
 */
public class Programmers17677 {

    void checkAndPut(char[] chars, Map<String, Integer> map) {
        for (int i = 0; i < chars.length - 1; i++) {
            String s = "";
            s += chars[i];
            s += chars[i + 1];
            s = s.toLowerCase();
            String replaced = s.replaceAll("\\W|\\d|_", "");
            if (s.equals(replaced)) {
                if (map.containsKey(s)) {
                    Integer integer = map.get(s);
                    map.put(s, integer + 1);
                } else {
                    map.put(s, 1);
                }
            }
        }
    }

    public int solution(String str1, String str2) {

        Map<String, Integer> str1Map = new HashMap<>();
        Map<String, Integer> str2Map = new HashMap<>();
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        checkAndPut(chars1, str1Map);
        checkAndPut(chars2, str2Map);

        // intersect, union
        HashMap<String, Integer> intersect = new HashMap<>();
        HashMap<String, Integer> union = new HashMap<>();
        Set<String> set1 = str1Map.keySet();
        Set<String> set2 = str2Map.keySet();
        for (String key : set1) {
            if (str2Map.containsKey(key)) {
                Integer value1 = str1Map.get(key);
                Integer value2 = str2Map.get(key);
                Integer min = Math.min(value1, value2);
                intersect.put(key, min);
            }

            union.put(key, str1Map.get(key));
        }
        for (String key : set2) {
            if (union.containsKey(key)) {
                Integer value1 = str1Map.get(key);
                Integer value2 = str2Map.get(key);
                Integer max = Math.max(value1, value2);
                union.put(key, max);
            } else {
                union.put(key, str2Map.get(key));
            }
        }

        Integer intersectCount = 0;
        Integer unionCount = 0;
        Set<String> intersectKeySet = intersect.keySet();
        for (String key : intersectKeySet) {
            intersectCount += intersect.get(key);
        }
        Set<String> unionKeySet = union.keySet();
        for (String key : unionKeySet) {
            unionCount += union.get(key);
        }

        if (intersectCount == 0 && unionCount == 0) {
            return 65536;
        }
        double result = ((double)intersectCount / unionCount) * 65536;

        return (int)(result);
    }

    public static void main(String[] args) {
        Programmers17677 programmers17677 = new Programmers17677();
        int solution = programmers17677.solution("E=M*C^2", "e=m*c^2");
        System.out.println(solution);
    }
}
