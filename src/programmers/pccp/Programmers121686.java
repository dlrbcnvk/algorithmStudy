package programmers.pccp;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 운영체제
 * 2023.04.26 AC
 * https://school.programmers.co.kr/learn/courses/15008/lessons/121686
 */
public class Programmers121686 {

    private static class Program implements Comparable<Program> {
        int id;
        int priority;
        int callTime;
        long enterTime;
        int runningTime;
        long endTime;

        public Program(int id, int priority, int callTime, int runningTime) {
            this.id = id;
            this.priority = priority;
            this.callTime = callTime;
            this.runningTime = runningTime;
        }

        @Override
        public int compareTo(Program p) {
            if (this.priority < p.priority) {
                return -1;
            } else if (this.priority > p.priority) {
                return 1;
            } else {
                if (this.callTime < p.callTime) {
                    return -1;
                } else if (this.callTime > p.callTime) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public long[] solution(int[][] program) {

        Program[] programs = new Program[program.length];
        for (int i = 0; i < program.length; i++) {
            programs[i] = new Program(i, program[i][0], program[i][1], program[i][2]);
        }
        // callTime 순으로 오름차순 정렬
        Arrays.sort(programs, (p1, p2) -> p1.callTime - p2.callTime);

        PriorityQueue<Program> pq = new PriorityQueue<>();
        long time = 0;
        int idx = 0;

        Program runningProgram = null;
        while (true) {

            // 현재 time 에 들어올 program 들 다 들어오도록
            while (idx < programs.length && programs[idx].callTime == time) {
                pq.add(programs[idx]);
                idx++;
            }

            // 뺄 시간이면 실행중인 프로그램을 먼저 빼고
            if (runningProgram != null && runningProgram.endTime == time) {
                runningProgram = null;
            }

            // 실행할 거 있으면 실행하기
            // 실행 시작할 때 endTime 미리 설정
            if (!pq.isEmpty() && runningProgram == null) {
                runningProgram = pq.poll();
                runningProgram.enterTime = time;
                runningProgram.endTime = runningProgram.enterTime + runningProgram.runningTime;
//                System.out.println("time "+ time + " - program " +  runningProgram.id + " is started");
            }

            // 종료 조건
            if (runningProgram == null && idx == programs.length && pq.isEmpty()) break;

            time++;
        }

        long[] answer = new long[11];
        Arrays.fill(answer, 0);
        answer[0] = time;
        for (Program p : programs) {
            answer[p.priority] += p.enterTime - p.callTime;
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers121686 programmers121686 = new Programmers121686();
        long[] solution = programmers121686.solution(
                new int[][]{{2,0,10},{1,5,5},{3,5,3},{3,12,2}}
        );

        for (long value : solution) {
            System.out.print(value + " ");
        }
    }
}
