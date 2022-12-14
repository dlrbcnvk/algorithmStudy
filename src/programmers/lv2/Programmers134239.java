package programmers.lv2;

import java.util.ArrayList;

/**
 * 우박수열 정적분
 * 예시를 그림으로 직접 그리면서 했음
 */
public class Programmers134239 {

    public double calculateArea(int preK, int k) {
        return (preK + k) / 2D;
    }

    public double[] solution(int k, int[][] ranges) {

        ArrayList<Double> areaList = new ArrayList<>();
        int preK = k;

        while (k > 1) {
            if (k % 2 == 0) {
                k = k / 2;
            } else {
                k = k * 3 + 1;
            }

            areaList.add(calculateArea(preK, k));
            preK = k;
        }



        double[] areaArr = new double[areaList.size()];
        int idx = 0;
        for (Double area : areaList) {
            areaArr[idx] = area;
            idx++;
        }

        int endPoint = areaArr.length;
        double[] answer = new double[ranges.length];
        idx = 0;
        for (int[] range : ranges) {
            int start = range[0];
            int end = endPoint + range[1];
            if (start == end) {
                answer[idx] = 0D;
            } else if (start > end) {
                answer[idx] = -1D;
            } else {
                double sum = 0;
                for (int i = start; i < end; i++) {
                    sum += areaArr[i];
                }
                answer[idx] = sum;
            }

            idx++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers134239 programmers134239 = new Programmers134239();
        double[] solution = programmers134239.solution(
                5, new int[][]{{0,0}, {0,-1}, {2,-3}, {3,-3}}
        );

        for (double d : solution) {
            System.out.print(d + " ");
        }
    }
}
