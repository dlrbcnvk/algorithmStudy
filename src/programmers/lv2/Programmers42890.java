package programmers.lv2;

import java.util.*;

/**
 * 후보키
 * 미해결
 */
public class Programmers42890 {

    private static int count = 0;
    private static Set<String> candidateKeySet = new HashSet<>();

    public int solution(String[][] relation) {

        int[] column = new int[relation[0].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = i;
        }
        boolean[] marked = new boolean[column.length];

        Queue<List<Integer>> queue = new LinkedList<>();
        for (int i = 0; i < marked.length; i++) {
            ArrayList<Integer> idxList = new ArrayList<>();
            idxList.add(i);
            queue.add(idxList);
        }

        while (!queue.isEmpty()) {
            List<Integer> idxList = queue.poll();
            boolean isCandidate = isCandidateKey(idxList, relation);
            if (isCandidate) {
                count++;
            } else {
                Integer lastIdx = idxList.get(idxList.size() - 1);
                for (int i = lastIdx; i < column.length; i++) {
                    ArrayList<Integer> nextIdxList = new ArrayList<>(idxList);
                    nextIdxList.add(i);
                    queue.add(nextIdxList);
                }
            }
        }

        return count;
    }

    private boolean isCandidateKey(List<Integer> idxList, String[][] relation) {
        Set<String> validateDuplicateSet = new HashSet<>();

        for (String[] rel : relation) {
            StringBuilder sb = new StringBuilder();
            for (int idx : idxList) {
                sb.append(rel[idx]);
            }
            String value = sb.toString();
            if (validateDuplicateSet.contains(value)) {
                return false;
            }
            validateDuplicateSet.add(value);
        }

        return true;
    }

    public static void main(String[] args) {
        Programmers42890 programmers42890 = new Programmers42890();
        int solution = programmers42890.solution(
                new String[][]{
                        {"100", "ryan", "music", "2"},
                        {"200", "apeach", "math", "2"},
                        {"300", "tube", "computer", "3"},
                        {"400", "con", "computer", "4"},
                        {"500", "muzi", "music", "3"},
                        {"600", "apeach", "music", "2"}
                }
        );
        System.out.println(solution);
    }
}
