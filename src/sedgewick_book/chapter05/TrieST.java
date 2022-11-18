package sedgewick_book.chapter05;

public class TrieST<Value> {

    private static int R = 256; // 기수(알파벳 크기)
    private Node root = new Node();

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) { // get start
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        // x를 뿌리로 하는 서브트라이에서 키에 연관된 노드를 리턴
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, Value val) { // put start
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        // x를 뿌리로 하는 서브트라이에 키가 존재하는 경우 그에 연관된 값을 변경
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        int cnt = 0;
        if (x.val != null) {
            cnt++;
        }
        for (char c = 0; c < R; c++) {
            cnt += size(x.next[c]);
        }
        return cnt;
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }

        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TrieST<String> trie = new TrieST<>();
        int val = 0;
        trie.put("she", "0");
        trie.put("sells", "1");
        trie.put("sea", "2");
        trie.put("shells", "3");
        trie.put("by", "4");
        trie.put("the", "5");
        trie.put("sea", "6");
        trie.put("shore", "7");

        String shells = trie.get("she");
        System.out.println(shells);

        int size = trie.size();
        System.out.println(size);
    }
}
