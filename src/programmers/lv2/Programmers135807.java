package programmers.lv2;

/**
 * 숫자 카드 나누기
 * 여러 수의 최대공약수 구하기
 */
public class Programmers135807 {

    public int solution(int[] arrayA, int[] arrayB) {

        boolean cond1 = true;
        int gcd1 = arrayA[0];
        for (int i = 1; i < arrayA.length; i++) {
            gcd1 = getGCD(gcd1, arrayA[i]);
        }
        for (int b : arrayB) {
            if (b % gcd1 == 0) {
                cond1 = false;
                break;
            }
        }

        boolean cond2 = true;
        int gcd2 = arrayB[0];
        for (int i = 1; i < arrayB.length; i++) {
            gcd2 = getGCD(gcd2, arrayB[i]);
        }
        for (int a : arrayA) {
            if (a % gcd2 == 0) {
                cond2 = false;
                break;
            }
        }

        if (cond1 && cond2) {
            return Math.max(gcd1, gcd2);
        } else if (cond1) {
            return gcd1;
        } else if (cond2) {
            return gcd2;
        } else {
            return 0;
        }
    }

    public int getGCD(int a, int b) {
        while (b != 0) {
            int rest = a % b;
            a = b;
            b = rest;
        }
        return a;
    }

    public static void main(String[] args) {
        Programmers135807 programmers135807 = new Programmers135807();
        int solution = programmers135807.solution(
                new int[]{10,20}, new int[]{5,17}
        );
        System.out.println(solution);
    }
}
