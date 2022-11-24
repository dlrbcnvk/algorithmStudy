package programmers.lv2;

/**
 * 모음사전
 * 완전탐색 - 재귀
 */
public class Programmers84512 {

    int count = 0;
    boolean found = false;
    char[] dic;
    String word;

    public void go(String str, int count) {
        if (str.equals(word)) {
            found = true;
            this.count = count;
            return;
        }
        if (str.length() == 5) {
            return;
        }
        if (found) {
            return;
        }

        for (char c : dic) {
            if (!found) {
                this.count++;
                go(str + c, this.count);
            }
        }
    }

    public int solution(String word) {

        this.word = word;
        this.dic = new char[]{'A', 'E', 'I', 'O', 'U'};

        go("", 0);

        return count;
    }

    public static void main(String[] args) {
        Programmers84512 programmers84512 = new Programmers84512();
        int solution = programmers84512.solution("EIO");
        System.out.println(solution);
    }
}
