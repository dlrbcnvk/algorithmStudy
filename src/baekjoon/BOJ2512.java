package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 예산
 */
public class BOJ2512 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] requests;
        requests = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(requests);
        int[] cuttingBudgetTotalRequests = new int[n];
        cuttingBudgetTotalRequests[0] = requests[0] * n;
        for (int i = 1; i < n; i++) {
            cuttingBudgetTotalRequests[i] = cuttingBudgetTotalRequests[i - 1] + (n - i) * (requests[i] - requests[i - 1]);
        }

        int budget = Integer.parseInt(br.readLine());
        // 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정한다.
        if (cuttingBudgetTotalRequests[n - 1] <= budget) {
            System.out.println(requests[n - 1]);
            return;
        }

        int st = 0; // inclusive
        int en = n - 1; // inclusive
        while (st < en) {
            int mid = (st + en) / 2;
            if (cuttingBudgetTotalRequests[mid] < budget) {
                // 시작부분을 mid로 옮겨도 아직 충분함.
                st = mid;

                if (st + 1 == en) {
                    break;
                }
            } else if (cuttingBudgetTotalRequests[mid] > budget) {
                // mid보다 budget이 부족한 상황.
                // 확실히 mid 는 아니므로 끝부분을 mid - 1 로 설정함.
                en = mid - 1;
            } else {
                System.out.println(requests[mid]);
                return;
            }
        }

        // 제일 작은 요청을 상한선으로 정해도 예산을 초과하는 경우
        if (st == 0) {
            System.out.println(budget / n);
            return;
        }

        // 지방들 예산요청 배열의 [st ~ en) 구간에서 최대의 예산을 구해보면 됨
        int requestTotal = cuttingBudgetTotalRequests[st];
        int lowerBound = requests[st]; // 상한액

        int additionalGainSpots = n - (st + 1);
        if (additionalGainSpots == 0) {
            System.out.println(lowerBound);
            return;
        } else {
            lowerBound += (budget - requestTotal) / additionalGainSpots;
        }

        System.out.println(lowerBound);
    }
}
