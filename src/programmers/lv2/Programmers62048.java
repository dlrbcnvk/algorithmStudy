package programmers.lv2;

/**
 * 멀쩡한 사각형
 * long으로 casting해야 한다. int * int -> int 라서 틀림. int범위를 넘어가면 에러내는게 아니라 의미없는 값을 얻게 되므로 WA
 */
public class Programmers62048 {

    public long solution(int w, int h) {

        long max = Math.max(w, h);
        long min = Math.min(w, h);
        long postY = -1;
        long x = 1;
        long cuttingCount = 0;

        if (w == h) {
            return (long) w * h - w;
        }

        while (x <= max) {
            long dividend = (min * x) / max;
            long rest = (min * x) % max;
            if (rest != 0) {
                if (dividend != postY) {
                    cuttingCount += 2;
                    postY = dividend;
                } else {
                    cuttingCount += 1;
                }
            }

            x++;
        }

        return (long) w * h - cuttingCount;
    }

    public static void main(String[] args) {
        Programmers62048 programmers62048 = new Programmers62048();
        long solution = programmers62048.solution(2,1);
        System.out.println(solution);
    }
}
