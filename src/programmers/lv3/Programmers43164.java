package programmers.lv3;

import java.util.*;

/**
 * 여행경로
 * dfs
 * 방향 그래프. 정점이 String.
 */
public class Programmers43164 {

    public static class DirectedEdge implements Comparable<DirectedEdge> {
        private final String v;
        private final String w;
        private boolean used = false;

        public DirectedEdge(String v, String w) {
            this.v = v;
            this.w = w;
        }

        public String from() { return v; }
        public String to() { return w; }
        public boolean isUsed() { return used; }

        @Override
        public int compareTo(DirectedEdge edge) {
            if (this.to().compareTo(edge.to()) < 0) {
                return -1;
            } else if (this.to().compareTo(edge.to()) > 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public class DirectedEdgeGraph {
        private final int V; // 정점 개수
        private int E; // 간선 개수
        private Map<String, ArrayList<DirectedEdge>> adj; // 인접 리스트(map)
        private Stack<String> stack = new Stack<>(); // 완성된 경로 추적
        private Stack<String> answer; // 끝에서부터 꺼내는 스택 순서를 뒤집어서 앞에서부터 꺼내도록. 시작점부터 끝까지 조회할 수 있도록

        public DirectedEdgeGraph(int V) {
            this.V = V;
            this.E = 0;
            adj = new HashMap();
            answer = new Stack<>();
        }

        public void addEdge(DirectedEdge edge) {
            if (adj.containsKey(edge.from())) {
                ArrayList<DirectedEdge> edges = adj.get(edge.from());
                edges.add(edge);
            } else {
                ArrayList<DirectedEdge> edges = new ArrayList<>();
                edges.add(edge);
                adj.put(edge.from(), edges);
            }
            this.E++;
        }

        public void dfs(String vertex) {
            // 간선 모두 탐색 - 종료
            if (stack.size() == E + 1) {

                for (int i = E; i >= 0; i--) {
                    String pop = stack.pop();
                    answer.push(pop);
                }
                return;
            }

            if (adj.containsKey(vertex)) {
                ArrayList<DirectedEdge> edges = adj.get(vertex);
                for (DirectedEdge edge : edges) {
                    if (!edge.isUsed()) {
                        edge.used = true;
                        stack.push(edge.to());
                        dfs(edge.to());
                        edge.used = false;
                        if (!stack.isEmpty()) {
                            stack.pop();
                        }
                    }
                }
            }

        }
    }

    public String[] solution(String[][] tickets) {

        // 정점 개수 파악하기
        Set<String> stringSet = new HashSet<>();
        for (String[] ticket : tickets) {
            stringSet.add(ticket[0]);
            stringSet.add(ticket[1]);
        }
        int V = stringSet.size();

        DirectedEdgeGraph graph = new DirectedEdgeGraph(V);
        for (String[] ticket : tickets) {
            graph.addEdge(new DirectedEdge(ticket[0], ticket[1]));
        }
        // 모든 인접 리스트를 목적지 알파벳 순으로 정렬
        Collection<ArrayList<DirectedEdge>> values = graph.adj.values();
        for (ArrayList<DirectedEdge> list : values) {
            Collections.sort(list);
        }

        graph.stack.push("ICN");
        graph.dfs("ICN");


        ArrayList<String> list = new ArrayList<>();
        Stack<String> stack = graph.answer;
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            list.add(pop);
        }

        int idx = 0;
        String[] strings = new String[list.size()];
        for (String str : list) {
            strings[idx] = str;
            idx++;
        }

        return strings;
    }

    public static void main(String[] args) {
        Programmers43164 programmers43164 = new Programmers43164();
        String[] solution = programmers43164.solution(
                new String[][]{{"ICN", "A"}, {"A", "B"}, {"A", "C"}, {"C", "A"}, {"B", "D"}}
        );

        for (String city : solution) {
            System.out.print(city + " ");
        }
    }
}
