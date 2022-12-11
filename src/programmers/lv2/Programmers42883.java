package programmers.lv2;


/**
 * 큰 수 만들기
 * '0 ~ 첫째자리로 가능한 마지노선' 사이의 최댓값
 * '첫째 인덱스 ~ 둘째자리로 가능한 마지노선'의 최댓값
 * ...
 */
public class Programmers42883 {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();

        String[] split = number.split("");
        boolean[] marked = new boolean[split.length];
        int maxIdx = -1;
        String maxValue;
        for (int i = k; i < split.length; i++) {

            int j = maxIdx + 1;
            maxValue = " ";

            while (j <= i) {
                if (!marked[j] && split[j].equals("9")) {
                    maxIdx = j;
                    break;
                }

                if (!marked[j] && split[j].compareTo(maxValue) > 0) {
                    maxValue = split[j];
                    maxIdx = j;

                }
                j++;
            }

            marked[maxIdx] = true;
        }

        for (int i = 0; i < split.length; i++) {
            if (marked[i]) {
                sb.append(split[i]);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Programmers42883 programmers42883 = new Programmers42883();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append((int)(Math.random() * 10));
        }
        long startTime = System.currentTimeMillis();
        String solution = programmers42883.solution("4177252841", 4);
        long endTime = System.currentTimeMillis();
        System.out.println(solution);
        System.out.println(endTime - startTime);
    }
}
