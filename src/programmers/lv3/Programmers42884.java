package programmers.lv3;

import java.util.Arrays;

/**
 * 단속카메라
 * 그리디
 * 나름의 정렬 기준으로 정렬
 * 뒤에서부터 기준 조건 재 가며 카운트 늘림
 */
public class Programmers42884 {

    static class Node implements Comparable<Node> {
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node node) {
            if (this.start < node.start) {
                return -1;
            } else if (this.start > node.start) {
                return +1;
            } else {
                if (this.end < node.end) {
                    return 1;
                } else if (this.end > node.end) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    public int solution(int[][] routes) {
        int count = 0;

        int n = routes.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(routes[i][0], routes[i][1]);
        }
        Arrays.sort(nodes);

        int idx = n - 1;
        while (idx >= 0) {
            int lastStartPoint = nodes[idx].start;
            int finalIdx = idx;
            while (finalIdx >= 0 && nodes[finalIdx].end >= lastStartPoint) {
                finalIdx--;
            }
            idx = finalIdx;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        Programmers42884 programmers42884 = new Programmers42884();
        int solution = programmers42884.solution(
            new int[][]{{-20,-15},{-14,-5},{-18,-13},{-5,-3}}
        );
        System.out.println(solution);
    }
}
