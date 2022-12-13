package programmers.lv2;

import java.util.ArrayList;

/**
 * 줄 서는 방법
 * 효율성 시간 초과 -> java stream 사용해서 시간초과 난 거였음
 * ArrayList를 돌면서 인덱스 조정하면서 직접 array로 바꾸니까 통과
 */
public class Programmers12936 {

    int[] numArr;
    boolean[] marked;
    ArrayList<Integer> resultList;
    long[] factorial;

    public int getNumberFromCountIdx(int countIdx) {
        int count = 0;
        int idx = 0;
        while (countIdx != count) {
            idx++;
            if (!marked[idx]) {
                count++;
            }
            if (!marked[idx] && countIdx == count) {
                break;
            }
        }
        return idx;
    }

    public void go(int n, long k) {

        if (n == 2) {
            int first = 1;
            boolean firstFound = false;
            int second = 1;
            for (int i = 1; i < numArr.length; i++) {
                if (!marked[i] && !firstFound) {
                    first = i;
                    firstFound = true;
                } else if (!marked[i]) {
                    second = i;
                    break;
                }
            }
            if (k == 1) {
                resultList.add(first);
                resultList.add(second);
            } else {
                resultList.add(second);
                resultList.add(first);
            }
            return;
        }


        long cabinet = factorial[n - 1];
        int countIdx;
        long rest;
        int num;
        if (k % cabinet == 0) {
            countIdx = (int)(k / cabinet);
            rest = cabinet;

        } else {
            countIdx = (int)(k / cabinet) + 1;
            rest = k % cabinet;

        }
        num = getNumberFromCountIdx(countIdx);
        resultList.add(num);
        marked[num] = true;

        go(n - 1, rest);
    }


    public int[] solution(int n, long k) {

        numArr = new int[n + 1];
        marked = new boolean[n + 1];
        for (int i = 1; i < numArr.length; i++) {
            numArr[i] = i;
        }
        factorial = new long[21];
        long value = 1;
        for (int i = 1; i <= 20; i++) {
            value *= i;
            factorial[i] = value;
        }
        resultList = new ArrayList<>();

        go(n, k);


        int[] answer = new int[n];
        int idx = 0;
        for (Integer num : resultList) {
            answer[idx] = num;
            idx++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers12936 programmers12936 = new Programmers12936();

        long startTime = System.currentTimeMillis();
        int[] solution = programmers12936.solution(20, 2432902008176639999L);
        long endTime = System.currentTimeMillis();

        for (int num : solution) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Time=" + (endTime - startTime));
    }
}
