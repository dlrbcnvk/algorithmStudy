package sedgewick_book.chapter04;

/**
 * 가중 방향 간선 그래프 타입
 */
public class DirectedEdge {
    private final int v; // 간선의 꼬리 정점
    private final int w; // 간선의 머리 정점
    private final double weight; // 간선 가중치

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
