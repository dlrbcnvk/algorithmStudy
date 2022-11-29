package programmers.lv3;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 디스크 컨르롤러
 * 미해결 ㅠㅠ
 */
public class Programmers42627 {

    class JobRequest implements Comparable<JobRequest> {
        int requested;
        int workingTime;

        public JobRequest(int requested, int workingTime) {
            this.requested = requested;
            this.workingTime = workingTime;
        }

        @Override
        public int compareTo(JobRequest jobRequest) {
            if (this.requested < jobRequest.requested) {
                return -1;
            } else if (this.requested > jobRequest.requested) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    class JobWorking implements Comparable<JobWorking> {

        int requested;
        int workingTime;

        public JobWorking(int requested, int workingTime) {
            this.requested = requested;
            this.workingTime = workingTime;
        }

        @Override
        public int compareTo(JobWorking jobWorking) {
            if (this.workingTime < jobWorking.workingTime) {
                return -1;
            } else if (this.workingTime > jobWorking.workingTime) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<JobWorking> pq = new PriorityQueue<>();
        JobRequest[] jobRequestArray = new JobRequest[jobs.length];

        for (int i = 0; i < jobs.length; i++) {
            int[] job = jobs[i];
            jobRequestArray[i] = new JobRequest(job[0], job[1]);
        }
        Arrays.sort(jobRequestArray);

        int idx = 0;
        JobRequest jobRequest = jobRequestArray[0];
        boolean[] marked = new boolean[jobRequestArray.length];
        pq.add(new JobWorking(jobRequest.requested, jobRequest.workingTime));
        marked[0] = true;
        int time = jobRequest.requested;
        while (idx < jobRequestArray.length) {

            jobRequest = jobRequestArray[idx];

            idx++;
        }

        return 1;
    }

    public static void main(String[] args) {
        Programmers42627 programmers42627 = new Programmers42627();
        int solution = programmers42627.solution(
                new int[][]{{0, 3}, {1, 9}, {2, 6}}
        );
        System.out.println(solution);
    }
}
