package programmers.lv3;

/**
 * 팰린드롬 최대 길이 문제
 */
public class Programmers12904 {
    public int solution(String s) {

        char[] arr = s.toCharArray();
        int n = arr.length;
        boolean isPalindrome;
        int maxLength = 1;
        for (int i = 0; i < n; i++) {

            // 홀수 판별
            int j = 1;
            isPalindrome = true;
            int count = 1;
            while (isPalindrome) {
                if (i - j < 0 || i + j >= n) {
                    break;
                }
                if (arr[i - j] != arr[i + j]) {
                    break;
                }
                count += 2;
                j++;
            }

            if (maxLength < count) {
                maxLength = count;
            }


            // 짝수 판별
            if (i == n - 1) {
                continue;
            }
            if (arr[i] != arr[i + 1]) {
                continue;
            }
            j = 1;
            isPalindrome = true;
            count = 2;
            while (isPalindrome) {
                if (i - j < 0 || i + 1 + j >= n) {
                    break;
                }
                if (arr[i - j] != arr[i + 1 + j]) {
                    break;
                }
                count += 2;
                j++;
            }
            if (maxLength < count) {
                maxLength = count;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Programmers12904 programmers440339 = new Programmers12904();
        int solution = programmers440339.solution("abacde");
        System.out.println(solution);
    }
}
