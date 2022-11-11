package javajungsuk;

import java.util.*;

public class study {

    public static void main(String[] args) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(1, 1));
        pointList.add(new Point(7, 9));
        pointList.add(new Point(5, 2));
        pointList.add(new Point(5, 7));
        pointList.add(new Point(5, 3));

        Collections.sort(pointList);
        System.out.println(pointList);

        Collections.sort(pointList, new MyComparator());
        System.out.println(pointList);

        Comparator<Point> myComparatorAnonymousClass = new Comparator<>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.x < p2.x) {
                    return 1; // x에 대해서는 내림차순
                } else if (p1.x == p2.x) {
                    if (p1.y > p2.y) { // y에 대해서는 오름차순
                        return 1;
                    }
                }
                return -1;
            }
        };

        Collections.sort(pointList, myComparatorAnonymousClass);

        Comparator<Point> myComparatorLambda = (p1, p2) -> {
            if (p1.x < p2.x) {
                return 1; // x에 대해서는 내림차순
            } else if (p1.x == p2.x) {
                if (p1.y > p2.y) { // y에 대해서는 오름차순
                    return 1;
                }
            }
            return -1;
        };

        Collections.sort(pointList, myComparatorLambda);
        System.out.println(pointList);
    }
}
