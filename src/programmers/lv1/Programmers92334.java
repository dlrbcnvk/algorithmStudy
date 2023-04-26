package programmers.lv1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 신고 결과 받기
 * 2023.04.26 AC
 */
public class Programmers92334 {

    public int[] solution(String[] id_list, String[] report, int k) {

        Map<String, Set<String>> reportingMap = new HashMap<>();
        Map<String, Set<String>> reportedMap = new HashMap<>();
        for (String rep : report) {
            String[] split = rep.split(" ");
            String reporter = split[0];
            String reported = split[1];
            if (!reportingMap.containsKey(reporter)) {
                reportingMap.put(reporter, new HashSet<>());
            }
            if (!reportedMap.containsKey(reported)) {
                reportedMap.put(reported, new HashSet<>());
            }
            reportingMap.get(reporter).add(reported);
            reportedMap.get(reported).add(reporter);
        }

        Set<String> bannedIdSet = new HashSet<>();

        for (String user : id_list) {
            if (reportedMap.containsKey(user)) {
                if (reportedMap.get(user).size() >= k) {
                    bannedIdSet.add(user);
                }
            }
        }

        int[] email_count = new int[id_list.length];
        for (int i = 0; i < email_count.length; i++) {
            int count = 0;
            String id = id_list[i];
            if (reportingMap.containsKey(id)) {
                for (String reported : reportingMap.get(id)) {
                    if (bannedIdSet.contains(reported)) {
                        count++;
                    }
                }
            }
            email_count[i] = count;
        }
        return email_count;
    }

    public static void main(String[] args) {
        Programmers92334 programmers92334 = new Programmers92334();
        int[] solution = programmers92334.solution(
                new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
                2
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
