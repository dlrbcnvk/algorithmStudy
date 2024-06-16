package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2805_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = split[0];
        long m = split[1];
        long[] treesFromIdx = new long[n];
        int[] heights = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(heights);
        long trees = 0;
        for (int i = heights.length - 2; i >= 0; i--) {
            trees = trees + (long) (heights.length - 1 - i) * (heights[i + 1] - heights[i]);
            treesFromIdx[i] = trees;
        }
        int st = 0; // inclusive
        int en = treesFromIdx.length - 1; // inclusive

        // 나무가 부족한 높이 중에서 가장 낮은 높이 찾기
        while (st < en) {
            int mid = (st + en) / 2;
            if (treesFromIdx[mid] < m) {
                // mid 기준으로 나무가 부족할 경우 en 를 줄여서 높이 범위를 낮춰야 함
                en = mid;
            } else if (treesFromIdx[mid] > m) {
                // mid 기준으로 나무가 초과할 경우 st 를 mid + 1로 하여 나무가 부족한 지점을 파악하고자 함.
                // 최종적으로 부족한 지점을 파악한 다음에 높이를 줄여나가기 위하여
                st = mid + 1;
            } else {
                // 우연히도 나무 양이 딱 맞는 높이를 찾으면 땡큐
                System.out.println(heights[mid]);
                return;
            }
        }

//        System.out.println("en=" + en);

        // 특정 포인트(en)에서 자르는 높이를 점점 낮춰야 함
        int height = heights[en];
        height -= (m - treesFromIdx[en]) / (n - en);
        if ((m - treesFromIdx[en]) % (n - en) != 0) {
            height--;
        }

        System.out.println(height);
    }
}
