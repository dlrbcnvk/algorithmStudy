package programmers.lv0;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 평행
 */
public class Programmers120875 {

    public int solution(int[][] dots) {

        if (getSlope(dots[0][0], dots[0][1], dots[1][0], dots[1][1]) == getSlope(dots[2][0], dots[2][1], dots[3][0], dots[3][1])) {
            return 1;
        }
        if (getSlope(dots[0][0], dots[0][1], dots[2][0], dots[2][1]) == getSlope(dots[1][0], dots[1][1], dots[3][0], dots[3][1])) {
            return 1;
        }
        if (getSlope(dots[0][0], dots[0][1], dots[3][0], dots[3][1]) == getSlope(dots[1][0], dots[1][1], dots[2][0], dots[2][1])) {
            return 1;
        }
        return 0;
    }

    public double getSlope(int x1, int y1, int x2, int y2) {
        return (double)(y1 - y2) / (x1 - x2);
    }

    public static void main(String[] args) {
        Programmers120875 programmers120875 = new Programmers120875();
        int solution = programmers120875.solution(new int[][]{{1,4},{9,2},{3,8},{11,6}});
        System.out.println(solution);
    }
}
