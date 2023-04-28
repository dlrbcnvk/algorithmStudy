package programmers.lv1;

/**
 * 카드 뭉치
 */
public class Programmers159994 {

    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int idx1 = 0;
        int idx2 = 0;
        int idxGoal = 0;

        while (idxGoal < goal.length) {
            if (idx1 < cards1.length && goal[idxGoal].equals(cards1[idx1])) {
                idxGoal++;
                idx1++;
            } else if (idx2 < cards2.length && goal[idxGoal].equals(cards2[idx2])) {
                idxGoal++;
                idx2++;
            } else {
                break;
            }
        }

        if (idxGoal == goal.length) {
            return "Yes";
        } else {
            return "No";
        }

    }
}
