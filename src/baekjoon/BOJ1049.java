package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 기타줄
 * https://www.acmicpc.net/problem/1049
 * 미해결
 */
public class BOJ1049 {

    static class Guitar {
        int pack;
        int unit;

        public Guitar(int pack, int unit) {
            this.pack = pack;
            this.unit = unit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        Guitar[] guitars = new Guitar[M];
        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            int pack = Integer.parseInt(split[0]);
            int unit = Integer.parseInt(split[1]);
            guitars[i] = new Guitar(pack, unit);
        }
    }
}
