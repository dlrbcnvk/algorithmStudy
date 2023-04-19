package programmers.lv2;

/**
 * 문자열 압축
 */
public class Programmers60057 {

    public int solution(String s) {

        int answer = s.length();
        int length;
        for (int n = 1; n <= s.length() / 2; n++) {
            length = getLength(n, s);
            answer = Math.min(answer, length);
        }
        return answer;
    }

    public int getLength(int n, String s) {
        StringBuilder sb = new StringBuilder();

        int len = s.length();
        String currentSub;
        String nextSub;

        int count = 1;
        for (int i = 0; i < len; i += n) {
            int endIdx = Math.min(i + n, len);
            currentSub = s.substring(i, endIdx);

            // current 가 마지막인 경우
            if (endIdx == len) {
                if (count > 1) {
                    sb.append(count);
                }
                sb.append(currentSub);
                return sb.length();
            }

            int nextEndIdx = Math.min(endIdx + n, len);
            nextSub = s.substring(endIdx, nextEndIdx);
            if (currentSub.equals(nextSub)) {
                count++;
            } else {
                if (count > 1) {
                    sb.append(count);
                }
                sb.append(currentSub);
                count = 1;
            }

        }

        return sb.length();
    }

    public static void main(String[] args) {
        Programmers60057 programmers60057 = new Programmers60057();
        int solution = programmers60057.solution("aabbaccc");
        System.out.println(solution);
    }
}
