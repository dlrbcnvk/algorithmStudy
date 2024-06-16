package programmers.lv2;

/**
 * 이진 변환 반복하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/70129
 */
public class Programmers70129_2 {
    public int[] solution(String s) {

        int binaryCount = 0;
        int zeroCount = 0;
        while (!"1".equals(s)) {
            int beforeLength = s.length();
            s = s.replaceAll("0", "");
            int afterLength = s.length();
            zeroCount += beforeLength - afterLength;
            s = Integer.toString(s.length(), 2);
            binaryCount++;
        }
        int[] answer = new int[]{binaryCount, zeroCount};
        return answer;
    }

    public static void main(String[] args) {
        Programmers70129_2 programmers701292 = new Programmers70129_2();
        int[] solution = programmers701292.solution("110010101001");

        System.out.println(solution[0] + " " + solution[1]);
    }
}
