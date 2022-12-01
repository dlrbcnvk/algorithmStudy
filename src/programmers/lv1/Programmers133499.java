package programmers.lv1;

/**
 * 옹알이 (2)
 */
public class Programmers133499 {

    private boolean canSpeak(String word) {

        String post = "";
        while (true) {
            if (word.startsWith("aya") && !post.equals("aya")) {
                post = "aya";
                word = word.replaceFirst("aya", "");
            }
            else if (word.startsWith("ye") && !post.equals("ye")) {
                post = "ye";
                word = word.replaceFirst("ye", "");
            }
            else if (word.startsWith("woo") && !post.equals("woo")) {
                post = "woo";
                word = word.replaceFirst("woo", "");
            } else if (word.startsWith("ma") && !post.equals("ma")) {
                post = "ma";
                word = word.replaceFirst("ma", "");
            } else {
                break;
            }
        }

        if (word.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public int solution(String[] babbling) {
        int answer = 0;

        for (int i = 0; i < babbling.length; i++) {
            if (canSpeak(babbling[i])) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers133499 programmers133499 = new Programmers133499();
        int solution = programmers133499.solution(
                new String[]{"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"}
        );
        System.out.println(solution);
    }
}
