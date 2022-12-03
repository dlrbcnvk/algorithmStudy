package programmers.lv0;

/**
 * 숫자 찾기
 */
public class Programmers120904 {

    public int solution(int num, int k) {

        String s = String.valueOf(k);
        String[] split = String.valueOf(num).split("");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals(s)) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Programmers120904 programmers120904 = new Programmers120904();
        int solution = programmers120904.solution(232443, 4);
        System.out.println(solution);
    }
}
