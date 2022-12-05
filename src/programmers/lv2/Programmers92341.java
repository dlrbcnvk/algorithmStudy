package programmers.lv2;

import java.util.*;

/**
 * 주차 요금 계산
 */
public class Programmers92341 {

    int basicTime;
    int basicFee;
    int unitTime;
    int unitFee;

    enum ParkingStatus {
        IN, OUT
    }

    static class Car implements Comparable<Car> {
        String id;
        int totalFee = 0;
        int enterHour;
        int enterMinute;
        int totalMinute = 0;
        ParkingStatus status;

        public Car(String id, int hour, int minute) {
            this.id = id;
            this.enterHour = hour;
            this.enterMinute = minute;
        }

        @Override
        public int compareTo(Car car) {
            if (Integer.parseInt(this.id) < Integer.parseInt(car.id)) {
                return -1;
            } else if (Integer.parseInt(this.id) > Integer.parseInt(car.id)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int[] solution(int[] fees, String[] records) {

        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];

        HashMap<String, Car> parkingMap = new HashMap<>();

        for (String record : records) {
            String[] recordArr = record.split(" ");
            String[] time = recordArr[0].split(":");
            Integer hour = Integer.parseInt(time[0]);
            Integer minute = Integer.parseInt(time[1]);
            String id = recordArr[1];
            String status = recordArr[2];
            if (!parkingMap.containsKey(id)) {
                // 맵에 없으면
                if (status.equals("OUT")) {
                    // 첫 기록이 OUT, 23:59 ~ 현시각
                    Car car = new Car(id, 23, 59);
                    car.totalMinute = getMinute(23, 59, hour, minute);
                    car.status = ParkingStatus.OUT;
                    parkingMap.put(id, car);
                } else {
                    // IN 으로 첫 등록을 해야 함
                    Car car = new Car(id, hour, minute);
                    car.status = ParkingStatus.IN;
                    parkingMap.put(id, car);
                }
            } else {
                // 이미 있는 경우
                Car car = parkingMap.get(id);
                if (car.status == ParkingStatus.IN) {
                    // 나갈 차례
                    int totalMinute = getMinute(car.enterHour, car.enterMinute, hour, minute);
                    car.totalMinute += totalMinute;
                    car.status = ParkingStatus.OUT;
                } else {
                    // 다시 들어올 차례
                    car.enterHour = hour;
                    car.enterMinute = minute;
                    car.status = ParkingStatus.IN;
                }
            }
        }

        ArrayList<Car> carArrayList = new ArrayList<>();
        Set<String> stringSet = parkingMap.keySet();
        for (String key : stringSet) {
            Car car = parkingMap.get(key);
            if (car.status == ParkingStatus.IN) {
                int totalMinute = getMinute(car.enterHour, car.enterMinute, 23, 59);
                car.totalMinute += totalMinute;
            }
            car.totalFee = calculateFee(car.totalMinute);
            carArrayList.add(car);
        }
        Collections.sort(carArrayList);

        int[] result = new int[carArrayList.size()];
        int idx = 0;
        for (Car car : carArrayList) {
            result[idx] = car.totalFee;
            idx++;
        }


        return result;
    }

    public int getMinute(int enterHour, int enterMinute, int exitHour, int exitMinute) {
        return (exitHour - enterHour) * 60 + (exitMinute - enterMinute);
    }

    public int calculateFee(int totalMinute) {
        int result = basicFee;
        if (totalMinute > basicTime) {
            result += Math.ceil((totalMinute - basicTime) / (double)unitTime) * unitFee;
        }
        return result;
    }

    public static void main(String[] args) {
        Programmers92341 programmers92341 = new Programmers92341();
        int[] solution = programmers92341.solution(
                new int[]{180, 5000, 10 ,600},
                new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"}
        );

        for (int fee : solution) {
            System.out.print(fee + " ");
        }
    }
}
