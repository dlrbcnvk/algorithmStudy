package programmers.lv3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 디스크 컨트롤러
 */
public class Programmers42627_2 {

    class Job {
        int requestTime;
        int runningTime;
        int startTime;
        int endTime;

        public Job(int requestTime, int runningTime) {
            this.requestTime = requestTime;
            this.runningTime = runningTime;
        }

        public int getTotalTime() {
            return (this.endTime - this.requestTime);
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
            this.endTime = this.startTime + this.runningTime;
        }

        public int getRequestTime() {
            return requestTime;
        }

        public int getRunningTime() {
            return runningTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }
    }
    public int solution(int[][] jobs) {

        Job[] jobArr = new Job[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            jobArr[i] = new Job(jobs[i][0], jobs[i][1]);
        }
        Arrays.sort(jobArr, Comparator.comparingInt(Job::getRequestTime));

        int time = 0;
        int jobIdx = 0;
        PriorityQueue<Job> waitingPQ = new PriorityQueue<>(Comparator.comparing(Job::getRunningTime));
        Job runningJob = null;
        while (true) {

            if (jobIdx == jobArr.length && waitingPQ.isEmpty()) {
                break;
            }

            if (runningJob != null && runningJob.getEndTime() == time) {
                runningJob = null;
            }

            while (jobIdx < jobArr.length) {
                if (jobArr[jobIdx].getRequestTime() == time) {
                    waitingPQ.add(jobArr[jobIdx]);
                    jobIdx++;
                } else {
                    break;
                }
            }

            if (runningJob == null && !waitingPQ.isEmpty()) {
                Job poll = waitingPQ.poll();
                poll.setStartTime(time);
                runningJob = poll;
            }

            time++;
        }

        return Arrays.stream(jobArr).mapToInt(Job::getTotalTime).sum() / jobArr.length;
    }

    public static void main(String[] args) {
        Programmers42627_2 programmers426272 = new Programmers42627_2();
        int solution = programmers426272.solution(new int[][]{
                {0,3},
                {1,9},
                {2,6}
        });

        System.out.println(solution);
    }
}
