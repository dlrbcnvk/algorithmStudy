package programmers.lv2;

/**
 * 124 나라의 숫자
 */
public class Programmers12899 {

    int n;
    String result = "";

    public void go(int num) {
        if (num <= 3) {
            if (num == 1) {
                result = "1" + result;
            } else if (num == 2) {
                result = "2" + result;
            } else if (num == 3) {
                result = "4" + result;
            }
            return;
        }

        String word124 = "";
        int nextNum = num / 3;
        if (num % 3 == 1) {
            word124 = "1";
        } else if (num % 3 == 2) {
            word124 = "2";
        } else {
            word124 = "4";
            nextNum--;
        }
        result = word124 + result;
        go(nextNum);
    }

    public String solution(int n) {
        this.n = n;

        go(n);

        return result;
    }

    public static void main(String[] args) {
        Programmers12899 programmers12899 = new Programmers12899();
        String solution = programmers12899.solution(13);
        System.out.println(solution);
    }
}
