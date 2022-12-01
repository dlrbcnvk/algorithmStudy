package programmers.lv1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * 실패율
 */
public class Programmers42889 {

    class Stage implements Comparable<Stage> {
        int id;
        int completedCount;
        int onGoingCount;

        public Stage(int id, int completedCount, int onGoingCount) {
            this.id = id;
            this.completedCount = completedCount;
            this.onGoingCount = onGoingCount;
        }

        public double getFailRate() {
            if (completedCount == 0) {
                return 0;
            } else {
                return (double)onGoingCount / completedCount;
            }
        }

        @Override
        public int compareTo(Stage stage) {
            if (this.getFailRate() > stage.getFailRate()) {
                return -1;
            } else if (this.getFailRate() < stage.getFailRate()) {
                return 1;
            } else {
                if (this.id < stage.id) {
                    return -1;
                } else if (this.id > stage.id) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public int[] solution(int N, int[] stages) {

        HashMap<Integer, Integer> onGoingMap = new HashMap<>();
        for (int stage : stages) {
            if (!onGoingMap.containsKey(stage)) {
                onGoingMap.put(stage, 1);
            } else {
                Integer integer = onGoingMap.get(stage);
                onGoingMap.put(stage, integer + 1);
            }
        }
        Stage[] arr = new Stage[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new Stage(i, 0, 0);
        }

        Set<Integer> set = onGoingMap.keySet();
        for (Integer key : set) {
            Integer onGoingCount = onGoingMap.get(key);
            for (int i = 1; i <= key; i++) {
                if (i == N + 1) {
                    continue;
                }
                arr[i].completedCount += onGoingCount;
            }

            if (key == N + 1) {
                continue;
            }
            arr[key].onGoingCount = onGoingCount;
        }

        Stage[] resultStage = Arrays.copyOfRange(arr, 1, arr.length);
        Arrays.sort(resultStage);

        int[] result = new int[resultStage.length];
        for (int i = 0; i < resultStage.length; i++) {
            result[i] = resultStage[i].id;
        }

        return result;
    }

    public static void main(String[] args) {
        Programmers42889 programmers42889 = new Programmers42889();
        int[] solution = programmers42889.solution(
                4, new int[]{4,4,4,4,4}
        );


        for (int num : solution) {
            System.out.print(num + " ");
        }
    }
}
