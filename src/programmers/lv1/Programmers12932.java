package programmers.lv1;

/**
 * 자연수 뒤집어 배열로 만들기
 */
public class Programmers12932 {

    public int[] solution(long n) {
        String s = String.valueOf(n);
        String[] split = s.split("");

        int[] answer = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            answer[i] = Integer.valueOf(split[split.length - 1 - i]);
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers12932 programmers12932 = new Programmers12932();
        int[] solution = programmers12932.solution(12345);

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
