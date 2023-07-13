package programmers.lv1;

/**
 * 바탕화면 정리
 * https://school.programmers.co.kr/learn/courses/30/lessons/161990
 */
public class Programmers161990 {

    private final static String SHARP = "#";
    public int[] solution(String[] wallpaper) {

        // 세로축 y, 가로축 x
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        int x = 0;
        for (String wall : wallpaper) {
            String[] split = wall.split("");
            for (int y = 0; y < split.length; y++) {
                if (split[y].equals(SHARP)) {
                    // # 을 받을 때는 해당 격자점(x,y) 에 파일이 있다고 생각하고
                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);
                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);
                }
            }
            x++;
        }

        // 마지막에 끝지점 x,y 하나씩 늘려주면 됨
        maxX++;
        maxY++;

        return new int[]{minX, minY, maxX, maxY};
    }
}
