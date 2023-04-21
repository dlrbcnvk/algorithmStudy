package programmers.lv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 순위 검색
 * 정확성 통과. 효율성 무엇이 문제일까
 */
public class Programmers72412 {

    public class Applicant implements Comparable<Applicant> {
        String lang;
        String job;
        String level;
        String food;
        Integer score;

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

    private List<Applicant> applicantList;

    public int[] solution(String[] info, String[] query) {
        applicantList = new ArrayList<>();

        for (String s : info) {
            String[] split = s.split(" ");
            Applicant applicant = new Applicant(split[0], split[1], split[2], split[3], Integer.valueOf(split[4]));
            applicantList.add(applicant);
        }
        Collections.sort(applicantList);

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String queryEndExcluded = query[i].replaceAll(" and", "");
            String[] split = queryEndExcluded.split(" ");
            String lang = split[0];
            String job = split[1];
            String level = split[2];
            String food = split[3];
            Integer score = Integer.valueOf(split[4]);
            int result = query(lang, job, level, food, score);
            answer[i] = result;
        }
        return answer;
    }

    private int query(String lang, String job, String level, String food, Integer score) {

        ArrayList<Applicant> filteredList = new ArrayList<>();
        for (Applicant applicant : this.applicantList) {
            if ((lang.equals("-") || applicant.lang.equals(lang)) &&
                    (job.equals("-") || applicant.job.equals(job)) &&
                    (level.equals("-") || applicant.level.equals(level)) &&
                    (food.equals("-") || applicant.food.equals(food))) {
                filteredList.add(applicant);
            }
        }
        Applicant[] filteredArr = new Applicant[filteredList.size()];
        for (int i = 0; i < filteredList.size(); i++) {
            filteredArr[i] = filteredList.get(i);
        }

//        Applicant[] filteredArr = applicantList.stream()
//                .filter(applicant -> (lang.equals("-") || applicant.lang.equals(lang)) &&
//                (job.equals("-") || applicant.job.equals(job)) &&
//                (level.equals("-") || applicant.level.equals(level)) &&
//                (food.equals("-") || applicant.food.equals(food)))
//                .toArray(Applicant[]::new);

        // binary search
        int start = 0; // inclusive
        int end = filteredArr.length; // exclusive

        while (end > start) {
            int mid = (start + end) / 2;

            if (filteredArr[mid].score < score) {
                start = mid + 1;
            } else if (filteredArr[mid].score >= score) {
                if (end - start <= 2) {
                    end = mid;
                } else {
                    end = mid + 1;
                }
            }
        }

        return filteredArr.length - start;
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
