package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 뒤에 있는 큰 수 찾기
 * dp. 뒤에서부터 기록하기
 */
public class Programmers154539 {

    public int[] solution(int[] numbers) {

        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        for (int i = answer.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] < numbers[j]) {
                    answer[i] = numbers[j];
                    break;
                } else if (numbers[i] >= numbers[j]) {
                    if (answer[j] == -1) {
                        answer[i] = -1;
                        break;
                    } else if (numbers[i] < answer[j]) {
                        answer[i] = answer[j];
                        break;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers154539 programmers154539 = new Programmers154539();
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 1000000; i >= 1; i--) {
            tempList.add(i);
        }
        int[] solution = programmers154539.solution(
                tempList.stream()
                        .mapToInt(i->i)
                        .toArray()
        );

        for (int num : solution) {
            System.out.print(num + " ");
        }
    }
}
