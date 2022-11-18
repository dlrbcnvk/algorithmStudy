package sedgewick_book.chapter05;

/**
 * 코드를 적었지만 아직 이해를 완전히 하지 못함
 */
public class KMP {
    private String pat;
    private int[][] dfa;

    public KMP(String pat) {
        // 패턴에서 DFA 만들기
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            //dfa[][j] 계산
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X]; // 미스매치의 경우, 복제
            }
            dfa[pat.charAt(j)][j] = j + 1; // 매치의 경우, 설정
            X = dfa[pat.charAt(j)][X]; // 재시작 상태 업데이트
        }
    }

    public int search(String txt) {
        // txt에서 dfa의 동작을 시뮬레이션
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) {
            return i - M;
        } else {
            return N;
        }
    }

    public static void main(String[] args) {
        String pat = "eabcd";
        String txt = "abbccddeabcdeabbceeddabbdcdcdebaccaaaddcebeacd";
        KMP kmp = new KMP(pat);
        System.out.println("text:   " + txt);
        int offset = kmp.search(txt);
        System.out.print("pattern:");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pat);
    }
}
