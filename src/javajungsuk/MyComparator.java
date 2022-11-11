package javajungsuk;

import java.util.Comparator;

public class MyComparator implements Comparator<Point> {

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
}
