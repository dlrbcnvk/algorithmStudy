package programmers.lv2;

import java.util.Arrays;

/**
 * 전화번호 목록
 * 어떤 번호가 다른 번호의 접두어가 될 수 있는 경우 compareTo -> 1
 * 정렬한 다음, 바로 뒤에 있는 것의 접두어가 되는지만 보면 됨
 */
public class Programmers42577 {

    public boolean solution(String[] phone_book) {

        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Programmers42577 programmers42577 = new Programmers42577();
        String[] arr = new String[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf((int) (Math.random() * 1000000000));
        }
        boolean solution = programmers42577.solution(
                arr
        );
        System.out.println(solution);
    }
}
