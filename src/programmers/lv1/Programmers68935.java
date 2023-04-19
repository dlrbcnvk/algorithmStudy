package programmers.lv1;

/**
 * 3진법 뒤집기
 */
public class Programmers68935 {

    public int solution(int n) {
        String s = Integer.toString(n, 3);
        String reversedStr = new StringBuilder(s).reverse().toString();
        Integer result = Integer.parseInt(reversedStr, 3);
        return result;
    }

    public static void main(String[] args) {
        Programmers68935 programmers68935 = new Programmers68935();
        int solution = programmers68935.solution(45);
        System.out.println(solution);
    }
}
