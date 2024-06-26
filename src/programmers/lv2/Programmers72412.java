package programmers.lv2;

import java.util.*;

/**
 * 순위 검색
 * 정확성 일부 런타임에러. 효율성 무엇이 문제일까
 * 미해결...
 */
public class Programmers72412 {

    private static String DASH = "-";
    private static String SPACE_AND = " and";
    private static String EMPTY = "";
    private static String SPACE = " ";
    public static class Applicant implements Comparable<Applicant> {
        String lang;
        String job;
        String level;
        String food;
        int score;

        public Applicant(String lang, String job, String level, String food, Integer score) {
            this.lang = lang;
            this.job = job;
            this.level = level;
            this.food = food;
            this.score = score;
        }

        @Override
        public int compareTo(Applicant a) {
            return this.score - a.score;
        }
    }

    private Applicant[] applicantList;

    public int[] solution(String[] info, String[] query) {
        applicantList = new Applicant[info.length];

        int idx = 0;
        for (String s : info) {
            String[] split = s.split(SPACE);
            Applicant applicant = new Applicant(split[0], split[1], split[2], split[3], Integer.valueOf(split[4]));
            applicantList[idx] = applicant;
            idx++;
        }


        Arrays.sort(applicantList);

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int result = query(query[i]);
            answer[i] = result;
        }
        return answer;
    }

    private int query(String query) {
        Applicant[] filteredApplicants;
        String queryEndExcluded = query.replaceAll(SPACE_AND, EMPTY);
        String[] split = queryEndExcluded.split(SPACE);
        String lang = split[0];
        String job = split[1];
        String level = split[2];
        String food = split[3];
        int score = Integer.parseInt(split[4]);

        filteredApplicants = Arrays.stream(applicantList)
                .filter(applicant ->
                        (lang.equals(DASH) || applicant.lang.equals(lang)) &&
                        (job.equals(DASH) || applicant.job.equals(job)) &&
                        (level.equals(DASH) || applicant.level.equals(level)) &&
                        (food.equals(DASH) || applicant.food.equals(food)))
                .toArray(Applicant[]::new);


        // binary search
        // 범위 중에서 제일 작은 수 (인덱스) 구하기
        int start = 0; // inclusive
        int end = filteredApplicants.length - 1; // inclusive

        // end - inclusive 로 간주하고 end 가 범위 내의 수로 진입한 다음
        // 마지막 2개 남았을 때에도 mid(==start) < score 라면 start 를 1 증가시켜 end 가 됨으로써 나머지 딱 하나 남음. 그때 include 시킴.
        while (end > start) {
            int mid = (start + end) / 2;

            if (filteredApplicants[mid].score < score) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        if (filteredApplicants[end].score >= score) {
            return filteredApplicants.length - end;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Programmers72412 programmers72412 = new Programmers72412();
        int[] solution = programmers72412.solution(
                new String[]{
                        "java backend junior pizza 900",
                        "python frontend senior chicken 800",
                        "python frontend senior chicken 700",
                        "cpp backend senior pizza 600",
                        "java backend junior chicken 80",
                        "python backend senior chicken 50"}
                ,
                 new String[]{
                         "java and backend and junior and pizza 100",
                         "python and frontend and senior and chicken 200",
                         "cpp and - and senior and pizza 250",
                         "- and backend and senior and - 150",
                         "- and - and - and chicken 100",
                         "- and - and - and - 40"
                }
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
