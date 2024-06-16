package programmers.lv2;

/**
 * 거리두기 확인하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/81302
 */
public class Programmers81302_2 {

    // 상하좌우(1칸), 상하좌우(2칸), 좌상, 우상, 좌하, 우하
    private int[] dy = {-1, 1, 0, 0, -2, 2, 0, 0, -1, -1, 1, 1};
    private int[] dx = {0, 0, -1, 1, 0, 0, -2, 2, -1, 1, -1, 1};

    public int[] solution(String[][] places) {

        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            String[] p = places[i];
            String[][] place = new String[p.length][p[0].length()];
            for (int j = 0; j < p.length; j++) {
                place[j] = p[j].split("");
            }
            int result = checkDistancing(place);
            answer[i] = result;
        }

        return answer;
    }

    private int checkDistancing(String[][] place) {
        boolean result = true;
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length; j++) {
                if ("P".equals(place[i][j])) {
                    result = checkManhatten(place, i, j);
                }
                if (!result) break;
            }
            if (!result) break;
        }

        return result ? 1 : 0;
    }

    private boolean checkManhatten(String[][] place, int y, int x) {
        int n = place.length;
        int m = place[0].length;
        for (int i = 0; i < dy.length; i++) {
            int toY = y + dy[i];
            int toX = x + dx[i];

            if (toY < 0 || toX < 0 || toY >= n || toX >= m) continue;

            String target = place[toY][toX];
            if (i < 4) {
                if ("P".equals(target)) {
                    return false;
                }
            }
            else if (i < 8) {
                if ("P".equals(target) && !"X".equals(place[y + dy[i - 4]][x + dx[i - 4]])) {
                    return false;
                }
            } else {
                if ("P".equals(target)
                        && (!"X".equals(place[y + (toY - y)][x]) || !"X".equals(place[y][x + (toX - x)]))) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Programmers81302_2 programmers813022 = new Programmers81302_2();
        int[] solution = programmers813022.solution(
                new String[][]{
                        {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
                }
        );

        for (int value : solution) {
            System.out.print(value + " ");
        }
    }
}
