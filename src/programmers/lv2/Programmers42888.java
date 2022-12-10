package programmers.lv2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 오픈채팅방
 */
public class Programmers42888 {

    enum Status {
        IN, OUT
    }

    static class Record {
        String id;
        Status status;

        public Record(String id, Status status) {
            this.id = id;
            this.status = status;
        }
    }

    public String[] solution(String[] record) {

        HashMap<String, String> userMap = new HashMap<>();
        Queue<Record> queue = new LinkedList<>();

        for (String command : record) {
            String[] split = command.split(" ");
            String behavior = split[0];
            String id = split[1];
            if (behavior.charAt(0) == 'E') {
                // Enter
                String nickname = split[2];
                userMap.put(id, nickname);
                queue.add(new Record(id, Status.IN));
            } else if (behavior.charAt(0) == 'L') {
                // Leave
                queue.add(new Record(id, Status.OUT));
            } else {
                // Change
                String nickname = split[2];
                userMap.put(id, nickname);
            }
        }

        String[] result = new String[queue.size()];
        int idx = 0;
        StringBuilder sb;
        while (!queue.isEmpty()) {
            sb = new StringBuilder();
            Record poll = queue.poll();
            String nickname = userMap.get(poll.id);
            Status status = poll.status;
            if (status == Status.IN) {
                sb.append(nickname);
                sb.append("님이 들어왔습니다.");
            } else {
                sb.append(nickname);
                sb.append("님이 나갔습니다.");
            }
            result[idx] = sb.toString();
            idx++;
        }

        return result;
    }

    public static void main(String[] args) {
        Programmers42888 programmers42888 = new Programmers42888();
        String[] solution = programmers42888.solution(
                new String[]{
                        "Enter uid1234 Muzi",
                        "Enter uid4567 Prodo",
                        "Leave uid1234",
                        "Enter uid1234 Prodo",
                        "Change uid4567 Ryan"
                }
        );

        for (String str : solution) {
            System.out.print(str + "");
            System.out.println();
        }
    }
}
