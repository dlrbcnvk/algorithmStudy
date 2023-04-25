package programmers.lv2;

/**
 * 타겟 넘버
 * dfs
 */
public class Programmers43165 {

    private int count = 0;
    private int lastIdx;
    private int target;
    private int[] numbers;

    public void dfs(int idx, int sum) {
        if (idx == lastIdx) {
            if (sum == target) {
                count++;
                return;
            }
            return;
        }
        int next = idx + 1;
        dfs(next, sum + numbers[next]);
        dfs(next, sum - numbers[next]);
    }


    public int solution(int[] numbers, int target) {
        this.lastIdx = numbers.length - 1;
        this.target = target;
        this.numbers = numbers;

        dfs(-1, 0);

        return count;
    }

    public static void main(String[] args) {
        Programmers43165 programmers43165 = new Programmers43165();
        int solution = programmers43165.solution(
                new int[]{1,1,1,1,1}, 3
        );
        System.out.println(solution);
    }
}
