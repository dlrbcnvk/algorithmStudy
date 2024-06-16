package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 카드2
 * https://www.acmicpc.net/problem/2164
 */
public class BOJ2164 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= n; i++) deque.addLast(i);
        while (deque.size() > 1) {
            // 맨위에서 (맨앞에서) 하나 꺼내서 버리고
            deque.removeFirst();
            if (deque.size() == 1) {
                System.out.println(deque.poll());
                return;
            }
            // 그다음에, 맨 위에서 (맨 앞에서) 하나 꺼내서 맨 뒤로 보낸다.
            int poll = deque.poll();
            deque.addLast(poll);
        }
        System.out.println(deque.poll());
    }
}
