package programmers.lv2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 주차 요금 계산
 * 2023.4.26 AC
 */
public class Programmers92341_2 {

    public int[] solution(int[] fees, String[] records) {

        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        Map<String, String> entryMap = new HashMap<>();
        Map<String, Integer> parkingTimeMap = new HashMap<>();

        for (String record : records) {
            String[] split = record.split(" ");
            String time = split[0];
            String carId = split[1];
            String inout = split[2];

            if (inout.equals("IN")) {
                entryMap.put(carId, time);
            } else {
                // 나갈 때 time 계산
                String entryTime = entryMap.remove(carId);
                int minutes = calculateMinute(entryTime, time);

                if (parkingTimeMap.containsKey(carId)) {
                    Integer currentMinutes = parkingTimeMap.get(carId);
                    parkingTimeMap.put(carId, currentMinutes + minutes);
                } else {
                    parkingTimeMap.put(carId, minutes);
                }
            }
        }

        // 23:59 에 강제로 나감
        for (String carId : entryMap.keySet()) {
            String entryTime = entryMap.get(carId);
            int minutes = calculateMinute(entryTime, "23:59");

            if (parkingTimeMap.containsKey(carId)) {
                parkingTimeMap.put(carId, parkingTimeMap.get(carId) + minutes);
            } else {
                parkingTimeMap.put(carId, minutes);
            }
        }

        // 총 시간을 가지고 한번에 계산
        Set<String> carIdSet = parkingTimeMap.keySet();

        return carIdSet.stream()
                .sorted()
                .map(carId -> calculateFee(parkingTimeMap.get(carId), basicTime, basicFee, unitTime, unitFee))
                .mapToInt(i -> i)
                .toArray();
    }

    private int calculateMinute(String entryTime, String outTime) {
        String[] entryTimeSplit = entryTime.split(":");
        int entryHour = Integer.parseInt(entryTimeSplit[0]);
        int entryMinute = Integer.parseInt(entryTimeSplit[1]);

        String[] outTimeSplit = outTime.split(":");
        int outHour = Integer.parseInt(outTimeSplit[0]);
        int outMinute = Integer.parseInt(outTimeSplit[1]);

        return ((outHour * 60) + outMinute) - ((entryHour * 60) + entryMinute);
    }

    private int calculateFee(int totalTime, int basicTime, int basicFee, int unitTime, int unitFee) {

        if (totalTime >= basicTime) {
            if ((totalTime - basicTime) % unitTime != 0) {
                return (int) (basicFee + Math.ceil(((double)totalTime - basicTime) / unitTime) * unitFee);
            }
            return basicFee + ((totalTime - basicTime) / unitTime) * unitFee;
        } else {
            return basicFee;
        }
    }

    public static void main(String[] args) {
        Programmers92341_2 programmers92341_2 = new Programmers92341_2();
        int[] solution = programmers92341_2.solution(
                new int[]{180, 5000, 10, 600},
                new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"}
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
