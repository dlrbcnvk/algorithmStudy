package programmers.lv2;

/**
 * 다음 큰 숫자
 */
public class Programmers12911 {

    public int solution(int n) {

        int oneCount = getOneCount(n);

        boolean found = false;
        n++;
        while (!found) {
            int ones = getOneCount(n);
            if (ones == oneCount) {
                return n;
            }

            n++;
        }
        return 0;
    }

    private int getOneCount(int n) {
        int oneCount = 0;
        String s = Integer.toBinaryString(n);
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '1') {
                oneCount++;
            }
        }
        return oneCount;
    }

    public static void main(String[] args) {
        Programmers12911 programmers12911 = new Programmers12911();
        int solution = programmers12911.solution(78);
        System.out.println(solution);
    }
}
