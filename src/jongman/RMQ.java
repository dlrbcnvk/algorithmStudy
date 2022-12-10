package jongman;

import java.util.ArrayList;

/**
 * 구간 트리
 * 특정 구간의 최소치를 찾는 문제 - 구간 최소 쿼리
 */
public class RMQ {
    // 배열의 길이
    int n;

    // 각 구간의 최소치
    int[] rangeMin;

    public RMQ(int[] array) {
        this.n = array.length;
        rangeMin = new int[n * 4];
        init(array, 0, n - 1, 1);
    }

    // node 노드가 array[left..right] 배열을 표현할 때
    // node를 루트로 하는 서브트리를 초기화하고, 이 구간의 최소치를 반환한다
    private int init(int[] array, int left, int right, int node) {
        if (left == right) {
            rangeMin[node] = array[left];
            return rangeMin[node];
        }
        int mid = (left + right) / 2;
        int leftMin = init(array, left, mid, node * 2);
        int rightMin = init(array, mid + 1, right, node * 2 + 1);
        rangeMin[node] = Math.min(leftMin, rightMin);
        return rangeMin[node];
    }

}
