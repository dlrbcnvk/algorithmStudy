package programmers.lv2;

/**
 * 스킬트리
 */
public class Programmers49993 {

    public int solution(String skill, String[] skill_trees) {

        int answer = 0;
        String expression = "[^" + skill + "]";
        for (String skillTree : skill_trees) {
            String s = skillTree.replaceAll(expression, "");
            if (skill.startsWith(s)) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers49993 programmers49993 = new Programmers49993();
        int solution = programmers49993.solution(
                "CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"}
        );
        System.out.println(solution);
    }
}
