package programmers.lv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 가장 큰 수
 * 무슨 기준으로 정렬해야할지 감이 안 온다..ㅠ
 * 미해결
 */
public class Programmers42746 {

    public String solution(int[] numbers) {
        String answer = "";

        int n = numbers.length;
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> list3 = new ArrayList<>();
        ArrayList<String> list4 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = String.valueOf(numbers[i]);
            switch (str.length()) {
                case 1:
                    list1.add(str);
                    break;
                case 2:
                    list2.add(str);
                    break;
                case 3:
                    list3.add(str);
                    break;
                case 4:
                    list4.add(str);
                    break;
                default:
                    break;
            }
        }
        list1.sort(Collections.reverseOrder());
        list2.sort(Collections.reverseOrder());
        list3.sort(Collections.reverseOrder());
        list4.sort(Collections.reverseOrder());
        String[] str1 = list1.toArray(String[]::new);
        String[] str2 = list2.toArray(String[]::new);
        String[] str3 = list3.toArray(String[]::new);
        String[] str4 = list4.toArray(String[]::new);


        int idx1 = 0, idx2 = 0, idx3 = 0, idx4 = 0;
        if (str1.length == 0) {
            idx1 = -5;
        }
        if (str2.length == 0) {
            idx2 = -5;
        }
        if (str3.length == 0) {
            idx3 = -5;
        }
        if (str4.length == 0) {
            idx4 = -5;
        }

        while (true) {
            boolean breakCondition = true;
            if (idx1 != -5 && idx1 != str1.length) {
                breakCondition = false;
            }
            if (idx2 != -5 && idx2 != str2.length) {
                breakCondition = false;
            }
            if (idx3 != -5 && idx3 != str3.length) {
                breakCondition = false;
            }
            if (idx4 != -5 && idx4 != str4.length) {
                breakCondition = false;
            }
            if (breakCondition) {
                break;
            }
            char firstValue = ' ';
            char value = ' ';
            int choice = 1;
            if (idx1 != -5 && idx1 < str1.length) {
                firstValue = str1[idx1].charAt(0);
            }
            if (idx2 != -5 && idx2 < str2.length) {
                value = str2[idx2].charAt(0);
                if (value > firstValue) {
                    firstValue = value;
                    choice = 2;
                }
            }
            if (idx3 != -5 && idx3 < str3.length) {
                value = str3[idx3].charAt(0);
                if (value > firstValue) {
                    firstValue = value;
                    choice = 3;
                }
            }
            if (idx4 != -5 && idx4 < str4.length) {
                value = str4[idx4].charAt(0);
                if (value > firstValue) {
                    firstValue = value;
                    choice = 4;
                }
            }

            switch (choice) {
                case 1:
                    if (idx1 != -5) {
                        while (idx1 < str1.length && str1[idx1].charAt(0) == firstValue) {
                            answer += str1[idx1];
                            idx1++;
                        }
                    }
                    break;
                case 2:
                    if (idx2 != -5) {
                        while (idx2 < str2.length && str2[idx2].charAt(0) == firstValue) {
                            answer += str2[idx2];
                            idx2++;
                        }
                    }
                    break;
                case 3:
                    if (idx3 != -5) {
                        while (idx3 < str3.length && str3[idx3].charAt(0) == firstValue) {
                            answer += str3[idx3];
                            idx3++;
                        }
                    }
                    break;
                case 4:
                    if (idx4 != -5) {
                        while (idx4 < str4.length && str4[idx4].charAt(0) == firstValue) {
                            answer += str4[idx4];
                            idx4++;
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
//        Programmers42746 programmers42746 = new Programmers42746();
//        String solution = programmers42746.solution(new int[]{3, 30, 34, 5, 9});
//        System.out.println(solution);

        String str1 = "32";
        String str2 = "34";
        String str3 = "3";

        System.out.println(str1.compareTo(str2));
        System.out.println(str2.compareTo(str3));
        System.out.println(str1.compareTo(str3));

    }
}
