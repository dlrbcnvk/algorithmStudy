package programmers.lv2;

import java.util.ArrayList;

/**
 * k진수에서 소수 개수 구하기
 * 미해결
 */
public class Programmers92335 {

    static class Range {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    ArrayList<Integer> zeroIdxArray;
    ArrayList<Range> rangeList;

    public int solution(int n, int k) {

        String[] s = Integer.toString(n, k).split("");
        zeroIdxArray = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals("0")) {
                zeroIdxArray.add(i);
            }
        }
        rangeList = new ArrayList<>();


        int answer = -1;
        return answer;
    }

    public static void main(String[] args) {
        Programmers92335 programmers92335 = new Programmers92335();
        int solution = programmers92335.solution(437674, 3);
        System.out.println(solution);
    }
}
