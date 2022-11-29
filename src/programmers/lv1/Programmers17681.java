package programmers.lv1;

/**
 * [1차] 비밀지도
 */
public class Programmers17681 {

    public String transToSharp(String str, int n) {
        StringBuilder result = new StringBuilder();
        char[] chars = str.toCharArray();
        if (chars.length < n) {
            for (int i = 0; i < n - chars.length; i++) {
                result.append(" ");
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                result.append("#");
            } else if (chars[i] == '0') {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] orResult = new String[n];
        for (int i = 0; i < n; i++) {
            String str = Integer.toBinaryString(arr1[i] | arr2[i]);
            String s = transToSharp(str, n);
            orResult[i] = s;
        }

        return orResult;
    }

    public static void main(String[] args) {
        Programmers17681 programmers17681 = new Programmers17681();
        String[] solution = programmers17681.solution(
            6, new int[]{46,33,33,22,31,50}, new int[]{27,56,19,14,14,10}
        );
        for (String str : solution) {
            System.out.println(str);
        }

        String s = Integer.toBinaryString(31 | 14);
        System.out.println(s);
    }
}
