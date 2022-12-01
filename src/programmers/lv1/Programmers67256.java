package programmers.lv1;

import java.util.HashMap;

/**
 * 키패드 누르기
 */
public class Programmers67256 {

    public double getDistance(Integer[] p1, Integer[] p2) {
        return Math.sqrt(Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]));
    }

    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();

        HashMap<Integer, Integer[]> leftMap = new HashMap<>();
        HashMap<Integer, Integer[]> rightMap = new HashMap<>();
        HashMap<Integer, Integer[]> middleMap = new HashMap<>();
        leftMap.put(1, new Integer[]{0,0});
        leftMap.put(4, new Integer[]{1,0});
        leftMap.put(7, new Integer[]{2,0});
        rightMap.put(3, new Integer[]{0,2});
        rightMap.put(6, new Integer[]{1,2});
        rightMap.put(9, new Integer[]{2,2});
        middleMap.put(2, new Integer[]{0,1});
        middleMap.put(5, new Integer[]{1,1});
        middleMap.put(8, new Integer[]{2,1});
        middleMap.put(0, new Integer[]{3,1});

        Integer[] left = new Integer[]{3,0};
        Integer[] right = new Integer[]{3,2};
        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                answer.append("L");
                left = leftMap.get(num);
            } else if (num == 3 || num == 6 || num == 9) {
                answer.append("R");
                right = rightMap.get(num);
            } else {
                Integer[] targetPoint = middleMap.get(num);
                double leftDistance = getDistance(left, targetPoint);
                double rightDistance = getDistance(right, targetPoint);
                if (leftDistance < rightDistance) {
                    answer.append("L");
                    left = targetPoint;
                } else if (leftDistance > rightDistance) {
                    answer.append("R");
                    right = targetPoint;
                } else {
                    if (hand.equals("left")) {
                        answer.append("L");
                        left = targetPoint;
                    } else if (hand.equals("right")) {
                        answer.append("R");
                        right = targetPoint;
                    }
                }
            }
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        Programmers67256 programmers67256 = new Programmers67256();
        String solution = programmers67256.solution(
                new int[]{1,2,3,4,5,6,7,8,9}, "right"
        );

        System.out.println(solution);
    }
}
