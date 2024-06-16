package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 뱀과 사다리 게임
 */
public class BOJ16928 {

    private static GameTurn[] board = new GameTurn[101];

    private static class GameTurn {
        int loc;
        int turn;

        public GameTurn(int loc, int turn) {
            this.loc = loc;
            this.turn = turn;
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < board.length; i++) {
            board[i] = new GameTurn(i, Integer.MAX_VALUE);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        Map<Integer, Integer> ladderSnakeMap = new HashMap<>();
        for (int i = 0; i < n + m; i++) {
            split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            ladderSnakeMap.put(from, to);
        }

        Queue<GameTurn> queue = new LinkedList<>();
        board[1].turn = 0;
        queue.add(board[1]);
        while (!queue.isEmpty()) {
            GameTurn curTurn = queue.poll();
            GameTurn nextTurn = null;
            if (ladderSnakeMap.containsKey(curTurn.loc)) {
                // 사다리나 뱀을 만났다!
                int nextLoc = ladderSnakeMap.get(curTurn.loc);
                if (board[nextLoc].turn > board[curTurn.loc].turn) {
                    // 사다리나 뱀을 타고 탐색을 이어가는게 낫다.
                    nextTurn = board[nextLoc];
                    nextTurn.turn = board[curTurn.loc].turn;
                    queue.add(nextTurn);
                }
            } else {
                for (int i = 1; i <= 6; i++) {
                    // 주사위 굴리기
                    if (curTurn.loc + i > 100) continue;

                    nextTurn = board[curTurn.loc + i];
                    if (board[nextTurn.loc].turn > board[curTurn.loc].turn + 1) {
                        nextTurn = board[nextTurn.loc];
                        nextTurn.turn = board[curTurn.loc].turn + 1;
                        queue.add(nextTurn);
                    }

                }
            }
        }

        System.out.println(board[100].turn);

    }
}
