package programmers.lv2;

/**
 * 뒤에 있는 큰 수 찾기
 * 미해결
 */
public class Programmers154539 {

    public int[] solution(int[] numbers) {

//        // 이중 for 문 - 시간초과
//        // 배열길이 최대 1,000,000 -> 조 단위의 계산량
//        int n = numbers.length;
//        int[] result = new int[n];
//        for (int i = 0; i < n; i++) {
//            int afterGt = -1;
//            for (int j = i + 1; j < n; j++) {
//                if (numbers[i] < numbers[j]) {
//                    afterGt = numbers[j];
//                    break;
//                }
//            }
//            result[i] = afterGt;
//        }
//
//        return result;

        return null;
    }

    public static void main(String[] args) {
        Programmers154539 programmers154539 = new Programmers154539();
        int[] solution = programmers154539.solution(
                new int[]{2,3,3,5}
        );

        for (int num : solution) {
            System.out.print(num + " ");
        }
    }
}
