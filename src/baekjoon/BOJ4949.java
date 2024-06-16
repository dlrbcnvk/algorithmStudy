package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 균형잡힌 세상
 * https://www.acmicpc.net/problem/4949
 */
public class BOJ4949 {

    private static String DOT = ".";
    private static char LEFT_SMALL = '(';
    private static char LEFT_BIG = '[';
    private static char RIGHT_SMALL = ')';
    private static char RIGHT_BIG = ']';
    private static String yes = "yes";
    private static String no = "no";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = br.readLine();
            if (str.equals(DOT)) break;
            char[] charArray = str.toCharArray();
            Stack<Character> stack = new Stack<>();
            boolean isCorrect = true;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == LEFT_SMALL || charArray[i] == LEFT_BIG) {
                    stack.push(charArray[i]);
                } else if (charArray[i] == RIGHT_BIG) {
                    if (!stack.isEmpty() && stack.peek().equals(LEFT_BIG)) {
                        stack.pop();
                    } else {
                        isCorrect = false;
                        break;
                    }
                } else if (charArray[i] == RIGHT_SMALL) {
                    if (!stack.isEmpty() && stack.peek().equals(LEFT_SMALL)) {
                        stack.pop();
                    } else {
                        isCorrect = false;
                        break;
                    }
                }
            }

            if (!stack.isEmpty() || !isCorrect) {
                sb.append(no);
            } else {
                sb.append(yes);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
