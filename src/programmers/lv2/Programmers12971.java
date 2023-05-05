package programmers.lv2;

/**
 * 스티커 모으기 (2)
 * 이렇게 하는게 아닌듯
 * 일단 보류 미해결
 */
public class Programmers12971 {
    int n;
    boolean[] marked;
    int maxSum = Integer.MIN_VALUE;

    private enum Direction {
        LEFT, RIGHT
    }

    public int solution(int sticker[]) {

        this.n = sticker.length;
        marked = new boolean[n];

        if (sticker.length == 1) {
            return sticker[0];
        } else if (sticker.length == 2) {
            return Math.max(sticker[0], sticker[1]);
        }



        return 1;
    }

    private void go(int idx, int[] sticker, boolean[] marked, int sum) {
        int leftTwo = getStepIdx(idx, 2, sticker.length, Direction.LEFT);
        int rightTwo = getStepIdx(idx, 2, sticker.length, Direction.RIGHT);
        // 종료 조건


    }

    private int getStepIdx(int idx, int step, int size, Direction direction) {
        if (direction == Direction.LEFT) {
            return (idx - step) < 0 ? idx - step + size : idx - step;
        } else {
            return (idx + step) > 0 ? idx + step - size : idx + step;
        }
    }


    public static void main(String[] args) {
        Programmers12971 programmers12971 = new Programmers12971();
        int solution = programmers12971.solution(new int[]{14, 6, 5, 11, 3, 9, 2, 10, 8});
        System.out.println(solution);
    }
}
