package programmers.lv2;

/**
 * 타겟 넘버
 */
public class Programmers43165_2 {

    private int targetNumberCount = 0;

    public int solution(int[] numbers, int target) {

        boolean[] marked = new boolean[numbers.length];
        go(0, 0, numbers, marked, target);

        return targetNumberCount;
    }

    private void go(int idx, int subResult, int[] numbers, boolean[] marked, int target) {
        if (idx == numbers.length) {
            if (subResult == target) {
                targetNumberCount++;
            }
            return;
        }

        go(idx + 1, subResult + numbers[idx], numbers, marked, target);
        go(idx + 1, subResult - numbers[idx], numbers, marked, target);
    }

    public static void main(String[] args) {
        Programmers43165_2 programmers431652 = new Programmers43165_2();
        int solution = programmers431652.solution(
                new int[]{4,1,2,1}, 4
        );
        System.out.println(solution);
    }
}
