package tutorial;

public class CharTest {

    public String solution(String s, int n) {
        char[] arr = s.toCharArray();
        String answer = "";
        for (int i=0; i<arr.length; i++) {
            if (" ".equals(Character.toString(arr[i]))) {
                answer += Character.toString(arr[i]);
                continue;
            }

            char value = arr[i];
            if (value < 91 && value + n >= 91) {
                answer += Character.toString(value + n - 26);
            }
            else if (value >= 91 && value + n >= 117) {
                answer += Character.toString(value + n - 26);
            }
            else {
                answer += Character.toString(value + n);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        CharTest charTest = new CharTest();
        String solution = charTest.solution("a B z", 4);
        System.out.println(solution);
    }
}
