package programmers.lv2;

/**
 * 스티커 모으기 (2)
 * 이렇게 하는게 아닌듯
 * 일단 보류 미해결
 */
public class Programmers12971 {
    int n;
    int marked[];

    public int solution(int sticker[]) {

        this.n = sticker.length;
        marked = new int[n];

        // i=0을 선택한 경우
        marked[0] = sticker[0];


        // i=1을 선택한 경우

        // i=2, i=n-1을 선택한 경우
        return 1;
    }

    public void reset() {
        for (int i = 0; i < n; i++) {
//            marked[i] = false;
        }
    }

    public static void main(String[] args) {
        Programmers12971 programmers12971 = new Programmers12971();
        int solution = programmers12971.solution(new int[]{14, 6, 5, 11, 3, 9, 2, 10, 8});
        System.out.println(solution);
    }
}
