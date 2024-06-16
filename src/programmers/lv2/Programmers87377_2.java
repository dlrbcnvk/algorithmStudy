package programmers.lv2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Programmers87377_2 {

    class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public String[] solution(int[][] line) {
        Set<Point> pointSet = new HashSet<>();
        for (int i = 0; i < line.length - 1; i++) {
            long A, B, C, D, E, F;
            A = line[i][0];
            B = line[i][1];
            E = line[i][2];
            for (int j = i + 1; j < line.length; j++) {
                C = line[j][0];
                D = line[j][1];
                F = line[j][2];

                double divider = (B * C) - (A * D);
                if (divider == 0) {
                    continue;
                }
                double x = ((D * E) - (B * F)) / divider;
                double y = ((A * F) - (C * E)) / divider;
                if (x == (long) x && y == (long) y) {
                    pointSet.add(new Point((long)x, (long)y));
                }
            }
        }

        long minY = Long.MAX_VALUE;
        long minX = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        long maxX = Long.MIN_VALUE;
        for (Point point : pointSet) {
            minY = Math.min(minY, point.y);
            minX = Math.min(minX, point.x);
            maxY = Math.max(maxY, point.y);
            maxX = Math.max(maxX, point.x);
        }

        String[] answer = new String[(int)(maxY - minY + 1)];
        int idx = 0;
        for (long i = maxY; i >= minY; i--) {
            StringBuilder sb = new StringBuilder();
            for (long j = minX; j <= maxX; j++) {
                Point point = new Point(j, i);
                if (pointSet.contains(point)) {
                    sb.append('*');
                } else {
                    sb.append('.');
                }
            }
            answer[idx] = sb.toString();
            idx++;
        }

        return answer;
    }
    public static void main(String[] args) {
        Programmers87377_2 programmers873772 = new Programmers87377_2();
        String[] solution = programmers873772.solution(new int[][]{
                {1,0,-1000},
                {0,1,1000},
                {0,-1,1},
                {5,-8,-12},
                {5,8,12}

        });
    }
}
