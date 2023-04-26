package programmers.lv3;

import java.util.ArrayList;

/**
 * 양과 늑대
 * 미해결
 */
public class Programmers92343 {

    private static enum Animal {
        SHEEP, WOLF
    }

    private static class Node {
        Animal animal;
        Node parent = null;
        ArrayList<Node> children = new ArrayList<>();

        public Node(Animal animal) {
            this.animal = animal;
        }
    }

    private static class State {
        int sheepCount;
        int wolfCount;

        public State(int sheepCount, int wolfCount) {
            this.sheepCount = sheepCount;
            this.wolfCount = wolfCount;
        }
    }

    public int solution(int[] info, int[][] edges) {

        Node[] nodes = new Node[info.length];
        for (int i = 0; i < nodes.length; i++) {
            if (info[i] == 0) {
                nodes[i] = new Node(Animal.SHEEP);
            } else {
                nodes[i] = new Node(Animal.WOLF);
            }
        }
        for (int[] edge : edges) {
            nodes[edge[0]].children.add(nodes[edge[1]]);
            nodes[edge[1]].parent = nodes[edge[0]];
        }

        State start = new State(0, 0);


        int answer = 0;
        return answer;
    }

    public State getMaxSheep(Node node, State state) {
        if (node.animal == Animal.WOLF) {
            state.wolfCount++;
            for (Node child : node.children) {
                if (child.animal == Animal.SHEEP) {
                    getMaxSheep(child, state);
                }
            }
        } else {
            state.sheepCount++;
        }

        return null;
    }

    public static void main(String[] args) {
        Programmers92343 programmers92343 = new Programmers92343();
//        int solution = programmers92343.solution();
//        System.out.println(solution);
    }
}
