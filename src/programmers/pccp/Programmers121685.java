package programmers.pccp;

/**
 * 유전 법칙
 * 2023.4,26 AC
 * https://school.programmers.co.kr/learn/courses/15008/lessons/121685
 */
public class Programmers121685 {

    private enum Wandoo {
        RR, Rr, rr
    }

    public String[] solution(int[][] queries) {

        String[] answer = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] routes = query(queries[i][0], queries[i][1] - 1).split(",");

            Wandoo wandoo = Wandoo.Rr;
            for (int j = 1; j < routes.length; j++) {
                wandoo = nextWandoo(wandoo, Integer.parseInt(routes[j]));
            }
            answer[i] = String.valueOf(wandoo);
        }

        return answer;
    }

    public String query(int generation, int loc) {
        StringBuilder sb = new StringBuilder();
        while (generation > 1) {
            sb.insert(0, "," + loc % 4);
            loc = loc / 4;
            generation--;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


    public Wandoo nextWandoo(Wandoo wandoo, int nextLoc) {
        switch (wandoo) {
            case RR:
                return Wandoo.RR;
            case rr:
                return Wandoo.rr;
            case Rr:
                if (nextLoc == 0) {
                    return Wandoo.RR;
                } else if (nextLoc == 3) {
                    return Wandoo.rr;
                } else {
                    return Wandoo.Rr;
                }
            default:
                return Wandoo.RR;
        }
    }

    public static void main(String[] args) {
        Programmers121685 programmers121685 = new Programmers121685();
        String[] solution = programmers121685.solution(
                new int[][]{{3,1},{2,3},{3,9}}
        );

        for (String s : solution) {
            System.out.print(s + " ");
        }
    }
}
