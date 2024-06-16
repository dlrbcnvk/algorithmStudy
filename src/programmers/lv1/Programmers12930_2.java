package programmers.lv1;

/**
 * 이상한 문자 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12930
 */
public class Programmers12930_2 {

    public String solution(String s) {
        String[] arr = s.split(" ", -1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(transform(arr[i]));
            if (i < arr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public String transform(String s) {
        String[] split = s.split("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 0) {
                // 짝수
                sb.append(split[i].toUpperCase());
            } else {
                // 홀수
                sb.append(split[i].toLowerCase());
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Programmers12930_2 programmers129302 = new Programmers12930_2();

        String solution = programmers129302.solution(" try hello world ");
        System.out.println(solution);
    }
}
