package sedgewick_book.chapter01;

/**
 * N개 쌍에 대한 find() 연산은 최악의 경우 제곱 횟수의 배열 접근을 유발함
 */
public class UF {
    private int[] id; // 컴포넌트 식별자를 저장
    private int count; // 컴포넌트 개수

    public UF(int N) {
        // 컴포넌트 식별자 배열 초기화
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        // p와 q를 같은 뿌리에 속하게 하기
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }

        // 단순히 왼쪽 것이 오른쪽 것 밑에 들어간다.
        id[i] = j;
        count--;
    }

    public static void main(String[] args) {

        UF uf = new UF(10);

        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 5);
        uf.union(2, 1);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);

        System.out.println(uf.count + " components");
    }
}
