package programmers.lv2;

import java.util.Arrays;

/**
 * 가장 큰 수
 * 숫자 두개 붙여보고, 바꿔서 붙여보기
 * edge case : 0000 -> 0
 * 가장 큰 수를 문자열로 바꾸어야 함
 */
public class Programmers42746 {

    static class Number implements Comparable<Number> {
        String numStr;

        public Number(int number) {
            this.numStr = String.valueOf(number);
        }

        @Override
        public int compareTo(Number num) {
            int integer1 = Integer.parseInt(this.numStr + num.numStr);
            int integer2 = Integer.parseInt(num.numStr + this.numStr);
            if (integer1 > integer2) {
                return -1;
            } else if (integer1 < integer2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public String solution(int[] numbers) {
        StringBuilder s = new StringBuilder();

        int n = numbers.length;
        Number[] numArray = new Number[n];

        for (int idx = 0; idx < numbers.length; idx++) {
            numArray[idx] = new Number(numbers[idx]);
        }
        Arrays.sort(numArray);

        for (int i = 0; i < numArray.length; i++) {
            s.append(numArray[i].numStr);
        }
        if (s.charAt(0) == '0') {
            return "0";
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Programmers42746 programmers42746 = new Programmers42746();
//        int[] ints = new int[1000];
//        for (int i = 0; i < 1000; i++) {
//            ints[i] = (int) (Math.random() * 1000);
//        }

        int[] ints = new int[]{0,0,0,0};

        String solution = programmers42746.solution(ints);
        System.out.println(String.valueOf(Integer.valueOf(solution)));
    }
}
