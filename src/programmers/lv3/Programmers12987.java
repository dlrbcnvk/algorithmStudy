package programmers.lv3;

import java.util.Arrays;

/**
 * 숫자게임
 * 이분탐색
 */
public class Programmers12987 {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        int n = A.length;
        Arrays.sort(A);
        Arrays.sort(B);
        boolean[] marked = new boolean[n];
        for (int i = 0; i < n; i++) {
            int lo = 0;
            int hi = n - 1;
            while (lo < hi) {
                int mid = (hi + lo) / 2;
                if (A[i] < B[mid]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            if (marked[hi]) {
                if (hi == n - 1) {
                    continue;
                }
                while (hi < n) {
                    if (marked[hi]) {
                        hi++;
                    } else {
                        break;
                    }
                }
            }
            if (hi == n) {
                break;
            }
//            if (A[i] > B[hi]) {
//                marked[hi] = true;
//                continue;
//            }
            if (hi < n && !marked[hi] && A[i] < B[hi]) {
                marked[hi] = true;
                answer++;
            } else {
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers12987 programmers440339_1412 = new Programmers12987();
        int solution = programmers440339_1412.solution(
                new int[]{5,5,5,5},
                new int[]{1,1,1,1}
        );
        System.out.println(solution);
    }
}
