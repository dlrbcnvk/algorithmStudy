package sedgewick_book.chapter06;

public class FlowEdge {
    private final int v;                // 간선 시작점
    private final int w;                // 간선 끝점
    private final double capacity;      // 용량
    private double flow;                // 흐름량

    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0;
    }

    public int from() { return v; }
    public int to() { return w; }
    public double capacity() { return capacity; }
    public double flow() { return flow; }

    public int other(int vertex) {
        if (vertex == v)
            return w;
        else if (vertex == w)
            return v;
        else
            throw new RuntimeException("Inconsistent edge");
    }

    public double residualCapacityTo(int vertex) {
        if (vertex == v) {
            return flow;
        } else if (vertex == w) {
            return capacity - flow;
        } else {
            throw new RuntimeException("Inconsistent edge");
        }
    }

    public void addResidualFlowTo(int vertex, double delta) {
        if (vertex == v) { // 역방향 추가 (순방향 감소)
            flow -= delta;
        } else if (vertex == w) { // 순방향 추가
            flow += delta;
        } else {
            throw new RuntimeException("Inconsistent edge");
        }
    }

    public String toString() {
        return String.format("%d->%d %.2f %.2f", v, w, capacity, flow);
    }
}
