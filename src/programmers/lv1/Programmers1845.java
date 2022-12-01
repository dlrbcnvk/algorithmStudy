package programmers.lv1;

import java.util.*;

/**
 * 폰켓몬
 */
public class Programmers1845 {

    class Node implements Comparable<Node> {
        int id;
        int count;

        public Node(int id, int count) {
            this.id = id;
            this.count = count;
        }

        @Override
        public int compareTo(Node node) {
            if (this.count < node.count) {
                return -1;
            } else if (this.count > node.count) {
                return 1;
            } else {
                if (this.id < node.id) {
                    return -1;
                } else if (this.id > node.id) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public int solution(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                Integer integer = map.get(num);
                map.put(num, integer + 1);
            }
        }

        Set<Integer> set = map.keySet();
        Queue<Node> queue = new LinkedList<>();
        int idx = 0;
        for (Integer key : set) {
            queue.add(new Node(key, map.get(key)));
        }

        int count = 0;
        HashSet<Integer> resultSet = new HashSet<>();
        while (count < nums.length / 2) {
            Node poll = queue.poll();
            resultSet.add(poll.id);
            if (poll.count > 1) {
                queue.add(new Node(poll.id, poll.count - 1));
            }
            count++;
        }
        return resultSet.size();
    }

    public static void main(String[] args) {
        Programmers1845 programmers1845 = new Programmers1845();
        int solution = programmers1845.solution(
                new int[]{3,3,3,2,2,4}
        );
        System.out.println(solution);
    }
}
