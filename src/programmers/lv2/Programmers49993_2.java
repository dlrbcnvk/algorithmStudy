package programmers.lv2;

/**
 * 스킬 트리
 */
public class Programmers49993_2 {
    public int solution(String skill, String[] skill_trees) {

        int availableSkillTreeCount = 0;
        for (String skill_tree : skill_trees) {
            String shortSkillTree = skill_tree.replaceAll("[^" + skill + "]", "");
            if (shortSkillTree.equals(skill) || skill.startsWith(shortSkillTree)) {
                availableSkillTreeCount++;
            }
        }

        return availableSkillTreeCount;
    }

    public static void main(String[] args) {
        Programmers49993_2 programmers499932 = new Programmers49993_2();
        int solution = programmers499932.solution(
                "CBD",
                new String[]{"BACDE", "CBADF", "AECB", "BDA"}
        );
        System.out.println(solution);
    }
}
