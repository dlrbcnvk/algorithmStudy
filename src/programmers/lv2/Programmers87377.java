package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 교점에 별 만들기
 * 2차원 배열 다루어 보기
 * 좌표 범위가 주어지지 않았기 때문에 x,y는 long 으로 표현함
 */
public class Programmers87377 {

    class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public String[] solution(int[][] line) {

        ArrayList<Point> pointList = new ArrayList<>();

        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {

                if (!isCross(line[i], line[j])) {
                    continue;
                }

                long a = line[i][0];
                long b = line[i][1];
                long c = line[i][2];

                long p = line[j][0];
                long q = line[j][1];
                long r = line[j][2];

                long divisorX = (((-q) * c) + (b * r));
                long divisorY = ((p * c) - (a * r));
                long dividend = ((a*q) - (p*b));

                long restX = divisorX % dividend;
                long restY =  divisorY % dividend;

                if (restX != 0 || restY != 0) {
                    continue;
                }

                Point point = new Point(divisorX / dividend, divisorY / dividend);
                pointList.add(point);
            }
        }

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        for (Point point : pointList) {
            long x = point.x;
            long y = point.y;

            if (x < minX) { minX = x; }
            if (x > maxX) { maxX = x; }
            if (y < minY) { minY = y; }
            if (y > maxY) { maxY = y; }
        }

        for (Point point : pointList) {
            point.x -= minX;
            point.y -= minY;
        }

        int tempX = (int) (maxX - minX + 1);
        int tempY = (int) (maxY - minY + 1);

        String[][] board = new String[tempY][tempX];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ".";
            }
        }

        for (Point point : pointList) {
            long x = point.x;
            long y = point.y;

            board[(int) ((maxY - minY) - y)][(int)x] = "*";
        }

        String[] answer = new String[board.length];
        for (int i = 0; i < board.length; i++) {
            answer[i] = "";
            for (int j = 0; j < board[0].length; j++) {
                answer[i] += board[i][j];
            }
        }

        return answer;
    }

    public boolean isCross(int[] line1, int[] line2) {
        long a = line1[0];
        long b = line1[1];

        long p = line2[0];
        long q = line2[1];

        if ((a * q) - (p * b) == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Programmers87377 programmers87377 = new Programmers87377();
        String[] solution = programmers87377.solution(
                new int[][]{{100000,1,-100000},{100000,-100000,55},{-1,-1,1}}
        );


        System.out.println();
        System.out.println();
        for (String line : solution) {
            System.out.println(line);
        }
    }
}
