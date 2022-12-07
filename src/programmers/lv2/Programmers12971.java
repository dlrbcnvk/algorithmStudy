package programmers.lv2;

/**
 * 스티커 모으기 (2)
 * 이렇게 하는게 아닌듯
 * 일단 보류 미해결
 */
public class Programmers12971 {

    public int solution(int sticker[]) {

        if (sticker.length % 2 == 0) {
            int sum1 = 0;
            for (int i = 0; i < sticker.length; i += 2) {
                sum1 += sticker[i];
            }
            int sum2 = 0;
            for (int i = 1; i < sticker.length; i += 2) {
                sum2 += sticker[i];
            }
            return Math.max(sum1, sum2);
        } else {
            int sum1 = 0;
            for (int i = 0; i < sticker.length - 2; i += 2) {
                sum1 += sticker[i];
            }
            int sum2 = 0;
            for (int i = 1; i < sticker.length; i += 2) {
                sum2 += sticker[i];
            }
            int sum3 = 0;
            for (int i = 2; i < sticker.length; i += 2) {
                sum3 += sticker[i];
            }
            return Math.max(Math.max(sum1, sum2), sum3);
        }
    }

    public static void main(String[] args) {
        Programmers12971 programmers12971 = new Programmers12971();
        int solution = programmers12971.solution(new int[]{14, 6, 5, 11, 3, 9, 2, 10, 8});
        System.out.println(solution);
    }
}
