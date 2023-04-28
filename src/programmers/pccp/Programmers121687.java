package programmers.pccp;

/**
 * 실습용 로봇
 * https://school.programmers.co.kr/learn/courses/15009/lessons/121687
 */
public class Programmers121687 {

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public int[] solution(String command) {
        int x = 0;
        int y = 0;

        Direction direction = Direction.UP;
        char[] chars = command.toCharArray();

        for (char c : chars) {
            if (c == 'R') {
                if (direction == Direction.UP) {
                    direction = Direction.RIGHT;
                } else if (direction == Direction.RIGHT) {
                    direction = Direction.DOWN;
                } else if (direction == Direction.DOWN) {
                    direction = Direction.LEFT;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.UP;
                }
            } else if (c == 'L') {
                if (direction == Direction.UP) {
                    direction = Direction.LEFT;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.DOWN;
                } else if (direction == Direction.DOWN) {
                    direction = Direction.RIGHT;
                } else if (direction == Direction.RIGHT) {
                    direction = Direction.UP;
                }
            } else if (c == 'G') {
                if (direction == Direction.UP) {
                    y++;
                } else if (direction == Direction.RIGHT) {
                    x++;
                } else if (direction == Direction.DOWN) {
                    y--;
                } else if (direction == Direction.LEFT) {
                    x--;
                }
            } else if (c == 'B') {
                if (direction == Direction.UP) {
                    y--;
                } else if (direction == Direction.RIGHT) {
                    x--;
                } else if (direction == Direction.DOWN) {
                    y++;
                } else if (direction == Direction.LEFT) {
                    x++;
                }
            }
        }



        return new int[]{x, y};
    }
}
