package programmers.lv1;

import java.util.ArrayList;

/**
 * [1차] 다트 게임
 */
public class Programmers17682 {

    class Game {
        int point;
        String bonus;
        String option;

        public Game(int point, String bonus, String option) {
            this.point = point;
            this.bonus = bonus;
            this.option = option;
        }
    }

    public int solution(String dartResult) {

        char[] chars = dartResult.toCharArray();
        char[] options = new char[]{' ', ' ', ' '};
        ArrayList<Integer> points = new ArrayList<>();
        ArrayList<String> bonuses = new ArrayList<>();


        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '#' || chars[i] == '*') {
                options[i / 3] = chars[i];
            } else if (Character.isDigit(chars[i])) {
                if (i > 0 && chars[i] == '0' && chars[i - 1] == '1') {
                    points.remove(points.size() - 1);
                    points.add(10);
                } else {
                    points.add(Integer.parseInt(chars[i] + ""));
                }
            } else {
                bonuses.add(chars[i] + "");
            }
        }

        Game[] games = new Game[3];
        games[0] = new Game(points.get(0), bonuses.get(0), options[0] + "");
        games[1] = new Game(points.get(1), bonuses.get(1), options[1] + "");
        games[2] = new Game(points.get(2), bonuses.get(2), options[2] + "");
        for (int i = 0; i < 3; i++) {
            if (games[i].bonus.equals("S")) {
                games[i].point = (int)Math.pow(games[i].point, 1);
            } else if (games[i].bonus.equals("D")) {
                games[i].point = (int)Math.pow(games[i].point, 2);
            } else if (games[i].bonus.equals("T")) {
                games[i].point = (int)Math.pow(games[i].point, 3);
            }

            if (games[i].option.equals(" ")) {

            } else if (games[i].option.equals("#")) {
                games[i].point = -games[i].point;
            } else if (games[i].option.equals("*")) {
                if (i == 0) {
                    games[i].point *= 2;
                } else {
                    games[i].point *= 2;
                    games[i-1].point *= 2;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += games[i].point;
        }
        return sum;
    }

    public static void main(String[] args) {
        Programmers17682 programmers17682 = new Programmers17682();
        int solution = programmers17682.solution("1D2S#10S");
        System.out.println(solution);
    }
}
