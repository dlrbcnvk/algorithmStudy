package programmers.lv4;

import java.util.HashMap;
import java.util.Map;

/**
 * 호텔 방 배정
 * 유니온 파인드
 * 서로소 집합의 최댓값을 루트 노드에 저장하고 있어야 함. 최댓값을 바로 찾기 위함. 최댓값을 찾기 위해 일일이 순회하면 시간초과
 */
public class Programmers64063 {

    private static class Node {
        private long depth = 1;
        private Node parent = null;

        private long max;

        public long max() {
            return root().max;
        }

        public boolean isConnected(Node node) {
            return root() == node.root();
        }

        public void merge(Node node) {
            if (isConnected(node)) return;

            Node root1 = root();
            Node root2 = node.root();

            if (root1.depth > root2.depth) {
                root2.parent = root1;
            } else if (root1.depth < root2.depth) {
                root1.parent = root2;
            } else {
                root2.parent = root1;
                root1.depth += 1;
            }

            root1.max = root2.max = Math.max(root1.max, root2.max);
        }

        private Node root() {
            if (parent == null) return this;
            return parent.root();
        }
    }

    public long[] solution(long k, long[] room_number) {

        Map<Long, Node> nodeMap = new HashMap<>();
        long[] allowedRoomArr = new long[room_number.length];
        int idx = 0;
        for (long room : room_number) {
            long allowedRoom;
            Node node = new Node();

            if (nodeMap.containsKey(room)) {
                // 이미 배정되어 있음. 원하는 방보다 번호가 크면서 비어있는 방 중 가장 번호가 작은 방을 배정
                allowedRoom = updateMinimumEmpty(room, nodeMap);
            } else {
                // 방이 비어 있음
                allowedRoom = room;
            }
            node.max = allowedRoom;
            nodeMap.put(allowedRoom, node);

            // 양쪽에 합칠 수 있는지 확인
            if (nodeMap.containsKey(allowedRoom - 1)) {
                nodeMap.get(allowedRoom - 1).merge(node);
            }
            if (nodeMap.containsKey(allowedRoom + 1)) {
                nodeMap.get(allowedRoom + 1).merge(node);
            }

            allowedRoomArr[idx] = allowedRoom;
            idx++;
        }

        return allowedRoomArr;
    }

    public long updateMinimumEmpty(long room, Map<Long, Node> nodeMap) {
        return nodeMap.get(room).max() + 1;
    }

    public static void main(String[] args) {
        Programmers64063 programmers64063 = new Programmers64063();
        long[] solution = programmers64063.solution(
                10, new long[]{1, 3, 4, 1, 3, 1}
        );

        for (long num : solution) {
            System.out.print(num + " ");
        }
    }
}
