package programmers.lv0;

/**
 * 평행
 * https://school.programmers.co.kr/learn/courses/30/lessons/120875
 */
public class Programmers120875_2 {

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
        Programmers120875_2 programmers1208752 = new Programmers120875_2();
        int solution = programmers1208752.solution(
                new int[][]{
                        {1,2},{2,1},{3,4},{4,5}
                });
        System.out.println(solution);
    }
}
