package programmers.lv2;

import java.util.Arrays;

/**
 * 미해결
 * 어떻게 하는지 감이 잘 안 옴..ㅠ
 */
public class Programmers42747 {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        boolean needToUpdate = false;
        int sameNum = -1;
        int sameStartIdx = -1;
        int sameEndIdx = -1;

        int idx = -1;
        int gteCount = citations.length;
        int lteCount = 0;
        int h = 0;
        while (h < citations[citations.length - 1]) {

            if (needToUpdate) {
                if (h != sameNum) {
                    // update gte, lte manually
                    sameEndIdx = h - 1;
                    int sameCount = sameEndIdx - sameStartIdx + 1;
                    gteCount -= sameCount;
                    lteCount += sameCount;

                    needToUpdate = false;
                }
            }

            if (idx == -1) {
                if (h == citations[0]) {
                    idx = 0;
                }
            }

            if (h > gteCount || h < lteCount) {
                 return h - 1;
            } else {
                answer = h - 1;
            }


            if (idx < citations.length - 1 && h == citations[idx + 1] && !needToUpdate) {
                needToUpdate = true;
                sameNum = h;
                sameStartIdx = idx;
                idx++;
                continue;
            }

            if (idx != -1 && !needToUpdate) {
                if (h == citations[idx]) {
                    gteCount--;
                    lteCount++;
                }
            }

            if (idx < citations.length - 1 && h + 1 == citations[idx + 1]) {
                idx++;
            }

            if (needToUpdate) {

            } else {
                h++;
            }
        }

        if (needToUpdate) {
            sameEndIdx = citations.length - 1;

        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers42747 programmers42747 = new Programmers42747();
        int solution = programmers42747.solution(
                new int[]{2,3,3,3,4,5,5,5}
        );

        System.out.println(solution);

    }
}
