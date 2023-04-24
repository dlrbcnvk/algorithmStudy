package programmers.lv1;

/**
 * 크기가 작은 부분문자열
 */
public class Programmers147355 {

    public int solution(String t, String p) {
        int answer = 0;
        for (int i = 0; i <= t.length() - p.length(); i++) {
            String value = t.substring(i, i + p.length());
            if (value.compareTo(p) <= 0) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers147355 programmers147355 = new Programmers147355();
        int solution = programmers147355.solution("3141592", "271");
        System.out.println(solution);
    }
}
