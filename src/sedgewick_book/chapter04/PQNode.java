package sedgewick_book.chapter04;

import java.util.Objects;

public class PQNode implements Comparable<PQNode> {
    private int key;
    private int value;

    public PQNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(PQNode node) {
        if (value < node.getValue()) {
            return -1;
        } else if (value > node.getValue()) {
            return +1;
        } else {
            return 0;
        }
    }
}
