package programmers.lv2;

/**
 * 파일명 정렬
 * 미해결
 */
public class Programmers17686 {

    class File {
        int id;
        String findName;
        String head;
        Integer number;
        String tail;

        public File(int id, String findName) {
            this.id = id;
            this.findName = findName;


        }
    }

    public String[] solution(String[] files) {
        String[] answer = {};
        return answer;
    }

    public static void main(String[] args) {
        Programmers17686 programmers17686 = new Programmers17686();
        String[] solution = programmers17686.solution(
            new String[]{}
        );
        System.out.println(solution);
    }
}
