package programmers.lv2;

/**
 * 예상 대진표
 */
public class Programmers12985 {

    public boolean isMeeting(int larger, int smaller) {
        if (larger - smaller == 1 && larger % 2 == 0) {
            return true;
        }
        return false;
    }

    public int nextNumber(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return (n + 1) / 2;
        }
    }

    public int solution(int n, int a, int b) {
        int answer = 0;

        int round = 1;

        int larger, smaller;
        if (a > b) {
            larger = a;
            smaller = b;
        } else {
            larger = b;
            smaller = a;
        }

        while (true) {
            if (isMeeting(larger, smaller)) {
                return round;
            } else {
                larger = nextNumber(larger);
                smaller = nextNumber(smaller);
                round++;
            }
        }
    }

    public static void main(String[] args) {
        Programmers12985 programmers12985 = new Programmers12985();
        int solution = programmers12985.solution(8, 4, 7);
        System.out.println(solution);
    }
}
