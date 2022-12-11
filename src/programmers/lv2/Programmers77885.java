package programmers.lv2;

/**
 * 2개 이하로 다른 비트
 * 값을 1씩 올리면서 기존 값과 이진코드 일일이 비교하는 방식 -> 시간초과
 * 짝수인 경우 / 홀수인 경우 나눠서 처리
 * 짝수인 경우
 * 10 -> 01
 *
 * 홀수인 경우
 * 111 -> 1011
 * 101 -> 110
 * 100111 -> 101011
 * 기존 이진코드를 뒤에서부터 확인하며 [0, 1] 패턴을 찾아 switch
 * 못찾고 맨 앞이 1인 경우: 맨앞 1 -> 10
 */
public class Programmers77885 {

    private long minimumGreaterThan(long num) {
        if (num % 2 == 0) {
            return num + 1;
        }

        // 홀수인 경우
        String[] split = Long.toBinaryString(num).split("");
        int lastOne;
        int firstZero;
        boolean updated = false;
        for (int i = split.length - 1; i > 0; i--) {
            if (split[i].equals("1") && split[i - 1].equals("0")) {
                lastOne = i;
                firstZero = i - 1;
                split[lastOne] = "0";
                split[firstZero] = "1";
                updated = true;
                break;
            }
        }
        if (split[0].equals("1") && !updated) {
            split[0] = "0";
        }
        StringBuilder str = new StringBuilder();
        if (!updated) {
            str.append("1");
        }
        for (int i = 0; i < split.length; i++) {
            str.append(split[i]);
        }
        return Long.parseLong(str.toString(), 2);
    }

    public long[] solution(long[] numbers) {

        long[] answer = new long[numbers.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = minimumGreaterThan(numbers[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers77885 programmers77885 = new Programmers77885();
        long[] solution = programmers77885.solution(new long[]{1,2});

        for (long num : solution) {
            System.out.print(num + " ");
        }
    }
}
