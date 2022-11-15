package sedgewick_book.chapter01;

/**
 * 개선된 union-find
 * union 시 노드 개수 작은 트리의 뿌리가 노드 개수 큰 트리에 링크되도록 함
 * n^2 개의 노드를 가진 트리는 높이 n을 가진다
 * 일반적으로 로그 시간 성능을 가짐
 */
public class WeightedQuickUnionUF {
    private int[] id; // 부모 링크
    private int[] sz; // 각 뿌리 노드가 속한 트리의 노드 개수. 컴포넌트 크기
    private int count; // 컴포넌트들의 개수

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int getCount() {
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
        int i = find(p);
        int j = find(q);
        if (i == j)
            return;

        // i, j는 각각 뿌리
        // 작은 트리의 뿌리가 큰 트리에 링크되도록 함
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i]; // 상위 트리가 된 쪽의 뿌리 인덱스에서 사이즈 갱신
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    public static void main(String[] args) {
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);

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
