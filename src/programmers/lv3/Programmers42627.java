package programmers.lv3;

import java.util.*;

/**
 * 디스크 컨트롤러
 * 들어오는 것마다 짧은 걸 최우선순위로 두면 될까? ㅇㅇ
 * 증명가능. (a,x), (b,y) 가 들어온 상태라고 가정. 진행중인 작업이 시각 c에 끝난 경우
 * 둘 중 누구를 먼저 해야 enterTime ~ endTime 의 평균이 최소가 될까? 생각해보면 됨
 */
public class Programmers42627 {

    public static class Job implements Comparable<Job>{
        int enterTime;
        int runningTime;
        int endTime;

        @Override
        public int compareTo(Job job) {
            if (this.runningTime < job.runningTime) {
                return -1;
            } else if (this.runningTime > job.runningTime) {
                return 1;
            } else {
                return 0;
            }
        }

        public Job(int enterTime, int runningTime) {
            this.enterTime = enterTime;
            this.runningTime = runningTime;
        }
    }

    public int solution(int[][] jobs) {

        HashMap<Integer, ArrayList<Integer>> readyMap = new HashMap<>();

        for (int[] job : jobs) {
            if (!readyMap.containsKey(job[0])) {
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(job[1]);
                readyMap.put(job[0], arr);
            } else {
                readyMap.get(job[0]).add(job[1]);
            }
        }

        Set<Integer> keySet = readyMap.keySet();
        int[] keys = new int[keySet.size()];
        int idx = 0;
        for (Integer key : keySet) {
            keys[idx] = key;
            idx++;
        }
        Arrays.sort(keys);

        int time = 0;
        int endTime = 0;
        int enterTime;
        boolean isOngoing = false;
        PriorityQueue<Job> pq = new PriorityQueue<>();
        ArrayList<Job> jobArrayList = new ArrayList<>();
        boolean started = false;
        idx = 0;
        while (true) {

            if (idx == keys.length && pq.isEmpty() && !isOngoing && started) {
                break;
            }

            // 들어올 시간이면 먼저 들여오기
            if (idx < keys.length && time == keys[idx]) {
                enterTime = keys[idx];
                ArrayList<Integer> runningTimeList = readyMap.get(enterTime);
                for (Integer runningTime : runningTimeList) {
                    Job job = new Job(enterTime, runningTime);
                    pq.add(job);
                    jobArrayList.add(job);
                }
                idx++;
                started = true;
            }

            // 디스크가 비어있으면 pq에서 하나 빼서 넣고
            if (!isOngoing) {
                if (!pq.isEmpty()) {
                    Job poll = pq.poll();
                    poll.endTime = time + poll.runningTime;
                    isOngoing = true;
                    endTime = poll.endTime;
                }
            } else {
                // 진행중이었는데, 작업이 끝날 시간이 됨. 새로운 작업이 있으면 넣기
                if (time == endTime) {
                    isOngoing = false;
                    if (!pq.isEmpty()) {
                        Job poll = pq.poll();
                        poll.endTime = time + poll.runningTime;
                        isOngoing = true;
                        endTime = poll.endTime;
                    }
                }
            }

            time++;
        }

        double sum = 0;
        for (Job job : jobArrayList) {
            sum += (job.endTime - job.enterTime);
        }


        return (int)(sum / jobArrayList.size());
    }

    public static void main(String[] args) {
        Programmers42627 programmers42627 = new Programmers42627();
        int solution = programmers42627.solution(
                new int[][]{{0, 3}, {1, 9}, {2, 6}}
        );
        System.out.println(solution);
    }
}
