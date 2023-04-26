package programmers.lv2;

/**
 * 양궁 대회
 * 높은 점수 맞추는 것부터 완전탐색...?결
 * 미해결
 */
public class Programmers92342 {

    public int[] solution(int n, int[] info) {
        int[] answer = {};

        dfs(n, 0, info, new int[]{0,0,0,0,0,0,0,0,0,0,0});


        return answer;
    }

    public void dfs(int n, int tryCount, int[] info, int[] my_info) {
        if (tryCount == n) {

        }

        for (int i = 0; i <= 10; i++) {

        }
    }

    public int getScore(int[] apeach_info, int[] lion_info) {
        int apeach_score = 0;
        int lion_score = 0;
        for (int i = 0; i <= 10; i++) {
            if (lion_info[i] == 0 && apeach_info[i] == 0) continue;

            if (lion_info[i] > apeach_info[i]) {
                lion_score += 10 - i;
            } else {
                apeach_score += 10 - i;
            }
        }
        return lion_score - apeach_score;
    }

    public static void main(String[] args) {
        Programmers92342 programmers92342 = new Programmers92342();
        int[] solution = programmers92342.solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0});
        System.out.println(solution);
    }
}
