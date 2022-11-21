package programmers.lv3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 단어 변환
 * bfs
 */
public class Programmers43163 {

    class Node {
        String str;
        int value;

        public Node(String str, int value) {
            this.str = str;
            this.value = value;
        }
    }

    public boolean isOnlyOneDifferent(String begin, String word) {
        if (begin.equals(word)) {
            return false;
        }
        char[] beginArr = begin.toCharArray();
        char[] wordArr = word.toCharArray();
        boolean oneDiff = false;
        for (int i = 0; i < beginArr.length; i++) {
            if (beginArr[i] != wordArr[i]) {
                if (!oneDiff) {
                    oneDiff = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public int solution(String begin, String target, String[] words) {

        boolean contains = false;
        for (int i = 0; i < words.length; i++) {
            if (target.equals(words[i])) {
                contains = true;
            }
        }
        if (!contains) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(begin, 0));

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            String str = poll.str;
            int value = poll.value;
            if (str.equals(target)) {
                return value;
            }

            for (String word : words) {
                if (isOnlyOneDifferent(str, word)) {
                    queue.add(new Node(word, value + 1));
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Programmers43163 programmers43163 = new Programmers43163();
        int solution = programmers43163.solution(
                "hit", "cog",
                new String[]{"hot", "dot", "dog", "lot", "log", "cog"}
        );
        System.out.println(solution);
    }
}
