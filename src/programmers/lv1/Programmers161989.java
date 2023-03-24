package programmers.lv1;

/**
 * 덧칠하기
 */
public class Programmers161989 {

    public int solution(int n, int m, int[] section) {
        int answer = 0;

        int location = section[0];
        int sectionIdx = 0;

        while (true) {

            // 덧칠하기
            location = location + m - 1;
            answer++;
            while (section[sectionIdx] <= location) {
                if (sectionIdx == section.length - 1) {
                    return answer;
                } else {
                    sectionIdx++;
                }
            }
            location = section[sectionIdx];
        }
    }

    public static void main(String[] args) {
        Programmers161989 programmers161989 = new Programmers161989();
        int solution = programmers161989.solution(4,1,new int[]{1,2,3,4});
        System.out.println(solution);
    }
}
