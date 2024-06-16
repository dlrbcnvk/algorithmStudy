package programmers.lv2;

/**
 * 쿼드압축 후 개수 세기
 * https://school.programmers.co.kr/learn/courses/30/lessons/68936
 */
public class Programmers68936_2 {

    private int zeroCount = 0;
    private int oneCount = 0;

    public int[] solution(int[][] arr) {
        int size = arr.length;
        divide(arr, 0, 0, size);
        int[] answer = new int[]{zeroCount, oneCount};
        return answer;
    }

    public void divide(int[][] arr, int y, int x, int size) {
        int value = arr[y][x];
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (arr[i][j] != value) {
                    int halfSize = size / 2;
                    divide(arr, y, x, halfSize);
                    divide(arr, y + halfSize, x, halfSize);
                    divide(arr, y, x + halfSize, halfSize);
                    divide(arr, y + halfSize, x + halfSize, halfSize);
                    return;
                }
            }
        }
        if (value == 0) {
            zeroCount++;
        } else if (value == 1) {
            oneCount++;
        }
    }

    public static void main(String[] args) {
        Programmers68936_2 programmers689362 = new Programmers68936_2();
        int[] solution = programmers689362.solution(
                new int[][]{
                        {1,1,1,1,1,1,1,1},
                        {0,1,1,1,1,1,1,1},
                        {0,0,0,0,1,1,1,1},
                        {0,1,0,0,1,1,1,1},
                        {0,0,0,0,0,0,1,1},
                        {0,0,0,0,0,0,0,1},
                        {0,0,0,0,1,0,0,1},
                        {0,0,0,0,1,1,1,1}
                }
        );
        System.out.println(solution[0] + " " + solution[1]);
    }
}
