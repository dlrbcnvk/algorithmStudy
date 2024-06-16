package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * DSLR
 * 미해결
 */
public class BOJ9019 {

    private static int T;

    private static String D = "D";
    private static String S = "S";
    private static String L = "L";
    private static String R = "R";

    static class Node {
        int value;
        String commands;

        public Node(int value, String commands) {
            this.value = value;
            this.commands = commands;
        }
    }

    private static Map<Integer, String> valueCommandMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            // dfs로 할 게 아니라 queue를 사용하는 bfs로 하는 것이 가장 짧은 길이를 구하는 데 있어서 더 적합할 듯.
            // bfs로 바꿀 것.

            Node node = new Node(a, "");
            Queue<Node> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                Node poll = queue.poll();
                int value = poll.value;
                String commands = poll.commands;
                if (poll.value == b) {
                    System.out.println(poll.commands);
                    break;
                }

                int d = 2 * value > 10000 ? (2 * value) % 10000 : 2 * value;
                checkValueCommandMapAndPut(d, commands + D, D, queue);


                int s = value - 1 == 0 ? 9999 : value - 1;
                checkValueCommandMapAndPut(s, commands + S, S, queue);

                if (value < 10) continue;

                // L, R의 경우도 추가할 것
                String stringValue = String.valueOf(value);
                String part1 = stringValue.substring(0, 1);
                String part2 = String.valueOf(Integer.parseInt(stringValue.substring(1)));
                int l = Integer.parseInt(part2 + part1);
                checkValueCommandMapAndPut(l, commands + L, L, queue);

                part1 = stringValue.substring(0, stringValue.length() - 1);
                part2 = stringValue.substring(stringValue.length() - 1);
                int r = Integer.parseInt(part2 + part1);
                checkValueCommandMapAndPut(r, commands + R, R, queue);
            }


        }
    }

    private static void checkValueCommandMapAndPut(int value, String commands, String command, Queue<Node> queue) {
        if (valueCommandMap.containsKey(value)) {
            if (valueCommandMap.get(value).length() > commands.length() + 1) {
                valueCommandMap.put(value, commands + command);
                queue.add(new Node(value, commands + command));
            }
        } else {
            valueCommandMap.put(value, commands + command);
            queue.add(new Node(value, commands + command));
        }
    }
}
