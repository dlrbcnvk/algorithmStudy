package programmers.lv1;

/**
 * 문자열 내 p와 y의 개수
 */
public class Programmers12916 {

    boolean solution(String s) {

        int pCount = 0;
        int yCount = 0;
        for (char c : s.toCharArray()) {
            if (c == 'p' || c == 'P') {
                pCount++;
            } else if (c == 'y' || c == 'Y') {
                yCount++;
            }
        }

        return pCount == yCount;
    }

    public static void main(String[] args) {
        Programmers12916 programmers12916 = new Programmers12916();
        boolean solution = programmers12916.solution("pPoooyY");
        System.out.println(solution);
    }
}
