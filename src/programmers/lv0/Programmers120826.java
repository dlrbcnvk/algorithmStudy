package programmers.lv0;

public class Programmers120826 {
    public String solution(String my_string, String letter) {
        String answer = my_string.replaceAll(letter, "");
        return answer;
    }

    public static void main(String[] args) {
        Programmers120826 programmers120826 = new Programmers120826();
        String solution = programmers120826.solution("BCBdbe", "B");
        System.out.println(solution);
    }
}
