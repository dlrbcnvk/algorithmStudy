package programmers.lv2;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * n ~ 0 순차 탐색
 * h값 변수, 인덱스 변수 관리
 */
public class Programmers42747 {

    int[] citations;

    public boolean condition(int h, int idx) {
        if (h <= citations[idx] && citations.length - idx >= h) {
            return true;
        }
        return false;
    }

    public int solution(int[] citations) {
        this.citations = citations;
        Arrays.sort(citations);

        int idx = citations.length - 1;
        int h = citations.length;
        while (true) {
            if (condition(h, idx)) {
                return h;
            }
            if (idx == 0) {
                while (true) {
                    if (condition(h, idx)) {
                        return h;
                    }
                    h--;
                }
            }

            if (citations[idx - 1] == h - 1) {
                idx--;
            }
            else if (citations[idx] == citations[idx - 1]) {
                idx--;
                continue;
            }
            h--;
        }
    }

    public static void main(String[] args) {
        Programmers42747 programmers42747 = new Programmers42747();
        int solution = programmers42747.solution(
                new int[]{4,4,4}
        );

        System.out.println(solution);

    }
}
