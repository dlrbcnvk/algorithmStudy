package programmers.lv2;

/**
 * 점 찍기
 * https://school.programmers.co.kr/learn/courses/30/lessons/140107
 */
public class Programmers140107 {

    public long solution(int k, int d) {


        long answer = 0;
        for (int y = 0; y <= d; y += k) {
            answer += getPointsInY(y, k, d);
        }

        return answer;
    }

    private long getPointsInY(int y, int k, int d) {

        // int * int 를 할 때 최댓값 범위를 넘어가므로 곱셈하는 것마다 long 타입으로 바꿔줘야 함
        long maxX = (long) Math.sqrt((long)d * d - (long)y * y);
        return (maxX / k) + 1;
    }

    public static void main(String[] args) {
        Programmers140107 programmers140107 = new Programmers140107();
        long solution = programmers140107.solution(50000, 100000);
        System.out.println(solution);
    }
}
