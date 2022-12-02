package programmers.lv2;

/**
 * 짝지어 제거하기
 * 미해결
 */
public class Programmers12973 {

    public String isReplaced(String s) {

        s = s.replaceAll("aa", "")
                .replaceAll("bb", "")
                .replaceAll("cc", "")
                .replaceAll("dd", "")
                .replaceAll("ee", "")
                .replaceAll("ff", "")
                .replaceAll("gg", "")
                .replaceAll("hh", "")
                .replaceAll("ii", "")
                .replaceAll("jj", "")
                .replaceAll("kk", "")
                .replaceAll("ll", "")
                .replaceAll("mm", "")
                .replaceAll("nn", "")
                .replaceAll("oo", "")
                .replaceAll("pp", "")
                .replaceAll("qq", "")
                .replaceAll("rr", "")
                .replaceAll("ss", "")
                .replaceAll("tt", "")
                .replaceAll("uu", "")
                .replaceAll("vv", "")
                .replaceAll("ww", "")
                .replaceAll("xx", "")
                .replaceAll("yy", "")
                .replaceAll("zz", "");
        return s;
    }

    public int solution(String s) {

        String str;
        while (true) {
            str = isReplaced(s);
            if (s.equals(str) || str.equals("")) {
                break;
            }
            s = str;
        }
        if (str.equals("")) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Programmers12973 programmers12973 = new Programmers12973();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            s.append("baabaaabbabb");
        }
        int solution = programmers12973.solution(s.toString());
        System.out.println(solution);
    }
}
