package programmers.lv5;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 방의 개수
 * 배열크기 최대 10만 -> 인접리스트 사용
 * 미해결
 */
public class Programmers49190 {

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
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
            return (x + "," + y).hashCode();
        }
    }

    public int solution(int[] arrows) {



        int startY = 0;
        int startX = 0;

        for (int a : arrows) {
            switch (a) {

            }
        }

        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {
        Programmers49190 programmers49190 = new Programmers49190();
//        int solution = programmers49190.solution();
//        System.out.println(solution);
    }
}
