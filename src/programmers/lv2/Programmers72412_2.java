package programmers.lv2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 순위 검색
 * https://school.programmers.co.kr/learn/courses/30/lessons/72412
 * 미해결
 */
public class Programmers72412_2 {

    public class Info {
        private String language;
        private String job;
        private String career;
        private String soulFood;
        private int score;

        public Info(String language, String job, String career, String soulFood, int score) {
            this.language = language;
            this.job = job;
            this.career = career;
            this.soulFood = soulFood;
            this.score = score;
        }

        public String getLanguage() { return language; }
        public String getJob() {
            return job;
        }
        public String getCareer() {
            return career;
        }
        public String getSoulFood() {
            return soulFood;
        }
        public int getScore() {
            return score;
        }
    }


    public int[] solution(String[] info, String[] query) {
        List<Info> infoList = new ArrayList<>();
        for (String infoUnit : info) {
            String[] InfoUnitSplit = infoUnit.split(" ");
            Info infoUnitObj = new Info(InfoUnitSplit[0], InfoUnitSplit[1], InfoUnitSplit[2], InfoUnitSplit[3], Integer.parseInt(InfoUnitSplit[4]));
            infoList.add(infoUnitObj);
        }

        // keySet
        Set<String> languageSet = new HashSet<>();
        Set<String> jobSet = new HashSet<>();
        Set<String> careerSet = new HashSet<>();
        Set<String> soulFoodSet = new HashSet<>();
        infoList.forEach(infoUnit -> {
            languageSet.add(infoUnit.getLanguage());
            jobSet.add(infoUnit.getJob());
            careerSet.add(infoUnit.getCareer());
            soulFoodSet.add(infoUnit.getSoulFood());
        });
        languageSet.add("-");
        jobSet.add("-");
        careerSet.add("-");
        soulFoodSet.add("-");

        // grouping
        Map<String, Map<String, Map<String, Map<String, List<Info>>>>> infoGroup = new HashMap<>();
        for (String language : languageSet) {
            infoGroup.put(language, new HashMap<>());
            for (String job : jobSet) {
                infoGroup.get(language).put(job, new HashMap<>());
                for (String career : careerSet) {
                    infoGroup.get(language).get(job).put(career, new HashMap<>());
                    for (String soulFood : soulFoodSet) {
                        // Info 아이템들을 필터링해서 확보한 다음, score 기준으로 정렬해서 넣기
                        List<Info> filteredInfoList = infoList.stream()
                                .filter(infoUnit -> "-".equals(language) || infoUnit.getLanguage().equals(language))
                                .filter(infoUnit -> "-".equals(job) || infoUnit.getJob().equals(job))
                                .filter(infoUnit -> "-".equals(career) || infoUnit.getCareer().equals(career))
                                .filter(infoUnit -> "-".equals(soulFood) || infoUnit.getSoulFood().equals(soulFood))
                                .sorted(Comparator.comparingInt(Info::getScore))
                                .collect(Collectors.toList());

                        infoGroup.get(language).get(job).get(career).put(soulFood, filteredInfoList);
                    }
                }
            }
        }

        List<Integer> resultList = new ArrayList<>();
        for (String queryUnit : query) {
            resultList.add(getCountGteScore(infoGroup, queryUnit));
        }

        return resultList.stream().mapToInt(i -> i).toArray();
    }

    // targetScore 점 이상 받은 사람은 모두 몇 명?
    private int getCountGteScore(Map<String, Map<String, Map<String, Map<String, List<Info>>>>> infoGroup, String queryUnit) {
        String[] queryUnitSplit = queryUnit.split(" and ");
        String[] lastSplit = queryUnitSplit[queryUnitSplit.length - 1].split(" ");
        queryUnitSplit[queryUnitSplit.length - 1] = lastSplit[0];
        String language = queryUnitSplit[0];
        String job = queryUnitSplit[1];
        String career = queryUnitSplit[2];
        String soulFood = queryUnitSplit[3];
        int targetScore = Integer.parseInt(lastSplit[1]);
        List<Info> targetInfoList = infoGroup.get(language).get(job).get(career).get(soulFood);

        int st = 0;
        int en = targetInfoList.size();
        while (st < en) {
            int mid = (st + en) / 2;
            int value = targetInfoList.get(mid).getScore();
            if (value < targetScore) {
                st = mid + 1;
            } else {
                en = mid;
            }
        }

        return targetInfoList.size() - st;
    }

    public static void main(String[] args) {
        Programmers72412_2 programmers724122 = new Programmers72412_2();
        int[] solution = programmers724122.solution(
                new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"}
        );

        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
    }
}
