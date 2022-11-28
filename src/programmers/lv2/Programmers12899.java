package programmers.lv2;

/**
 * 124 나라의 숫자
 * 미해결 ㅠㅠ
 */
public class Programmers12899 {

    String[] strings = new String[]{"0", "1", "2", "4"};
    int n;
    String result;

    public void go(String str, int num) {
        if (num == n) {
            this.result = str;
        }



    }

    public String solution(int n) {
        this.n = n;
        int start = 1;
        int end = 3;
        int length = 1;
        int multiplier = 2;
        while (true) {
            if (n <= end) {
                break;
            }
            length++;
            start = end + 1;
            end += Math.pow(3, multiplier);
            multiplier++;
        }
        n = n - start;
        int total = end - start;
        start = 0;
        // ex) 40 ~ 120 -> 0 ~ 80
        int divide = end + 1;


        return "";



    }

    public static void main(String[] args) {
        Programmers12899 programmers12899 = new Programmers12899();
        String solution = programmers12899.solution(50000000);
        System.out.println(solution);
    }
}
