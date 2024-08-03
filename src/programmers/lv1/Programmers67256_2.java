package programmers.lv1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 키패드 누르기
 */
public class Programmers67256_2 {

    private static String ONE = "1";
    private static String TWO = "2";
    private static String THREE = "3";
    private static String FOUR = "4";
    private static String FIVE = "5";
    private static String SIX = "6";
    private static String SEVEN = "7";
    private static String EIGHT = "8";
    private static String NINE = "9";
    private static String STAR = "*";
    private static String ZERO = "0";
    private static String SHARP = "#";

    private static String LEFT = "left";
    private static String RIGHT = "right";
    private static String L = "L";
    private static String R = "R";

    class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
        public int getDistancePower(Point target) {
            int distanceX = this.getX() - target.getX();
            int distanceY = this.getY() - target.getY();
            return Math.abs(distanceX) + Math.abs(distanceY);
        }
    }

    public String solution(int[] numbers, String hand) {

        Map<String, Point> keypadMap = initKeypadMap();

        List<String> touchResult = new ArrayList<>();
        Point currentLeftPoint = keypadMap.get(STAR);
        Point currentRightPoint = keypadMap.get(SHARP);
        for (int number : numbers) {
            String numberStr = String.valueOf(number);
            Point targetPoint = keypadMap.get(numberStr);
            if (ONE.equals(numberStr) || FOUR.equals(numberStr) || SEVEN.equals(numberStr)) {
                currentLeftPoint = targetPoint;
                touchResult.add(L);
            } else if (THREE.equals(numberStr) || SIX.equals(numberStr) || NINE.equals(numberStr)) {
                currentRightPoint = targetPoint;
                touchResult.add(R);
            } else {
                int currentLeftDistance = currentLeftPoint.getDistancePower(targetPoint);
                int currentRightDistance = currentRightPoint.getDistancePower(targetPoint);
                if (currentLeftDistance == currentRightDistance) {
                    if (hand.equals(LEFT)) {
                        currentLeftPoint = targetPoint;
                        touchResult.add(L);
                    } else if (hand.equals(RIGHT)) {
                        currentRightPoint = targetPoint;
                        touchResult.add(R);
                    }
                } else if (currentLeftDistance < currentRightDistance) {
                    currentLeftPoint = targetPoint;
                    touchResult.add(L);
                } else {
                    currentRightPoint = targetPoint;
                    touchResult.add(R);
                }
            }
        }

        return touchResult.stream().collect(Collectors.joining());
    }

    private Map<String, Point> initKeypadMap() {
        Map<String, Point> keypadMap = new HashMap<>();
        keypadMap.put(ONE, new Point(0, 0));
        keypadMap.put(TWO, new Point(0, 1));
        keypadMap.put(THREE, new Point(0, 2));
        keypadMap.put(FOUR, new Point(1, 0));
        keypadMap.put(FIVE, new Point(1, 1));
        keypadMap.put(SIX, new Point(1, 2));
        keypadMap.put(SEVEN, new Point(2, 0));
        keypadMap.put(EIGHT, new Point(2, 1));
        keypadMap.put(NINE, new Point(2, 2));
        keypadMap.put(STAR, new Point(3, 0));
        keypadMap.put(ZERO, new Point(3, 1));
        keypadMap.put(SHARP, new Point(3, 2));
        return keypadMap;
    }


    public static void main(String[] args) {
        Programmers67256_2 programmers672562 = new Programmers67256_2();
        String solution = programmers672562.solution(
                new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2},
                "left"
        );
        System.out.println(solution);
    }
}
