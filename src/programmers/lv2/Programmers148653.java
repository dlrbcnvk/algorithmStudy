package programmers.lv2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 마법의 엘리베이터
 * https://school.programmers.co.kr/learn/courses/30/lessons/148653
 * bfs
 */
public class Programmers148653 {

    class Node {
        int level;
        int stoneSum;

        public Node(int level, int stoneSum) {
            this.level = level;
            this.stoneSum = stoneSum;
        }
    }

    public int solution(int storey) {

        Queue<Node> queue = new LinkedList<>();
        Node node = new Node(storey, 0);
        queue.add(node);

        // 해당 층에 위치하는 데에 있어 최소한의 돌이 드는 경우만 고려하고 싶음
        Map<Integer, Integer> levelMinStoneMap = new HashMap<>();

        int minStone = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int level = poll.level;
            int stoneSum = poll.stoneSum;

            if (level == 0) {
                minStone = Math.min(minStone, stoneSum);
                continue;
            }

            int lastDigit = level % 10;
            // 뒤쪽의 연속된 0들은 필요없으니 최대한 없앤다.
            while (lastDigit == 0) {
                level = level / 10;
                lastDigit = level % 10;
            }

            // 1 ~ 9
            if (level <= 5) {
                minStone = Math.min(minStone, stoneSum + level);
                continue;
            } else if (level <= 9) {
                minStone = Math.min(minStone, stoneSum + 11 - level);
                continue;
            }


            int up = 10 - lastDigit;
            int uplevel = level + up;
            int upStone = stoneSum + up;
            if (levelMinStoneMap.containsKey(uplevel)) {
                Integer stoneFromMap = levelMinStoneMap.get(uplevel);
                if (stoneFromMap > upStone) {
                    levelMinStoneMap.put(uplevel, upStone);
                    queue.add(new Node(uplevel, upStone));
                }
            } else {
                queue.add(new Node(uplevel, upStone));
            }

            int down = lastDigit;
            int downlevel = level - down;
            int downStone = stoneSum + down;
            if (levelMinStoneMap.containsKey(downlevel)) {
                Integer stoneFromMap = levelMinStoneMap.get(downlevel);
                if (stoneFromMap > downStone) {
                    levelMinStoneMap.put(downlevel, downStone);
                    queue.add(new Node(downlevel, downStone));
                }
            } else {
                queue.add(new Node(downlevel, downStone));
            }
            queue.add(new Node(downlevel, stoneSum + down));
        }


        return minStone;
    }

    public static void main(String[] args) {
        Programmers148653 programmers148653 = new Programmers148653();
        int solution = programmers148653.solution(2554);
        System.out.println(solution);
    }
}
